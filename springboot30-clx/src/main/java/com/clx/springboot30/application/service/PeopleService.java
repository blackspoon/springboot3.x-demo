package com.clx.springboot30.application.service;

import com.clx.springboot30.application.model.People;
import com.clx.springboot30.application.persistent.jpa.PeopleRepository;
import com.clx.springboot30.application.persistent.mapper.PeopleMapper;
import com.clx.springboot30.application.persistent.template.PeopleTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    private PeopleTemplate peopleTemplate;

    private PeopleMapper peopleMapper;

    private PeopleRepository peopleRepository;

    public PeopleService(PeopleTemplate peopleTemplate, PeopleMapper peopleMapper, PeopleRepository peopleRepository) {
        this.peopleTemplate = peopleTemplate;
        this.peopleMapper = peopleMapper;
        this.peopleRepository = peopleRepository;
    }

    //-------------------------------NamedParameterJdbcTemplate start-----------------------

    /**
     *
     * @param personId
     * @return
     */
    public People getPeople(int personId) {
        return peopleTemplate.getPeople(personId);
    }

    /**
     * getAllPeople
     * @return
     */
    public List<People> getAllPeople() {
        return peopleTemplate.getAllPeople();
    }

    /**
     * insert people
     * @return
     */
    public int insertPeople(People people) {
        return  peopleTemplate.insertPeople(people);
    }

    /**
     * update people
     * @return
     */
    public int updatePeople(People people) {
        return  peopleTemplate.updatePeople(people);
    }

    /**
     * delete people
     * @return
     */
    public int deletePeople(int personId) {
        return  peopleTemplate.deletePeople(personId);
    }
    //-------------------------------NamedParameterJdbcTemplate end-----------------------

    //-------------------------------mapper start-----------------------
    /**
     * getPeopleByPersonId
     * @return
     */
    public People getPeopleMapper(int personId) {
        return peopleMapper.getPeopleByPersonId(personId);
    }

    /**
     * getAllPeopleMapper
     * @return
     */
    public List<People> getAllPeopleMapper() {
        return peopleMapper.getPeople();
    }

    /**
     * insert people
     * @return
     */
    public int insertPeopleMapper(People people) {
        return  peopleMapper.save(people);
    }

    /**
     * update people
     * @return
     */
    public int updatePeopleMapper(People people) {
        return  peopleMapper.update(people);
    }

    /**
     * delete people
     * @return
     */
    public int deletePeopleMapper(int personId) {
        return  peopleMapper.delete(personId);
    }
    //-------------------------------mapper end-----------------------

    //-------------------------------jpa start-----------------------
    /**
     * getOne
     * @return
     */
    public People getOne(int personId) {
        return peopleRepository.getById(personId);
    }

    /**
     * findByFirstName
     * @return
     */
    public People findByFirstName(String firstName) {
        return peopleRepository.findByFirstName(firstName);
    }

    /**
     * findAll
     * @return
     */
    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    /**
     * queryByCondition1
     *
     * @return
     */
    public List<People> queryByCondition1(int personId) {
        return peopleRepository.queryByCondition1(personId);
    }

    /**
     * queryByCondition2
     *
     * @return
     */
    public List<People> queryByCondition2(int personId) {
        return peopleRepository.queryByCondition2(personId);
    }

    /**
     * saveOrUpdate
     * @return
     */
    public People saveOrUpdate(People people) {
        return peopleRepository.save(people);
    }

    /**
     * deleteOne
     * @return
     */
    public void deleteOne(int personId) {
        peopleRepository.deleteById(personId);
    }

    //-------------------------------jpa end-----------------------

}
