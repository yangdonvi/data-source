package com.ncwu.datasource.dao;

import com.ncwu.datasource.entity.BorrowRecord;
import com.ncwu.datasource.entity.BorrowRecordBuild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Donvi Yang on 2019/3/24.
 */
@Repository("borrowRecordDao")
@Mapper
public interface BorrowRecordDao {
    BorrowRecord selectById (@Param("id") Integer id);

    List<BorrowRecord> selectAllRecord ();

    void insertBorrowRecordBuild (BorrowRecordBuild borrowRecordBuild);
}
