package com.jaremo.test_mybatis.lesson1.mapper;

import com.jaremo.test_mybatis.lesson1.Account;

import java.util.List;

public interface TestMapper {
    List selectAll();

    List selectById(int aid);

    void deleteById(int aid);

    void insertAccount(Account account);

    void updateById(Account account);
}
