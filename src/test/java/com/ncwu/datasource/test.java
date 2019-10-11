package com.ncwu.datasource;

import com.alibaba.fastjson.JSONObject;
import com.ncwu.datasource.calc.entity.ReaderNum;
import com.ncwu.datasource.dao.BorrowRecordDao;
import com.ncwu.datasource.dao.ReaderNumDao;
import com.ncwu.datasource.dao.UserDao;
import com.ncwu.datasource.entity.BorrowRecord;
import com.ncwu.datasource.entity.BorrowRecordBuild;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import java.util.Calendar;

/**
 * @Description:
 * @Author: yangdongwei
 * @CreateDate: 2019/3/20
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BorrowRecordDao borrowRecordDao;
    @Autowired
    private ReaderNumDao readerNumDao;

    @Test
    public void test01() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }

    @Test
    public void test02() {
        List<BorrowRecord> borrowRecords = borrowRecordDao.selectAllRecord();
        System.out.println(borrowRecords.size());
    }

    // 处理数据
    @Test
    public void data_build() {

        // 获取加工前数据
        List<BorrowRecord> borrowRecords = borrowRecordDao.selectAllRecord();

        Random random = new Random();

        // 学院
        String departments[] = {"水利学院", "材料学院", "土木与交通学院", "电力学院",
                "机械学院", "环境与市政工程学院", "管理与经济学院", "数学与统计学院",
                "建筑学院", "信息工程学院", "物理与电子学院", "外国语学院",
                "法学院", "艺术与设计学院"};

        // 专业
        String[][] majors = new String[14][];
        // 水利学院
        majors[0] = new String[]{"水利实验中心", "农业水利工程系", "水利水电工程系", "水文与水资源系", "港口航道与治河工程系", "工程管理系", "水务工程系"};
        // 材料学院
        majors[1] = new String[]{"材料加工工程系", "材料学系"};
        // 土木与交通学院
        majors[2] = new String[]{"力学系", "土建工程系", "材料工程系", "交通工程系"};
        // 电力学院
        majors[3] = new String[]{"能源与动力工程（热动）", "能源与动力工程（水动）", "电气工程及其自动化", "自动化", "电子科学与技术", "核工程与核技术", "轨道交通信号与控制"};
        // 机械学院
        majors[4] = new String[]{"机械设计制造系", "机电与测控仪器系", "车辆与交通运输系", "机械基础系"};
        // 环境与市政工程学院
        majors[5] = new String[]{"给排水科学与工程系", "建筑环境与能源应用工程系", "环境工程系", "消防工程系", "应用化学系"};
        // 管理与经济学院
        majors[6] = new String[]{"经济贸易系", "会计学系", "市场营销系", "物流与工业工程系", "信息管理系"};
        // 数学与统计学院
        majors[7] = new String[]{"信息与计算科学系", "应用数学系", "统计学系", "应用统计学系", "金融数学系"};
        // 建筑学院
        majors[8] = new String[]{"设计基础系", "建筑设计系", "城乡规划系", "城市设计系", "风景园林系", "历史与技术系"};
        // 信息工程学院
        majors[9] = new String[]{"计算机科学与技术系", "网络工程系", "数字媒体系", "软件工程系"};
        // 物理与电子学院
        majors[10] = new String[]{"电子信息系", "通信工程系", "电子科学与技术系"};
        // 外国语学院
        majors[11] = new String[]{"汉语国际教育系", "英语系"};
        // 法学院
        majors[12] = new String[]{"法学系", "水法与水政系"};
        // 艺术与设计学院
        majors[13] = new String[]{"环境设计系", "视觉传达系", "公共艺术系", "美术系"};

        for (BorrowRecord borrowRecord : borrowRecords) {
            BorrowRecordBuild borrowRecordBuild = JSONObject.parseObject(JSONObject.toJSONString(borrowRecord), BorrowRecordBuild.class);

            // 设置男女比例3：1
            // 取[0，3]区间的随机数
            int sexFlag = random.nextInt(4);
            if (sexFlag <= 2) {
                // 随机数为0，1，2，男
                borrowRecordBuild.setSex(0);
            } else {
                // 随机数为3，女
                borrowRecordBuild.setSex(1);
            }


            // 教工不设置学院、专业、年级属性
            if (borrowRecord.getReaderId().length() > 6) {
                // 根据1，2，3三个等级分配学院人数
                // 取[0,28]区间的随机数
                int department = random.nextInt(29);
                // 根据随机数分配学院、专业
                switch (department) {
                    // 水利学院
                    case 0:
                    case 1:
                    case 2:
                        borrowRecordBuild.setDepartment(departments[0]);
                        borrowRecordBuild.setMajor(majors[0][random.nextInt(majors[0].length)]);
                        break;
                    // 材料学院
                    case 3:
                        borrowRecordBuild.setDepartment(departments[1]);
                        borrowRecordBuild.setMajor(majors[1][random.nextInt(majors[1].length)]);
                        break;
                    // 土木与交通学院
                    case 4:
                    case 5:
                        borrowRecordBuild.setDepartment(departments[2]);
                        borrowRecordBuild.setMajor(majors[2][random.nextInt(majors[2].length)]);
                        break;
                    // 电力学院
                    case 6:
                    case 7:
                    case 8:
                        borrowRecordBuild.setDepartment(departments[3]);
                        borrowRecordBuild.setMajor(majors[3][random.nextInt(majors[3].length)]);
                        break;
                    // 机械学院
                    case 9:
                    case 10:
                    case 11:
                        borrowRecordBuild.setDepartment(departments[4]);
                        borrowRecordBuild.setMajor(majors[4][random.nextInt(majors[4].length)]);
                        break;
                    // 环境与市政工程学院
                    case 12:
                    case 13:
                        borrowRecordBuild.setDepartment(departments[5]);
                        borrowRecordBuild.setMajor(majors[5][random.nextInt(majors[5].length)]);
                        break;
                    // 管理与经济学院
                    case 14:
                    case 15:
                        borrowRecordBuild.setDepartment(departments[6]);
                        borrowRecordBuild.setMajor(majors[6][random.nextInt(majors[6].length)]);
                        break;
                    // 数学与统计学院
                    case 16:
                    case 17:
                        borrowRecordBuild.setDepartment(departments[7]);
                        borrowRecordBuild.setMajor(majors[7][random.nextInt(majors[7].length)]);
                        break;
                    // 建筑学院
                    case 18:
                    case 19:
                    case 20:
                        borrowRecordBuild.setDepartment(departments[8]);
                        borrowRecordBuild.setMajor(majors[8][random.nextInt(majors[8].length)]);
                        break;
                    // 信息工程学院
                    case 21:
                    case 22:
                        borrowRecordBuild.setDepartment(departments[9]);
                        borrowRecordBuild.setMajor(majors[9][random.nextInt(majors[9].length)]);
                        break;
                    // 物理与电子学院
                    case 23:
                    case 24:
                        borrowRecordBuild.setDepartment(departments[10]);
                        borrowRecordBuild.setMajor(majors[10][random.nextInt(majors[10].length)]);
                        break;
                    // 外国语学院
                    case 25:
                        borrowRecordBuild.setDepartment(departments[11]);
                        borrowRecordBuild.setMajor(majors[11][random.nextInt(majors[11].length)]);
                        break;
                    // 法学院
                    case 26:
                        borrowRecordBuild.setDepartment(departments[12]);
                        borrowRecordBuild.setMajor(majors[12][random.nextInt(majors[12].length)]);
                        break;
                    // 艺术与设计学院
                    case 27:
                    case 28:
                        borrowRecordBuild.setDepartment(departments[13]);
                        borrowRecordBuild.setMajor(majors[13][random.nextInt(majors[13].length)]);
                        break;
                }

                // 随机年级
                borrowRecordBuild.setGrade(random.nextInt(4));
            }
            borrowRecordDao.insertBorrowRecordBuild(borrowRecordBuild);
        }
    }

    @Test
    public void insertReaderNum() {
        // 学院
        String departments[] = {"水利学院", "材料学院", "土木与交通学院", "电力学院",
                "机械学院", "环境与市政工程学院", "管理与经济学院", "数学与统计学院",
                "建筑学院", "信息工程学院", "物理与电子学院", "外国语学院",
                "法学院", "艺术与设计学院"};

        // 专业
        String[][] majors = new String[14][];
        // 水利学院
        majors[0] = new String[]{"水利实验中心", "农业水利工程系", "水利水电工程系", "水文与水资源系", "港口航道与治河工程系", "工程管理系", "水务工程系"};
        // 材料学院
        majors[1] = new String[]{"材料加工工程系", "材料学系"};
        // 土木与交通学院
        majors[2] = new String[]{"力学系", "土建工程系", "材料工程系", "交通工程系"};
        // 电力学院
        majors[3] = new String[]{"能源与动力工程（热动）", "能源与动力工程（水动）", "电气工程及其自动化", "自动化", "电子科学与技术", "核工程与核技术", "轨道交通信号与控制"};
        // 机械学院
        majors[4] = new String[]{"机械设计制造系", "机电与测控仪器系", "车辆与交通运输系", "机械基础系"};
        // 环境与市政工程学院
        majors[5] = new String[]{"给排水科学与工程系", "建筑环境与能源应用工程系", "环境工程系", "消防工程系", "应用化学系"};
        // 管理与经济学院
        majors[6] = new String[]{"经济贸易系", "会计学系", "市场营销系", "物流与工业工程系", "信息管理系"};
        // 数学与统计学院
        majors[7] = new String[]{"信息与计算科学系", "应用数学系", "统计学系", "应用统计学系", "金融数学系"};
        // 建筑学院
        majors[8] = new String[]{"设计基础系", "建筑设计系", "城乡规划系", "城市设计系", "风景园林系", "历史与技术系"};
        // 信息工程学院
        majors[9] = new String[]{"计算机科学与技术系", "网络工程系", "数字媒体系", "软件工程系"};
        // 物理与电子学院
        majors[10] = new String[]{"电子信息系", "通信工程系", "电子科学与技术系"};
        // 外国语学院
        majors[11] = new String[]{"汉语国际教育系", "英语系"};
        // 法学院
        majors[12] = new String[]{"法学系", "水法与水政系"};
        // 艺术与设计学院
        majors[13] = new String[]{"环境设计系", "视觉传达系", "公共艺术系", "美术系"};

        // 每个专业的人口基数
        Integer baseNum = 350;

        // 写入学生信息后，记得要写入职工人数，初步定为800人
        // 还要记得写全校人数，共21800
        for (int i = 0; i < departments.length; i++) {
            ReaderNum departNum = new ReaderNum();
            departNum.setDepartName(departments[i]);
            departNum.setDepartmentNum(majors[i].length * baseNum);
            readerNumDao.insert(departNum);
            System.out.println("第" + (i + 1) + "个" + departments[i] + "学院已经插入");
            for (int j = 0; j < majors[i].length; j++) {
                ReaderNum majorNum = new ReaderNum();
                majorNum.setDepartName(majors[i][j]);
                majorNum.setMajorNum(baseNum);
                readerNumDao.insert(majorNum);
                System.out.println("第" + (j + 1) + "个" + "已经插入" + departments[i] + "学院的" + majors[i][j] + "专业");
            }
        }
    }

    @Test
    public void test001(){
        System.out.println(Calendar.getInstance().get(Calendar.YEAR) - 1);
    }


}
