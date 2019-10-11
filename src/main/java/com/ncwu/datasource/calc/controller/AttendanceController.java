package com.ncwu.datasource.calc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncwu.datasource.calc.service.AttendanceService;
import com.ncwu.datasource.calc.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
@RequestMapping("/attendance")
@RestController
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    @PostMapping("/getDptmtAttendance")
    @ResponseBody
    public ServerResponse getDptmtAttendance(@RequestBody String data){
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = attendanceService.getDptmtAttendance(jsonObject.getString("beginDate"),jsonObject.getString("endDate"),
                    jsonObject.getString("dptmt"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    @PostMapping("/getLentNumBigestReader")
    @ResponseBody
    public ServerResponse getLentNumBigestReader(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = attendanceService.getLentNumBigestReader(jsonObject.getString("beginDate"),jsonObject.getString("endDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }


}
