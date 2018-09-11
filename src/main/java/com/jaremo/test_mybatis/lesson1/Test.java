package com.jaremo.test_mybatis.lesson1;

import com.jaremo.test_mybatis.lesson1.mapper.TestMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class Test {

    private static Logger log = Logger.getLogger(Test.class);

    public static SqlSessionFactory getSqlSessionFactory() throws IOException{
        String resource = "mb.xml"; // 类路径下的mybatis的资源文件的路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // SqlSession的工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return  sqlSessionFactory;
    }

    public static void main(String[] args) throws IOException{

        SqlSessionFactory ssf = getSqlSessionFactory();
        // 从 SqlSessionFactory 中获取 SqlSession
        SqlSession sqlSession = ssf.openSession(); // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class); // 根据sqlsession获取mapper接口的实例
//        log.debug(testMapper.selectAll()); // 查询所有
//        log.debug(testMapper.selectById(1)); // 单条件查询
//        testMapper.deleteById(3); // 根据条件删除
//        Account account = new Account();
//        account.setAbalance("500");
//        account.setAid("5");
//        account.setAname("zl");
//        testMapper.insertAccount(account);
        Account account = new Account();
        account.setAid("5");
        account.setAname("彭永杰");
        testMapper.updateById(account);
        log.debug("success");
        sqlSession.commit();
    }
}
