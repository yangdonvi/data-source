package com.ncwu.datasource.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: yangdongwei
 * @CreateDate: 2019/3/20
 * @Version: 1.0
 */
@Repository("userDao")
@Mapper
public interface UserDao {
    String selectAgeByName (@Param("name") String name);

}
