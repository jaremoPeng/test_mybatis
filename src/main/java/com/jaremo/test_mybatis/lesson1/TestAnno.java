package com.jaremo.test_mybatis.lesson1;

import com.jaremo.test_mybatis.lesson1.mapper.TestAnnoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestAnno {

    private static Logger logger = Logger.getLogger(TestAnno.class);
    public static SqlSessionFactory getSqlSessionFactory() throws IOException{
        String resource = "mb.xml";
        InputStream build = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(build);
        return  sqlSessionFactory;
    }

    public static void main(String[] args) throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TestAnnoMapper testAnnoMapper = sqlSession.getMapper(TestAnnoMapper.class);
//        logger.debug(testAnnoMapper.selectAll());
        logger.debug(testAnnoMapper.selectById(5));
//        Account account = new Account();
//        account.setAname("zl");
//        account.setAid("6");
//        account.setAbalance("1000");
//        testAnnoMapper.insertAccount(account);
        sqlSession.commit();
    }
}
