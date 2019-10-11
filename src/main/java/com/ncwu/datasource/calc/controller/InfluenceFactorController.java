package com.ncwu.datasource.calc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncwu.datasource.calc.service.InfluenceFactorService;
import com.ncwu.datasource.calc.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Donvi Yang on 2019/5/3.
 */
@RequestMapping("/influenceFactor")
@RestController
public class InfluenceFactorController {

    @Autowired
    InfluenceFactorService influenceFactorService;

    // 获取各月份借出数量
    @PostMapping("/getMonthNum")
    @ResponseBody
    public ServerResponse getMonthNum(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = influenceFactorService.getMonthNum(Integer.parseInt(jsonObject.getString("begin").split("-")[1]),
                    Integer.parseInt(jsonObject.getString("end").split("-")[1]));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }
    // 获取各年级最受欢迎前五名
    @PostMapping("/getGradeTopFive")
    @ResponseBody
    public ServerResponse getGradeTopFive(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = influenceFactorService.getGradeTopFive(jsonObject.getString("beginDate"),jsonObject.getString("endDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

}
