package com.clx.springboot30.application.persistent.mapper;

import com.clx.springboot30.application.model.People;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PeopleMapper {

    List<People> getPeople();

    People getPeopleByPersonId(int personId);

    int save(People people);

    int update(People people);

    int delete(int personId);

}
