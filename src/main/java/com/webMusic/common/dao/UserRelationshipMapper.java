package com.webMusic.common.dao;

import com.webMusic.common.model.UserRelationship;
import com.webMusic.common.model.UserRelationshipExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserRelationshipMapper {
    int countByExample(UserRelationshipExample example);

    int deleteByExample(UserRelationshipExample example);

    int deleteByPrimaryKey(String relationshipId);

    int insert(UserRelationship record);

    int insertSelective(UserRelationship record);

    List<UserRelationship> selectByExample(UserRelationshipExample example);

    UserRelationship selectByPrimaryKey(String relationshipId);

    int updateByExampleSelective(@Param("record") UserRelationship record, @Param("example") UserRelationshipExample example);

    int updateByExample(@Param("record") UserRelationship record, @Param("example") UserRelationshipExample example);

    int updateByPrimaryKeySelective(UserRelationship record);

    int updateByPrimaryKey(UserRelationship record);

    Integer findUserRealtionCount(UserRelationship userRelationship);
}