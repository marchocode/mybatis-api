package xyz.chaobei.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {

    @Select("SELECT `User` FROM mysql.`user`")
    List<String> list();

}
