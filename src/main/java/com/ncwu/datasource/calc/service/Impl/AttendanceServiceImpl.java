package com.ncwu.datasource.calc.service.Impl;

import com.ncwu.datasource.calc.dao.AttendanceDao;
import com.ncwu.datasource.calc.entity.Attendance;
import com.ncwu.datasource.calc.entity.BookWorth;
import com.ncwu.datasource.calc.service.AttendanceService;
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
public class AttendanceServiceImpl implements AttendanceService{

    @Autowired
    AttendanceDao attendanceDao;

    @Override
    public ServerResponse getDptmtAttendance(String beginDate, String endDate, String dptmt) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<Attendance> attendances = attendanceDao.selectAttendance(beginDate, endDate);
        if (attendances != null && attendances.size() != 0) {
            // 返回全馆或对应学院的数据
            List<Attendance> attendances1 = new ArrayList<>();
            // 返回全馆数据
            if (dptmt.equals("全馆")){
                for (Attendance attendance : attendances) {
                    if (attendance.getDeptName()!=null && attendance.getMajorName()==null) {
                        attendances1.add(attendance);
                    }
                }
            }
            // 返回对应专业的数据
            else {
                for (Attendance attendance : attendances) {
                    if (attendance.getDeptName() != null && attendance.getDeptName().equals(dptmt) && attendance.getMajorName()!=null) {
                        attendances1.add(attendance);
                    }
                }
            }
            if (attendances1.size() > 0){
                return ServerResponse.createBySuccess(attendances1);
            }
        }
        // 库中没有则运行mapreduce
        List<Attendance> attendances2 = new ArrayList<>();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        // 获取sql
        String sql = "";
        if (dptmt.equals("全馆")){
            sql = HiveSql.getAllDepartmentAttendance(beginDate, endDate);
        }  else {
            sql = HiveSql.getDptmtMajorAttendance(beginDate, endDate, dptmt);
        }
        System.out.println("Running:" + sql);
        // 执行sql
        ResultSet res = stmt.executeQuery(sql);
        int flag = 1;
        // 查询全馆的
        if (dptmt.equals("全馆")){
            while (res.next()) {
                if (res.getString(1).equals("")){
                    continue;
                }
                Attendance attendance = new Attendance();
                attendance.setDeptName(res.getString(1));
                attendance.setTotalNum(res.getInt(2));
                attendance.setSortNum(flag);
                attendance.setBeginDate(beginDate);
                attendance.setEndDate(endDate);
                attendanceDao.insertAttendance(attendance);
                attendances2.add(attendance);
                flag++;
            }
        }else {
            // 查询某个学院的
            while (res.next()) {
                Attendance attendance = new Attendance();
                attendance.setDeptName(dptmt);
                attendance.setMajorName(res.getString(1));
                attendance.setTotalNum(res.getInt(2));
                attendance.setSortNum(flag);
                attendance.setBeginDate(beginDate);
                attendance.setEndDate(endDate);
                attendanceDao.insertAttendance(attendance);
                attendances2.add(attendance);
                flag++;
            }
        }
        // 关闭连接
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(attendances2);
    }

    @Override
    public ServerResponse getLentNumBigestReader(String beginDate, String endDate) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<Attendance> attendances = attendanceDao.selectAttendance(beginDate, endDate);
        if (attendances != null && attendances.size() != 0) {
            for (Attendance attendance : attendances) {
                if (attendance.getStuName() != null) {
                    return ServerResponse.createBySuccess(attendance);
                }
            }
        }
        // 库中没有则运行mapreduce
        Attendance attendance2 = new Attendance();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        // 获取sql
        String sql = HiveSql.getLentNumBigestReader(beginDate, endDate);
        System.out.println("Running:" + sql);
        // 执行sql
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            attendance2.setStuName(res.getString(1));
            attendance2.setTotalNum(res.getInt(2));
            attendance2.setBeginDate(beginDate);
            attendance2.setEndDate(endDate);
            attendanceDao.insertAttendance(attendance2);
        }
        // 关闭连接
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(attendance2);
    }
}
