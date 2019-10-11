package com.ncwu.datasource.calc.service.Impl;

import com.ncwu.datasource.calc.dao.BaseDataDao;
import com.ncwu.datasource.calc.dao.EBookDao;
import com.ncwu.datasource.calc.entity.BaseData;
import com.ncwu.datasource.calc.entity.EBook;
import com.ncwu.datasource.calc.entity.ReaderNum;
import com.ncwu.datasource.calc.service.BaseDataService;
import com.ncwu.datasource.calc.util.HiveClientUtils;
import com.ncwu.datasource.calc.util.HiveSql;
import com.ncwu.datasource.calc.util.ServerResponse;
import com.ncwu.datasource.dao.ReaderNumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donvi Yang on 2019/5/2.
 */
@Service
public class BaseDateServiceImpl implements BaseDataService {

    @Autowired
    BaseDataDao baseDataDao;
    @Autowired
    EBookDao eBookDao;
    @Autowired
    ReaderNumDao readerNumDao;

    @Override
    public ServerResponse getTotalLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<BaseData> baseDatas = baseDataDao.selectBaseData(beginDate, endDate);
        if (baseDatas != null && baseDatas.size() != 0) {
            for (BaseData baseData : baseDatas) {
                if (baseData.getTotalOutBookNum() != null) {
                    return ServerResponse.createBySuccess(baseData);
                }
            }
        }
        // 库中没有则运行mapreduce
        BaseData baseData2 = new BaseData();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        // 获取sql
        String sql = HiveSql.getTotalLentNum(beginDate, endDate);
        System.out.println("Running:" + sql);
        // 执行sql
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            baseData2.setTotalOutBookNum(res.getInt(1));
            baseData2.setBeginDate(beginDate);
            baseData2.setEndDate(endDate);
            baseDataDao.insertBaseData(baseData2);
        }
        // 关闭连接
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(baseData2);
    }

    @Override
    public ServerResponse getLanguageLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<BaseData> baseDatas = baseDataDao.selectBaseData(beginDate, endDate);
        if (baseDatas != null && baseDatas.size() != 0) {
            // 只返回书种类的数据
            List<BaseData> baseDatas3 = new ArrayList<>();
            for (BaseData baseData : baseDatas) {
                if (baseData.getLanguageType() != null) {
                    baseDatas3.add(baseData);
                }
            }
            if (baseDatas3.size() > 0){
                return ServerResponse.createBySuccess(baseDatas3);
            }
        }
        // 库中没有则运行mapreduce
        List<BaseData> baseDatas2 = new ArrayList<>();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        // 获取sql
        String sql = HiveSql.getLanguageLentNum(beginDate, endDate);
        System.out.println("Running:" + sql);
        // 执行sql
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            BaseData baseData2 = new BaseData();
            baseData2.setLanguageType(res.getString(1));
            baseData2.setLanguageNum(res.getInt(2));
            baseData2.setBeginDate(beginDate);
            baseData2.setEndDate(endDate);
            baseDataDao.insertBaseData(baseData2);
            baseDatas2.add(baseData2);
        }
        // 关闭连接
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(baseDatas2);
    }

    @Override
    public ServerResponse getCampusLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<BaseData> baseDatas = baseDataDao.selectBaseData(beginDate, endDate);
        if (baseDatas != null && baseDatas.size() != 0) {
            // 只返回校区借书量的数据
            List<BaseData> baseDatas3 = new ArrayList<>();
            for (BaseData baseData : baseDatas) {
                if (baseData.getCampusName() != null) {
                    baseDatas3.add(baseData);
                }
            }
            if (baseDatas3.size() > 0){
                return ServerResponse.createBySuccess(baseDatas3);
            }
        }
        // 库中没有则运行mapreduce
        List<BaseData> baseDatas2 = new ArrayList<>();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        // 获取sql
        String sql = HiveSql.getAddressLentNum(beginDate, endDate);
        System.out.println("Running:" + sql);
        // 执行sql
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            BaseData baseData2 = new BaseData();
            baseData2.setCampusName(res.getString(1));
            baseData2.setCampusOutBookNum(res.getInt(2));
            baseData2.setBeginDate(beginDate);
            baseData2.setEndDate(endDate);
            baseDataDao.insertBaseData(baseData2);
            baseDatas2.add(baseData2);
        }
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(baseDatas2);
    }

    @Override
    public ServerResponse getAvgLentNum(String beginDate, String endDate) throws SQLException, ClassNotFoundException {
        ServerResponse serverResponse = getTotalLentNum(beginDate,endDate);
        BaseData baseData = (BaseData) serverResponse.getData();
        // 等出勤率展示模块写好后，直接调接口，
        // 出勤率与平均借阅（本接口）有何区别：
        // 出勤率可以展示出勤次数，本接口再处以人数即可
        List<ReaderNum> readerNums = new ArrayList<>();
        readerNums = readerNumDao.selectAll();
        for (ReaderNum readerNum : readerNums){
            if (readerNum.getSchoolNum() != null){
                float result = (float) baseData.getTotalOutBookNum() / readerNum.getSchoolNum();
                DecimalFormat df = new DecimalFormat("0.00");
                String result1 = df.format(result);
                return ServerResponse.createBySuccess(result1);
            }
        }
        return null;
    }

    @Override
    public ServerResponse getEBook() {
        return ServerResponse.createBySuccess(eBookDao.selectAll());
    }


}
