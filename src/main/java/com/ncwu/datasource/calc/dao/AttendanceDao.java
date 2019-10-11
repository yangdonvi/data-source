package com.ncwu.datasource.calc.dao;

import com.ncwu.datasource.calc.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Donvi Yang on 2019/4/29.
 */
@Repository("AttendanceDao")
@Mapper
public interface AttendanceDao {
    int insertAttendance(Attendance attendance);
    List<Attendance> selectAttendance (@Param("beginDate") String beginDate, @Param("endDate") String endDate);
}
