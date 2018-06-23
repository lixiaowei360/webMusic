package com.webMusic.Portal.service;

import com.webMusic.common.dao.CommentsMapper;
import com.webMusic.common.dao.UUserMapper;
import com.webMusic.common.model.Comments;
import com.webMusic.common.model.UUser;
import com.webMusic.core.mybatis.BaseMybatisDao;
import com.webMusic.core.mybatis.page.Pagination;
import com.webMusic.core.statics.ResultMessage;
import com.webMusic.song.service.impl.CommentListServiceImpl;
import com.webMusic.user.service.UUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserManagerServiceImpl extends BaseMybatisDao<UUserMapper> {
    public UUser getCurrentUser(){
        return (UUser) SecurityUtils.getSubject().getPrincipal();
    }
}
