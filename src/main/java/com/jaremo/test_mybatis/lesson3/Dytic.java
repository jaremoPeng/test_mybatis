package com.jaremo.test_mybatis.lesson3;

import com.jaremo.test_mybatis.lesson3.domain.Person;
import com.jaremo.test_mybatis.lesson3.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Dytic {

    public SqlSession getSqlSession() throws IOException{
        String resource = "mb.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return  sqlSession;
    }

    @org.junit.Test
    public void test1() throws IOException{
        PersonMapper personMapper = getSqlSession().getMapper(PersonMapper.class);
        Person person = new Person();
//        person.setPname("赵");
        person.setAddress("区");
        List<Person> list = personMapper.findByPnameAndAddress(person);
        System.out.println(list);
    }

    @org.junit.Test
    public void test2() throws IOException{
        PersonMapper personMapper = getSqlSession().getMapper(PersonMapper.class);
        Integer sex = 0;
        List<Person> list = personMapper.findBySex(sex);
        System.out.println(list);
    }

    @org.junit.Test
    public void test3() throws IOException{
        PersonMapper personMapper = getSqlSession().getMapper(PersonMapper.class);
        Person person = new Person();
        person.setPname("赵小");
        person.setAddress("杭州田园");
        List<Person> list = personMapper.findByPerson(person);
        System.out.println(list);
    }

    @org.junit.Test
    public void test4() throws IOException{
        PersonMapper personMapper = getSqlSession().getMapper(PersonMapper.class);
        Person person = new Person();
        person.setPid(5);
        person.setPname("田七");
        person.setSex(1);
        personMapper.updateByPerson(person);
        List<Person> list = personMapper.findByPnameAndAddress(new Person());
        System.out.println(list);
    }

    @org.junit.Test
    public void test5() throws IOException{
        PersonMapper personMapper = getSqlSession().getMapper(PersonMapper.class);
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(3);
        List<Person> list = personMapper.findByPidList(arrayList);
        System.out.println(list);
    }
}
