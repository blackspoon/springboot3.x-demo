package com.clx.springboot30.application.controller;

import com.clx.springboot30.application.model.People;
import com.clx.springboot30.application.service.PeopleService;
//import com.github.pagehelper.PageHelper;
import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressResult;
import com.taobao.stresstester.core.StressTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleController {

    private PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    //-------------------------------NamedParameterJdbcTemplate start-----------------------

    /**
     * http://localhost:3100/getPeople?personId=10
     * @param personId
     * @return
     */
    @RequestMapping("/getPeople")
    public People getPeople(int personId) {
        return peopleService.getPeople(personId);
    }

    /**
     * http://localhost:3100/getAllPeople
     *
     * @return
     */
    @RequestMapping("/getAllPeople")
    public List<People> getAllPeople() {
        // 并发数
        int concurrencyLevel =100;
        // 总请求数
        int totalRequest = 1000;
        StressResult result = StressTestUtils.test(concurrencyLevel, totalRequest, new StressTask() {
            @Override
            public Object doTask() throws Exception {
                return peopleService.getAllPeople();
            }
        });
        System.out.println(StressTestUtils.format(result));
        return peopleService.getAllPeople();
    }

    /**
     * http://localhost:3100/insertPeople?personId=10&firstName=xiang&lastName=hui
     *
     * @param people
     * @return
     */
    @RequestMapping("/insertPeople")
    public String insertPeople(People people) {
        int result = peopleService.insertPeople(people);
        if (result > 0) {
            return "insert success!";
        } else {
            return "no data insert!";
        }
    }

    /**
     * http://localhost:3100/updatePeople?personId=10&lastName=yu
     *
     * @param people
     * @return
     */
    @RequestMapping("/updatePeople")
    public String updatePeople(People people) {
        int result = peopleService.updatePeople(people);
        if (result > 0) {
            return "update success!";
        } else {
            return "no data update!";
        }
    }

    /**
     * http://localhost:3100/deletePeople?personId=10
     *
     * @param personId
     * @return
     */
    @RequestMapping("/deletePeople")
    public String deletePeople(int personId) {
        int result = peopleService.deletePeople(personId);
        if (result > 0) {
            return "delete success!";
        } else {
            return "no data delete!";
        }
    }
    //-------------------------------NamedParameterJdbcTemplate end-----------------------

    //-------------------------------mapper start-----------------------
    /**
     * http://localhost:3100/getPeopleMapper?personId=9
     *
     * @param personId
     * @return
     */
    @RequestMapping("/getPeopleMapper")
    public People getPeopleMapper(int personId) {
        return peopleService.getPeopleMapper(personId);
    }

    /**
     * http://localhost:3100/getAllPeopleMapper
     *
     * @return
     */
    @RequestMapping("/getAllPeopleMapper")
    public List<People> getAllPeopleMapper() {
        // 分页,使用时打开即可
    //    PageHelper.startPage(1,3);
        return peopleService.getAllPeopleMapper();
    }

    /**
     * http://localhost:3100/insertPeopleMapper?personId=9&firstName=han&lastName=meihui
     *
     * @param people
     * @return
     */
    @RequestMapping("/insertPeopleMapper")
    public String insertPeopleMapper(People people) {
        int result = peopleService.insertPeopleMapper(people);
        if (result > 0) {
            return "insert success!";
        } else {
            return "no data insert!";
        }
    }

    /**
     * http://localhost:3100/updatePeopleMapper?personId=9&lastName=meimei
     *
     * @param people
     * @return
     */
    @RequestMapping("/updatePeopleMapper")
    public String updatePeopleMapper(People people) {
        int result = peopleService.updatePeopleMapper(people);
        if (result > 0) {
            return "update success!";
        } else {
            return "no data update!";
        }
    }

    /**
     * http://localhost:3100/deletePeopleMapper?personId=9
     *
     * @param personId
     * @return
     */
    @RequestMapping("/deletePeopleMapper")
    public String deletePeopleMapper(int personId) {
        int result = peopleService.deletePeopleMapper(personId);
        if (result > 0) {
            return "delete success!";
        } else {
            return "no data delete!";
        }
    }
    //-------------------------------mapper end-----------------------

    //-------------------------------jpa start-----------------------
    /**
     * http://localhost:3100/getOneJpa?personId=9
     *
     * @param personId
     * @return
     */
    @RequestMapping("/getOneJpa")
    public People getOneJpa(int personId) {
        return peopleService.getOne(personId);
    }

    /**
     * http://localhost:3100/findByFirstNameJpa?firstName=chi
     *
     * @param firstName
     * @return
     */
    @RequestMapping("/findByFirstNameJpa")
    public People findByFirstNameJpa(String firstName) {
        return peopleService.findByFirstName(firstName);
    }

    /**
     * http://localhost:3100/findAllJpa
     *
     * @return
     */
    @RequestMapping("/findAllJpa")
    public List<People> findAllJpa() {
        return peopleService.findAll();
    }

    /**
     * http://localhost:3100/queryByCondition1Jpa?personId=20
     *
     * @return
     */
    @RequestMapping("/queryByCondition1Jpa")
    public List<People> queryByCondition1Jpa(int personId) {
        return peopleService.queryByCondition1(personId);
    }

    /**
     * http://localhost:3100/queryByCondition2Jpa?personId=20
     *
     * @return
     */
    @RequestMapping("/queryByCondition2Jpa")
    public List<People> queryByCondition2Jpa(int personId) {
        return peopleService.queryByCondition2(personId);
    }

    /**
     * http://localhost:3100/saveOrUpdateJpa?personId=20&firstName=tian&lastName=cai
     *
     * @param people
     * @return
     */
    @RequestMapping("/saveOrUpdateJpa")
    public People saveOrUpdateJpa(People people) {
        return peopleService.saveOrUpdate(people);
    }

    /**
     * http://localhost:3100/deleteOneJpa?personId=20
     *
     * @param personId
     * @return
     */
    @RequestMapping("/deleteOneJpa")
    public String deleteOneJpa(int personId) {
        peopleService.deleteOne(personId);
        return "success";
    }
    //-------------------------------jpa end-----------------------
}
