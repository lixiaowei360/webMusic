package com.webMusic.song.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.webMusic.common.dao.CommentsMapper;
import com.webMusic.common.model.Comments;
import com.webMusic.common.model.CommentsExample;
import com.webMusic.common.model.UUser;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CommentListServiceImpl extends BaseMybatisDao<CommentsMapper>{

	@Autowired
	private CommentsMapper commentsMapper;
	
	public int deleteByExample(Comments example) {
		
		CommentsExample ce = new CommentsExample();
		if(StringUtils.isEmpty(example.getCommentsId())){
			ce.createCriteria().andCommentsIdEqualTo(example.getCommentsId());
		}
		return commentsMapper.deleteByExample(ce);
	}

	public ResultMessage save(Comments record) throws Exception  {
		UUser user = (UUser) SecurityUtils.getSubject().getPrincipal();
		ResultMessage r = new ResultMessage();
		Integer flag = null;
		if(StringUtils.isEmpty(record.getCommentsId())){//添加
			record.setCreateTime(Calendar.getInstance().getTime());
		 	record.setFromUid(String.valueOf(user.getId()));
		 	record.setCommentsId(UUID.randomUUID().toString());
		 flag = commentsMapper.insertSelective(record);
		}else{//修改
			CommentsExample ce = new CommentsExample();
			ce.createCriteria().andCommentsIdEqualTo(record.getCommentsId());
			flag = commentsMapper.updateByExampleSelective(record, ce);
		}
		r.setCode(flag != 0?"200":"500");
		r.addSuccessMessage(flag != 0?"保存成功":"失败");
		return r;
	}

	public Pagination<Comments> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		 Pagination page = super.findPage(resultMap, pageNo, pageSize);
		 return page;
	}
	
	private void getCommentsChild(List<Comments> commentss, String topicId,Integer Root) {
		Root++;//层级+1
		CommentsExample example = new CommentsExample();
		example.setOrderByClause("cs.create_time desc");
		example.createCriteria().andTopicIdEqualTo(topicId);
		List<Comments> cs = commentsMapper.selectByExample(example);
		if (null != cs && cs.size()>0) { 
			  for (int i = 0; i < cs.size(); i++) { 
				  Comments c = cs.get(i);
				  c.setLeve(Root);
				  commentss.add(c);
				  getCommentsChild(commentss,c.getCommentsId(), Root);
			  }
		}
	}


	public List<Comments> selectCommentsList(Comments record){
		ResultMessage r = new ResultMessage();
		CommentsExample ce = new CommentsExample();
		
		if(!StringUtils.isEmpty(record.getTopicType())){
			ce.createCriteria().andTopicIdEqualTo(record.getTopicId());
		}
		if(!StringUtils.isEmpty(record.getTopicType())){
			ce.createCriteria().andTopicTypeEqualTo(record.getTopicType());
		}
		return commentsMapper.selectByExample(ce);
	}
	public Comments getComments(Comments cs){
		return commentsMapper.selectByPrimaryKey(cs.getCommentsId());
		
	}
	
	public Pagination<Comments> lookCommentsTalk(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		 Pagination page = super.findPage(resultMap, pageNo, pageSize);
		Integer root = 1;//用于标记层级关系
		List<Comments> commentss = new ArrayList<>();
		for(Object o : page.getList()){
			Comments c = (Comments) o;
			c.setLeve(root);
			commentss.add(c);
			getCommentsChild(commentss,c.getCommentsId(),root);
			root = 1;//重新置换为1
		}
		 page.setList(commentss);
		 return page;
	}
}
