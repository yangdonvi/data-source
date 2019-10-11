package com.ncwu.datasource.calc.service;

import com.ncwu.datasource.calc.util.ServerResponse;

import java.sql.SQLException;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
public interface BaseDataService {
    ServerResponse getTotalLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException;

    ServerResponse getLanguageLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException;

    ServerResponse getCampusLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException;

    ServerResponse getAvgLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException;

    ServerResponse getEBook();
}
