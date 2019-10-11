package com.ncwu.datasource.calc.dao;

import com.ncwu.datasource.calc.entity.InfluenceFactor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Donvi Yang on 2019/4/29.
 */
@Repository("InfluenceFactorDao")
@Mapper
public interface InfluenceFactorDao {
    int insertInfluenceFactor(InfluenceFactor influenceFactor);
    List<InfluenceFactor> selectInfluenceFactor ();
    List<InfluenceFactor> getGradeTopFive (@Param("beginDate") String beginDate, @Param("endDate") String endDate);

}
