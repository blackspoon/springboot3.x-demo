package com.clx.springboot30.application.service;

import com.clx.springboot30.application.model.People;
import com.clx.springboot30.application.persistent.mapper.PeopleMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "people")
public class RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Resource
    private PeopleMapper peopleMapper;

    /**
     * insert people
     *
     * @return
     */
    public int insertPeopleMapper(People people) {
        return peopleMapper.save(people);
    }

    /**
     * getPeopleByPersonId
     *
     * @return
     */
    @Cacheable(key = "#personId", unless="#result == null")
    public People getPeopleMapper(int personId) {
        logger.info("not use cache");
        return peopleMapper.getPeopleByPersonId(personId);
    }

    /**
     * getAllPeopleMapper
     *
     * @return
     */
    @Cacheable
    public List<People> getAllPeopleMapper() {
        logger.info("not use cache");
        return peopleMapper.getPeople();
    }

    /**
     * update people
     *
     * @return
     */
    @CacheEvict(key = "#people.personId")
    public int updatePeopleMapper(People people) {
        return peopleMapper.update(people);
    }

    /**
     * delete people
     *
     * @return
     */
    @CacheEvict(key = "#id")
    public int deletePeopleMapper(int id) {
        return peopleMapper.delete(id);
    }
}
