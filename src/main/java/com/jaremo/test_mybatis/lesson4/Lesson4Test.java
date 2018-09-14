package com.jaremo.test_mybatis.lesson4;

import com.jaremo.test_mybatis.lesson4.domain.Person;
import com.jaremo.test_mybatis.lesson4.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Lesson4Test {
    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        String resource = "mb.xml";
        InputStream currentUser = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(currentUser);
        return sqlSessionFactory;
    }

    /*
        一级缓存是默认开启的(不能跨SqlSession的)
        在使用同一个SqlSession的情况下是一级缓存
        保证是一个SqlSession的前提下,在做同条件查询的sql语句时,第二次查询不在去数据库中查询
        不在同一个SqlSession中,第二个SqlSession不会去缓存中查询
     */
    @Test
    public void test() throws IOException{
        SqlSession session = getSqlSessionFactory().openSession();

        PersonMapper personMapper = session.getMapper(PersonMapper.class);
        Person person = personMapper.findByPid(6); // 第一个查完,将查询结果放入缓存中

        PersonMapper personMapper1 = session.getMapper(PersonMapper.class);
        Person person1 = personMapper.findByPid(6); // 第二次进行同条件查询时,直接从缓存中去查询
        System.out.println(person == person1);

        System.out.println("================================================");
        SqlSession session1 = getSqlSessionFactory().openSession();
        session1.getMapper(PersonMapper.class).findByPid(6);
    }

    /*
        为了解决不能SqlSeesion访问同样的缓存,出现了二级缓存,默认不开启
        在同一个SqlSessionFactory前提下,使用两个不同的SqlSession对象去执行相同查询条件的查询，
        第二次查询时不会再发送SQL语句，而是直接从缓存中取出数据
     */
    @Test
    public void test1() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        SqlSession session1 = sqlSessionFactory.openSession();

        PersonMapper personMapper = session.getMapper(PersonMapper.class);
        PersonMapper personMapper1 = session1.getMapper(PersonMapper.class);
        Person person = personMapper.findByPid(3);
        session.commit(); //
        System.out.println("===================================");
        Person person1 = personMapper1.findByPid(3);

        System.out.println(person == person1);
    }
}
