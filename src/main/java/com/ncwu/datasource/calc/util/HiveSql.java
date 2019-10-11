package com.ncwu.datasource.calc.util;

/**
 * @Description:
 * @Author: yangdongwei
 * @CreateDate: 2019/4/30
 * @Version: 1.0
 */
public class HiveSql {

    // 基础数据展示模块
    // 累计外借图书册数
    public static String getTotalLentNum(String beginDate, String endDate){
        return "select count(*) from borrow_record_build " +
                "where to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\")";
    }

    // 各语种外借图书册数
    public static String getLanguageLentNum(String beginDate, String endDate){
        return "select language_type,count(*) from borrow_record_build" +
                " where to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") GROUP BY language_type";
    }

    // 新校区外借图书册数
    public static String getAddressLentNum(String beginDate, String endDate){
        return "select book_address,count(*) from borrow_record_build where" +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") group by book_address";
    }

    // 图书价值展示模块
    // 全馆最受欢迎前十名
    public static String getAllBookTopTen(String beginDate, String endDate){
        return "select book_name,count(book_name) as num from borrow_record_build where " +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\")  GROUP BY book_name ORDER BY num DESC limit 10";
    }

    // 各学院最受欢迎前五名
    public static String getDepartmentTopFive(String beginDate, String endDate, String dptmt){
        return "select book_name,count(book_name) as num from borrow_record_build where" +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") and department=\"" + dptmt + "\" GROUP BY book_name ORDER BY num DESC limit 5";
    }

    // 男女生最喜欢前三名
    public static String getTopThreeBySex(String beginDate, String endDate, Integer sex){
        return "select book_name,count(book_name) as num from borrow_record_build where" +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") and sex= " + sex + " GROUP BY book_name ORDER BY num DESC limit 3";
    }

    // 出勤率展示模块
    // 各学院出勤率排名
    public static String getAllDepartmentAttendance(String beginDate, String endDate){
        return "select department,count(*) as num from borrow_record_build where " +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") group by department ORDER BY num DESC";
    }

    // 学院各专业出勤率排名
    public static String getDptmtMajorAttendance(String beginDate, String endDate, String dptmt){
        return "select major,count(*) as num from borrow_record_build where " +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") and department=\"" + dptmt + "\" group by major ORDER BY num DESC";
    }
    // 全年阅读量最大的读者
    public static String getLentNumBigestReader(String beginDate, String endDate){
        return "select reader_name,count(*) as num from borrow_record_build where " +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") GROUP BY reader_name ORDER BY num DESC limit 1";
    }
    // 年级最受欢迎前五名
    public static String getGradeTopFive(String beginDate, String endDate, Integer grade){
        return "select book_name,count(book_name) as num from borrow_record_build where" +
                " to_date(lent_time) >= to_date(\""+beginDate+"\") and to_date(lent_time) <= to_date(\""+endDate+"\") and grade=" + grade + " GROUP BY book_name ORDER BY num DESC limit 5";
    }
    /*
    public static String (String beginDate, String endDate){
        return
    }
    public static String (String beginDate, String endDate){
        return
    }
    public static String (String beginDate, String endDate){
        return
    }
    public static String (String beginDate, String endDate){
        return
    }*/


}
