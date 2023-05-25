package com.clx.springboot30.application.persistent.jpa;

import com.clx.springboot30.application.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PeopleRepository extends JpaSpecificationExecutor<People>,JpaRepository<People, Integer> {

    // 方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
    People findByFirstName(String firstName);

    // 复杂逻辑可以写原生sql
    @Query(value = "select * from people where person_id < ?1", nativeQuery = true)
    List<People> queryByCondition1(int personId);

    // 注意，这里的People是对象，不是表名,JPQL方式
    @Query("from People where personId < ?1")
    List<People> queryByCondition2(int personId);

}
