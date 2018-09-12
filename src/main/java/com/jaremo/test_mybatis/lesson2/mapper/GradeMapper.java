package com.jaremo.test_mybatis.lesson2.mapper;

import com.jaremo.test_mybatis.lesson2.domain.Grade;
import com.jaremo.test_mybatis.lesson2.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 测试结果集的映射
public interface GradeMapper {

    List<Grade> selectAllGrade();

    /*
        id 值为true的话,说明是主键
        javaType 返回值类型  类.class
        many 返回值是集合....
     */
    @Results({
            @Result(id = true , column = "gid" , property = "gid"),
            @Result(column = "gname" , property = "gname1"),
            @Result(property = "studentList" , javaType = List.class , column = "gid" ,
                    many = @Many(select = "com.jaremo.test_mybatis.lesson2.mapper.StudentMapper.selectByGid"))
    })
    @Select("select * from grade where gid=#{gid}")
    Grade selectById(String gid);


}
