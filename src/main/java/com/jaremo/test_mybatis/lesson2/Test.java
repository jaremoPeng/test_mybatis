package com.jaremo.test_mybatis.lesson2;

import com.jaremo.test_mybatis.lesson2.domain.Grade;
import com.jaremo.test_mybatis.lesson2.domain.Student;
import com.jaremo.test_mybatis.lesson2.mapper.GradeMapper;
import com.jaremo.test_mybatis.lesson2.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test{

    public SqlSession getSqlSession() throws IOException{
        String resource = "mb.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return  sqlSession;
    }

    @org.junit.Test
    public void test1() throws IOException{
        GradeMapper gradeMapper = getSqlSession().getMapper(GradeMapper.class);
        List<Grade> list = gradeMapper.selectAllGrade();
        for (Grade f:list) {
            System.out.println(f);
        }
    }

    @org.junit.Test
    public void test2() throws IOException{
        StudentMapper studentMapper = getSqlSession().getMapper(StudentMapper.class);
        Student student = studentMapper.selectById("1");
        System.out.println(student);
    }

    @org.junit.Test
    public void test3() throws IOException{
        GradeMapper gradeMapper = getSqlSession().getMapper(GradeMapper.class);
        Grade grade = gradeMapper.selectById("1");
        System.out.println(grade);
    }
}
