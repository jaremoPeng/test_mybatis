package com.jaremo.test_mybatis.lesson2.mapper;

import com.jaremo.test_mybatis.lesson2.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    /*
         column 传参的
         property 需要赋值实体类的属性
         one 表示不是集合... 是单一的记录
     */
    @Results({
            @Result(column = "gid" , property = "grade" , one = @One(select = "com.jaremo.test_mybatis.lesson2.mapper.GradeMapper.selectById"))
    })
    @Select("select * from student where sid=#{sid}")
    Student selectById(String sid);

    /**
     * 根据班级id,查询出属于该班级的学生
     * @param gid
     * @return
     */
    // Student selectByGid(String gid); xml文件用它
    @Select("select * from student where gid=#{gid}")
    List<Student> selectByGid(String gid); // 注解用它

}
