package com.webMusic.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.webMusic.common.model.CarouselContent;
import com.webMusic.common.model.CarouselContentExample;

public interface CarouselContentMapper {
    int countByExample(CarouselContentExample example);

    int deleteByExample(CarouselContentExample example);

    int deleteByPrimaryKey(String carouselId);

    int insert(CarouselContent record);

    int insertSelective(CarouselContent record);

    List<CarouselContent> selectByExample(CarouselContentExample example);

    CarouselContent selectByPrimaryKey(String carouselId);

    int updateByExampleSelective(@Param("record") CarouselContent record, @Param("example") CarouselContentExample example);

    int updateByExample(@Param("record") CarouselContent record, @Param("example") CarouselContentExample example);

    int updateByPrimaryKeySelective(CarouselContent record);

    int updateByPrimaryKey(CarouselContent record);
}