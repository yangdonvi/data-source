package com.ncwu.datasource.calc.dao;

import com.ncwu.datasource.calc.entity.BaseData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Donvi Yang on 2019/4/29.
 */
@Repository("BaseDataDao")
@Mapper
public interface BaseDataDao {
    int insertBaseData(BaseData baseData);
    List<BaseData> selectBaseData (@Param("beginDate") String beginDate, @Param("endDate") String endDate);
}
