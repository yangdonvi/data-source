package com.ncwu.datasource.calc.service.Impl;

import com.ncwu.datasource.calc.dao.BookWorthDao;
import com.ncwu.datasource.calc.entity.BaseData;
import com.ncwu.datasource.calc.entity.BookWorth;
import com.ncwu.datasource.calc.service.BookWorthService;
import com.ncwu.datasource.calc.util.HiveClientUtils;
import com.ncwu.datasource.calc.util.HiveSql;
import com.ncwu.datasource.calc.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
@Service
public class BookWorthServiceImpl implements BookWorthService{

    @Autowired
    BookWorthDao bookWorthDao;

    @Override
    public ServerResponse getAllBookTopTen(String beginDate, String endDate) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<BookWorth> bookWorths = bookWorthDao.selectBookWorth(beginDate, endDate);
        if (bookWorths != null && bookWorths.size() != 0) {
            // 只返回全馆的数据
            List<BookWorth> bookWorths1 = new ArrayList<>();
            for (BookWorth bookWorth : bookWorths) {
                if (bookWorth.getDepartName().equals("全馆")) {
                    bookWorths1.add(bookWorth);
                }
            }
            if (bookWorths1.size() > 0){
                return ServerResponse.createBySuccess(bookWorths1);
            }
        }
        // 库中没有则运行mapreduce
        List<BookWorth> bookWorths2 = new ArrayList<>();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        // 获取sql
        String sql = HiveSql.getAllBookTopTen(beginDate, endDate);
        System.out.println("Running:" + sql);
        // 执行sql
        ResultSet res = stmt.executeQuery(sql);
        int flag = 1;
        while (res.next()) {
            BookWorth bookWorth = new BookWorth();
            bookWorth.setDepartName("全馆");
            bookWorth.setBookName(res.getString(1));
            bookWorth.setTotalNum(res.getInt(2));
            bookWorth.setSortNum(flag);
            bookWorth.setBeginDate(beginDate);
            bookWorth.setEndDate(endDate);
            bookWorthDao.insertBookWorth(bookWorth);
            bookWorths2.add(bookWorth);
            flag++;
        }
        // 关闭连接
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(bookWorths2);
    }

    @Override
    public ServerResponse getDepartmentTopFive(String beginDate, String endDate, String dptmt) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<BookWorth> bookWorths = bookWorthDao.selectBookWorth(beginDate, endDate);
        if (bookWorths != null && bookWorths.size() != 0) {
            // 只返回全馆的数据
            List<BookWorth> bookWorths1 = new ArrayList<>();
            for (BookWorth bookWorth : bookWorths) {
                if (bookWorth.getDepartName().equals(dptmt)) {
                    bookWorths1.add(bookWorth);
                }
            }
            if (bookWorths1.size() > 0){
                return ServerResponse.createBySuccess(bookWorths1);
            }
        }
        // 库中没有则运行mapreduce
        List<BookWorth> bookWorths2 = new ArrayList<>();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        // 获取sql
        String sql = "";
        if (dptmt.equals("教职工")){
            sql = HiveSql.getDepartmentTopFive(beginDate, endDate, "");
        } else if (dptmt.equals("男")){
            sql = HiveSql.getTopThreeBySex(beginDate, endDate, 0);
        } else if (dptmt.equals("女")){
            sql = HiveSql.getTopThreeBySex(beginDate, endDate, 1);
        } else {
            sql = HiveSql.getDepartmentTopFive(beginDate, endDate, dptmt);
        }
        System.out.println("Running:" + sql);
        // 执行sql
        ResultSet res = stmt.executeQuery(sql);
        int flag = 1;
        while (res.next()) {
            BookWorth bookWorth = new BookWorth();
            bookWorth.setDepartName(dptmt);
            bookWorth.setBookName(res.getString(1));
            bookWorth.setTotalNum(res.getInt(2));
            bookWorth.setSortNum(flag);
            bookWorth.setBeginDate(beginDate);
            bookWorth.setEndDate(endDate);
            bookWorthDao.insertBookWorth(bookWorth);
            bookWorths2.add(bookWorth);
            flag++;
        }
        // 关闭连接
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(bookWorths2);
    }

}
