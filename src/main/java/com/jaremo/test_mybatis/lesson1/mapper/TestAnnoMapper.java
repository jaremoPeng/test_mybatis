package com.jaremo.test_mybatis.lesson1.mapper;

import com.jaremo.test_mybatis.lesson1.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface TestAnnoMapper {
    // 使用mybatis注解形式

    @Select("select * from account")
    List<Map> selectAll();

    @Select("select * from account where aid=#{aid}")
    List<Account> selectById(int aid); // 假如不使用@Param 则传参的  下标或者param1,param2... 使用注解就可以使用自定义的名字传参

    @Delete("delete from account where aid=#{aid}")
    void deleteById(@Param("aid") int aid);

//    @SelectKey(before = true,keyProperty = "key-name",resultType = int.class ,statement = "sql语句")  查询主键
    @Insert("insert into account(aid , aname , abalance) values (#{aid},#{aname},#{abalance})")
    void insertAccount(Account account);

    @Update("update account set aname=#{aname} where aid=#{aid}")
    void updateById(Account account);
}
