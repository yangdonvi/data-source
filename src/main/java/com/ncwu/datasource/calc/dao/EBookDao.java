package com.ncwu.datasource.calc.dao;

import com.ncwu.datasource.calc.entity.EBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
@Repository("EBookDao")
@Mapper
public interface EBookDao {
    List<EBook> selectAll();
}
