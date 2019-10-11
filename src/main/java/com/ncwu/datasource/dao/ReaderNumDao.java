package com.ncwu.datasource.dao;

import com.ncwu.datasource.calc.entity.ReaderNum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Donvi Yang on 2019/5/1.
 */
@Repository("ReaderNumDao")
@Mapper
public interface ReaderNumDao {
    int insert(ReaderNum readerNum);

    List<ReaderNum> selectAll();
}
