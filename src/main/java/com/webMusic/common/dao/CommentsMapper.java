package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.Comments;
import com.webMusic.common.model.CommentsExample;
import com.webMusic.common.model.SongClassify;

public interface CommentsMapper {
    int countByExample(CommentsExample example);

    int deleteByExample(CommentsExample example);

    int insert(Comments record);

    int insertSelective(Comments record);

    List<Comments> selectByExample(CommentsExample example);

    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);
    
    Comments selectByPrimaryKey(String songClassifyId);
}