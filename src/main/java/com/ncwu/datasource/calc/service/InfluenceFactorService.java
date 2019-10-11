package com.ncwu.datasource.calc.service;

import com.ncwu.datasource.calc.util.ServerResponse;

import java.sql.SQLException;

/**
 * Created by Donvi Yang on 2019/5/3.
 */
public interface InfluenceFactorService {

    ServerResponse getMonthNum(Integer begin, Integer end) throws SQLException, ClassNotFoundException;

    ServerResponse getGradeTopFive(String beginDate, String endDate) throws SQLException, ClassNotFoundException;
}
