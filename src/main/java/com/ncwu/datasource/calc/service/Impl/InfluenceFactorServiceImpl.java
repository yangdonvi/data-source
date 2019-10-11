package com.ncwu.datasource.calc.service.Impl;

import com.ncwu.datasource.calc.dao.InfluenceFactorDao;
import com.ncwu.datasource.calc.entity.BaseData;
import com.ncwu.datasource.calc.entity.InfluenceFactor;
import com.ncwu.datasource.calc.service.BaseDataService;
import com.ncwu.datasource.calc.service.InfluenceFactorService;
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
 * Created by Donvi Yang on 2019/5/3.
 */
@Service
public class InfluenceFactorServiceImpl implements InfluenceFactorService {

    @Autowired
    InfluenceFactorDao influenceFactorDao;
    @Autowired
    BaseDataService baseDataService;

    @Override
    public ServerResponse getMonthNum(Integer begin, Integer end) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<InfluenceFactor> result = new ArrayList<>();
        List<InfluenceFactor> influenceFactors = new ArrayList<>();
        influenceFactors = influenceFactorDao.selectInfluenceFactor();
        for (int i = begin; i <= end; i++) {
            // 标记此月份是否已经放入返回集
            boolean flag = true;
            for (InfluenceFactor influenceFactor : influenceFactors) {
                if (influenceFactor.getMonth() != null && influenceFactor.getMonth() == i) {
                    flag = false;
                    result.add(influenceFactor);
                    continue;
                }
            }
            if (flag) {
                // 说明之前数据库中没有当月记录，要查
                System.out.println("正在查询第" + i + "月");
                ServerResponse serverResponse = new ServerResponse();
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                        serverResponse = baseDataService.getTotalLentNum("2018-0" + String.valueOf(i) + "-01", "2018-0" + String.valueOf(i) + "-31");
                        break;
                    case 10:
                    case 12:
                        serverResponse = baseDataService.getTotalLentNum("2018-" + String.valueOf(i) + "-01", "2018-" + String.valueOf(i) + "-31");
                        break;
                    case 4:
                    case 6:
                    case 9:
                        serverResponse = baseDataService.getTotalLentNum("2018-0" + String.valueOf(i) + "-01", "2018-0" + String.valueOf(i) + "-30");
                        break;
                    case 11:
                        serverResponse = baseDataService.getTotalLentNum("2018-" + String.valueOf(i) + "-01", "2018-" + String.valueOf(i) + "-30");
                        break;
                    case 2:
                        serverResponse = baseDataService.getTotalLentNum("2018-0" + String.valueOf(i) + "-01", "2018-0" + String.valueOf(i) + "-28");
                        break;
                }
                BaseData baseData = (BaseData) serverResponse.getData();
                InfluenceFactor influenceFactor = new InfluenceFactor();
                influenceFactor.setTotalNum(baseData.getTotalOutBookNum());
                influenceFactor.setMonth(i);
                influenceFactorDao.insertInfluenceFactor(influenceFactor);
                result.add(influenceFactor);
            }
        }

        return ServerResponse.createBySuccess(result);
    }

    @Override
    public ServerResponse getGradeTopFive(String beginDate, String endDate) throws SQLException, ClassNotFoundException {
        // 先查库,库中有记录，直接返回
        List<InfluenceFactor> influenceFactors = influenceFactorDao.getGradeTopFive(beginDate, endDate);
        if (influenceFactors != null && influenceFactors.size() != 0) {
            return ServerResponse.createBySuccess(influenceFactors);
        }
        // 库中没有则运行mapreduce
        List<InfluenceFactor> result = new ArrayList<>();
        // 获取连接
        Connection conn = HiveClientUtils.getConn();
        Statement stmt = conn.createStatement();
        for (int i = 0 ; i < 4 ; i++){
            // 获取sql
            String sql = HiveSql.getGradeTopFive(beginDate, endDate, i);
            System.out.println("Running:" + sql);
            // 执行sql
            ResultSet res = stmt.executeQuery(sql);
            int flag = 1;
            while (res.next()) {
                InfluenceFactor influenceFactor = new InfluenceFactor();
                influenceFactor.setGrade(i);
                influenceFactor.setBookName(res.getString(1));
                influenceFactor.setTotalNum(res.getInt(2));
                influenceFactor.setSortNum(flag);
                influenceFactor.setBeginDate(beginDate);
                influenceFactor.setEndDate(endDate);
                influenceFactorDao.insertInfluenceFactor(influenceFactor);
                result.add(influenceFactor);
                flag++;
            }
        }
        // 关闭连接
        HiveClientUtils.closeConn(conn, stmt);
        return ServerResponse.createBySuccess(result);
    }
}
