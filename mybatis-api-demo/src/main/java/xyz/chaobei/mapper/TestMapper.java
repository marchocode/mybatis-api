package xyz.chaobei.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.chaobei.annotation.MybatisRequestMapping;

import java.util.List;

@Mapper
@MybatisRequestMapping("/test")
public interface TestMapper {


    @MybatisRequestMapping("/list")
    @Select("SELECT `User` FROM mysql.`user`")
    List<String> list();

}
