package com.jaremo.test_mybatis.lesson3.mapper;

import com.jaremo.test_mybatis.lesson3.domain.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface PersonMapper {
    // 内部类
    static class PersonProvider{
        public String findByPnameAndAddressSql(Map map){ // 这个方法是通过map传值,根据传入参数自动封装这个map
            Person person = (Person) map.get("person"); // 强转
            String sql = "select * from person where 1=1";
            if(person.getPname() != null){
                person.setPname("%"+person.getPname()+"%"); // 由于注解的形式不支持${} 只能用#{}传值
                sql += " and pname like #{person.pname}"; // 拼接sql语句
            }
            if(person.getAddress() != null){
                person.setAddress("%"+person.getAddress()+"%");
                sql += " and address like #{person.address}";
            }
            return sql; // 返回这个sql语句
        }
        // 上面这个方法的2.0 超级智能化
        public String findByPnameAndAddressSql2(Map map){
            Person person = (Person) map.get("person");
            SQL sql = new SQL();
            sql.SELECT("*").FROM("person");// "select * from person where 1=1"
            if(person.getPname() != null){
                person.setPname("%"+person.getPname()+"%");
                sql.WHERE("pname like #{person.pname}"); // " and pname like #{person.pname}"
            }
            if(person.getAddress() != null){
                person.setAddress("%"+person.getAddress()+"%");
                sql.AND(); // and
                sql.WHERE("address like #{person.address}"); //" and address like #{person.address}"
            }
            return sql.toString();
        }

        public String findBySexSql(Map map){
            Integer sex = (Integer)map.get("sex");
            SQL sql = new SQL();
            sql.SELECT("*").FROM("person");
            if(sex!=null){
                sql.WHERE("sex = #{sex}");
            }else{
                sql.WHERE("sex = 1");
            }
            return sql.toString();
        }

        public String updateByPersonSql(Map map){
            Person person = (Person)map.get("per");
            SQL sql = new SQL();
            sql.SELECT("*").FROM("person");
            if(person.getPname()!=null){
                sql.SET("pname = #{per.pname}");
            }
            if(person.getSex()!=null){
                sql.SET("sex = #{per.sex}");
            }
            if(person.getAge()!=null){
                sql.SET("age = #{per.age}");
            }
            sql.WHERE("pid = #{per.pid}");
            System.out.println(sql);
            return sql.toString();
        }

        public String findByPidListSql(Map map){
            List<Integer> pidList = (List<Integer>)map.get("pidList");
            SQL sql = new SQL();
            sql.SELECT("*").FROM("person");
            StringBuilder tempStr = new StringBuilder();
            if(pidList!=null && pidList.size()>0){
                for (Integer pid : pidList) {
                    tempStr.append(pid).append(",");
                }
            }
            if(tempStr.length()>0){
                tempStr.deleteCharAt(tempStr.length()-1);
                sql.WHERE("pid in ("+tempStr.toString()+")");
            }
            return sql.toString();
        }
    }

    @SelectProvider(type = PersonProvider.class, method = "findByPnameAndAddressSql2") // 只是接受指定类的指定方法返回的sql语句(字符串)
    List<Person> findByPnameAndAddress(@Param("person") Person person); // 设定别名,作key

    @SelectProvider(type = PersonProvider.class , method = "findBySexSql")
    List<Person> findBySex(@Param("sex") Integer sex);

    List<Person> findByPerson(@Param("per") Person person);

    @UpdateProvider(type = PersonProvider.class , method = "updateByPersonSql")
    void updateByPerson(@Param("per") Person person);

    @SelectProvider(type = PersonProvider.class , method = "findByPidListSql")
    List<Person> findByPidList(@Param("pidList") List<Integer> pidList);
}
