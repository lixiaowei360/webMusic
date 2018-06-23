package com.webMusic.Portal.service;

import com.sun.tools.hat.internal.model.Root;
import com.webMusic.common.dao.CommentsMapper;
import com.webMusic.common.model.Comments;
import com.webMusic.common.model.CommentsExample;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.impl.CommentListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("commentManagerServiceImpl")
public class CommentManagerServiceImpl extends BaseMybatisDao<CommentsMapper> {
    @Autowired
    CommentListServiceImpl commentListServiceImpl;

    @Autowired
    private CommentsMapper commentsMapper;

    public Pagination<Comments> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return  commentListServiceImpl.findByPage(resultMap,pageNo,pageSize);
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

        private void getCommentsChild(List<Comments> commentss, String topicId,Integer Root) {
            Root++;//层级+1
            CommentsExample example = new CommentsExample();
            example.setOrderByClause("cs.create_time desc");
            example.createCriteria().andTopicIdEqualTo(topicId);
            example.createCriteria().andIsPublicEqualTo(true);
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

    public ResultMessage saveSongComments(Comments comments) throws Exception {
        return commentListServiceImpl.save(comments);
    }
}
