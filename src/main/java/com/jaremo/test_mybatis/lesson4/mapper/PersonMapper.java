package com.jaremo.test_mybatis.lesson4.mapper;

import com.jaremo.test_mybatis.lesson4.domain.Person;

public interface PersonMapper {
    Person findByPid(int pid);
}
