package com.clx.springboot30.application.persistent.mapper;

import com.clx.springboot30.application.model.UserScore;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserScoreMapper {

    @Select("select * from user_score")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_score", property="userScore", jdbcType=JdbcType.BIGINT),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<UserScore> select();

    @Insert("insert into user_score(user_id, user_score, name) values(#{userId},#{userScore},#{name})")
    int insert(UserScore record);
}
