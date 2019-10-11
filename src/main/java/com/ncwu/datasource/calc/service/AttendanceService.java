package com.ncwu.datasource.calc.service;

import com.ncwu.datasource.calc.util.ServerResponse;

import java.sql.SQLException;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
public interface AttendanceService {
    ServerResponse getDptmtAttendance(String beginDate, String endDate, String dptmt) throws SQLException, ClassNotFoundException;

    ServerResponse getLentNumBigestReader(String beginDate, String endDate) throws SQLException, ClassNotFoundException;
}
