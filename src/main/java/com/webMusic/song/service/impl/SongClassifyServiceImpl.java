package com.webMusic.song.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.webMusic.common.dao.SongClassifyMapper;
import com.webMusic.common.dao.SongListMapper;
import com.webMusic.common.model.SongClassify;
import com.webMusic.common.model.SongClassifyExample;
import com.webMusic.common.model.SongListExample;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.song.service.SongClassifyService;

@Service
public class SongClassifyServiceImpl extends BaseMybatisDao<SongClassifyMapper> implements SongClassifyService {

	@Resource
	private SongClassifyMapper songClassifyMapper;
	
	
	@Override
	public int countByExample(SongClassify example) {
		SongClassifyExample se = new SongClassifyExample();
		
		if(!StringUtils.isEmpty(example.getSongClassifyName())){
			se.createCriteria().andSongClassifyIdEqualTo(example.getSongClassifyName());
		}
		return songClassifyMapper.countByExample(se);
	}

	@Override
	public int deleteByExample(SongClassify example) {
		SongClassifyExample se = new SongClassifyExample();
		example = songClassifyMapper.selectByPrimaryKey(example.getSongClassifyId());
		//删除节点需要考虑将其父节点
		if(!StringUtils.isEmpty((example.getSongClassifyId()))){
			se.createCriteria().andSongClassifyIdEqualTo(example.getSongClassifyId());
		}
		songClassifyMapper.deleteByExample(se);
		
		//查找父节点下是否有子节点
		se = new SongClassifyExample();
		se.createCriteria().andPidEqualTo(example.getPid());
		if(songClassifyMapper.countByExample(se) == 0){//等于0需要将删除节点的父节点isParent设置为false
			
			se = new SongClassifyExample();
			se.createCriteria().andSongClassifyIdEqualTo(example.getPid());
			example = new SongClassify();
			example.setIsParent(false);
			songClassifyMapper.updateByExampleSelective(example, se);
		}
		
		return 1;
	}

	@Override
	public int deleteByPrimaryKey(String songListClassifyId) {
		return songClassifyMapper.deleteByPrimaryKey(songListClassifyId);
	}

	@Override
	public int insert(SongClassify record) {
		
		//插入之前设置上一个节点状态为非子节点
		SongClassify s = new SongClassify();
		s.setSongClassifyId(record.getPid());
		s.setIsParent(true);
		songClassifyMapper.updateByPrimaryKeySelective(s);
		
		//新插入的是子节点
		record.setIsParent(false);
		record.setSongClassifyId(UUID.randomUUID().toString());
		
		return songClassifyMapper.insert(record);
	}

	@Override
	public int insertSelective(SongClassify record) {
		return 0;
	}

	@Override
	public List<SongClassify> selectBySongClassify(SongClassify example) {
		SongClassifyExample se = new SongClassifyExample();
		SongClassifyExample.Criteria ca = se.createCriteria();
		if(!StringUtils.isEmpty(example.getSongClassifyName())){
			ca.andSongClassifyIdLike(("%"+example.getSongClassifyName()+"%"));
		}
		if(!StringUtils.isEmpty(example.getSongClassifyId())){
			ca.andPidEqualTo(example.getSongClassifyId());
		}
		return songClassifyMapper.selectByExample(se);
	}

	@Override
	public SongClassify selectByPrimaryKey(String SongClassifyId) {
		return songClassifyMapper.selectByPrimaryKey(SongClassifyId);
	}

	@Override
	public int updateByExample(SongClassify record) {
		return songClassifyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Pagination<SongClassify> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSizeTotal) {
		//过滤出父节点
		resultMap.put("pid", "0");
		Pagination page = super.findPage(resultMap, pageNo, pageSizeTotal);

		List<SongClassify> songClassifys = new ArrayList<>();
		songClassifys = throughtParentGetChild(page.getList());
		//添加根节点
		SongClassify sRoot = new SongClassify();
		sRoot.setSongClassifyId("0");
		sRoot.setPid("-1");
		sRoot.setSongClassifyName("歌曲分类");
		songClassifys.add(sRoot);
		page.setList(songClassifys);
		
		return page;
	}

	@Override
	public List<SongClassify> throughtParentGetChild(List<SongClassify> parent){
		//申请临时变量
		List<SongClassify> songClassifys = new ArrayList<>();
		Integer root = 1;//用于标记层级关系
		//为父节点添加子节点,循环遍历
		for(Object o : parent){
			SongClassify s = (SongClassify) o;
			s.setHierarchy(root);
			songClassifys.add(s);
			getSongClassifyChild(songClassifys,s.getSongClassifyId(),root);
			root = 1;//重新置换为1
		}
		return songClassifys;
	}
	//递归查询子节点
	public void getSongClassifyChild(List<SongClassify> saveSongClassifys,String pid,Integer root){
		root++;
		SongClassifyExample example = new SongClassifyExample();
		example.createCriteria().andPidEqualTo(pid);
		List<SongClassify> songClassifys = songClassifyMapper.selectByExample(example);
		if (null != songClassifys && songClassifys.size()>0) { 
			  for (int i = 0; i < songClassifys.size(); i++) { 
				  SongClassify s = songClassifys.get(i);
				  s.setHierarchy(root);
				  saveSongClassifys.add(s);
				  getSongClassifyChild(saveSongClassifys,s.getSongClassifyId(),root);
			  }
		}
	}
	
}
