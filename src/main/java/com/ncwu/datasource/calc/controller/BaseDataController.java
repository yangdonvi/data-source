package com.ncwu.datasource.calc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncwu.datasource.calc.service.BaseDataService;
import com.ncwu.datasource.calc.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
@RequestMapping("/baseData")
@RestController
public class BaseDataController {

    @Autowired
    BaseDataService baseDataService;

    // 全年累计外借图书册数
    @PostMapping("/getTotalLentNum")
    @ResponseBody
    public ServerResponse getTotalLentNum(@RequestBody String data){
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = baseDataService.getTotalLentNum(jsonObject.getString("beginDate"),jsonObject.getString("endDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    // 各语种外借图书册数
    @PostMapping("/getLanguageLentNum")
    @ResponseBody
    public ServerResponse getLanguageLentNum(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = baseDataService.getLanguageLentNum(jsonObject.getString("beginDate"),jsonObject.getString("endDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    // 各分馆外借图书册数
    @PostMapping("/getCampusLentNum")
    @ResponseBody
    public ServerResponse getCampusLentNum(@RequestBody String data){
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = baseDataService.getCampusLentNum(jsonObject.getString("beginDate"),jsonObject.getString("endDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    // 人均借阅册数
    @PostMapping("/getAvgLentNum")
    @ResponseBody
    public ServerResponse getAvgLentNum(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = baseDataService.getAvgLentNum(jsonObject.getString("beginDate"),jsonObject.getString("endDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    // 各电子书库访问量、下载量
    @PostMapping("/getEBook")
    @ResponseBody
    public ServerResponse getEBook(){
        ServerResponse serverResponse = baseDataService.getEBook();
        return serverResponse;
    }


}
