package com.clx.springboot30.application.persistent.mapper;

import com.clx.springboot30.application.model.SysUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select id,user_name from sys_user where id=#{id} ")
    @Results({
            @Result(column = "user_name", property = "userName")
    })
    SysUser find(@Param("id") String id);
}
