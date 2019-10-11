package com.ncwu.datasource.calc.controller;

import com.alibaba.fastjson.JSONObject;
import com.ncwu.datasource.calc.entity.BookWorth;
import com.ncwu.datasource.calc.service.BookWorthService;
import com.ncwu.datasource.calc.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
@RequestMapping("/bookWorth")
@RestController
public class BookWorthController {

    @Autowired
    BookWorthService bookWorthService;

    // 全馆最受欢迎前十
    @PostMapping("/getAllBookTopTen")
    @ResponseBody
    public ServerResponse getAllBookTopTen(@RequestBody String data){
        System.out.println("getAllBookTopTen:"+data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = bookWorthService.getAllBookTopTen(jsonObject.getString("beginDate"),jsonObject.getString("endDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    // 各院最受欢迎前五
    // 最受教职工欢迎前五(传入的dptmt字段为"教职工"即可)
    // 最受男女生欢迎的前三名，传入的dptmt字段为男/女即可
    @PostMapping("/getDepartmentTopFive")
    @ResponseBody
    public ServerResponse getDepartmentTopFive(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        System.out.println("getDepartmentTopFive:"+jsonObject.getString("dptmt")+data);
        ServerResponse serverResponse = new ServerResponse();
        try {
            serverResponse = bookWorthService.getDepartmentTopFive(jsonObject.getString("beginDate"),jsonObject.getString("endDate"),
                    jsonObject.getString("dptmt"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

}
