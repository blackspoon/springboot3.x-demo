package com.clx.springboot30.application.controller;

import com.clx.springboot30.application.model.People;
import com.clx.springboot30.application.service.RankingService;
import com.clx.springboot30.application.service.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RankingService rankingService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //-------------------------------DB Cache Start-----------------------------------------------------
    /**
     * http://localhost:3100/insertPeopleRedis?personId=30&firstName=wang&lastName=zai
     *
     * @param people
     * @return
     */
    @RequestMapping("/insertPeopleRedis")
    public String insertPeopleRedis(People people) {
        int result = redisService.insertPeopleMapper(people);
        if (result > 0) {
            return "insert success!";
        } else {
            return "no data insert!";
        }
    }

    /**
     * 第一次当redis没数据时从db取，第二次之后就从redis缓存中取得了，service里有log标识
     * http://localhost:3100/getPeopleRedis?personId=30
     *
     * @param personId
     * @return
     */
    @RequestMapping("/getPeopleRedis")
    public People getPeopleRedis(int personId) {
        return redisService.getPeopleMapper(personId);
    }

    /**
     * http://localhost:3100/updatePeopleRedis?personId=30&lastName=niu
     *
     * @param people
     * @return
     */
    @RequestMapping("/updatePeopleRedis")
    public String updatePeopleRedis(People people) {
        int result = redisService.updatePeopleMapper(people);
        if (result > 0) {
            return "update success!";
        } else {
            return "no data update!";
        }
    }

    /**
     * http://localhost:3100/getAllPeopleRedis
     *
     * @return
     */
    @RequestMapping("/getAllPeopleRedis")
    public List<People> getAllPeopleRedis() {
        return redisService.getAllPeopleMapper();
    }

    /**
     * http://localhost:3100/deletePeopleRedis?personId=30
     *
     * @param personId
     * @return
     */
    @RequestMapping("/deletePeopleRedis")
    public String deletePeopleRedis(int personId) {
        int result = redisService.deletePeopleMapper(personId);
        if (result > 0) {
            return "delete success!";
        } else {
            return "no data delete!";
        }
    }
    //-------------------------------DB Cache End-----------------------------------------------------

    //-------------------------------Session Cache Start---------------------------------------------------

    /**
     * 项目启动时，设置了20s过期
     * http://localhost:3100/setSession
     * @param request
     * @return
     */
    @RequestMapping(value = "/setSession")
    public Map<String, Object> setSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request_url", request.getRequestURL());
        map.put("request_url", request.getRequestURL());
        return map;
    }

    /**
     * http://localhost:3100/getSession
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSession")
    public Object getSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionIdUrl",request.getSession().getAttribute("request_url"));
        map.put("sessionId", request.getSession().getId());
        return map;
    }
    //-------------------------------Session Cache End-----------------------------------------------------

    //-------------------------------rank 不整合DB Start---------------------------------------------------
    /**
     * 添加积分数据(覆盖)
     * http://localhost:3100/addScore?uid=1&score=10
     * http://localhost:3100/getScore?uid=1
     * @param uid
     * @param score
     * @return
     */
    @RequestMapping(value = "/addScore")
    public String addScore(String uid, Integer score) {
        rankingService.rankAdd(uid, score);
        return "success";
    }

    /**
     * 添加积分数据(累加)
     * http://localhost:3100/increScore?uid=1&score=30
     * http://localhost:3100/increScore?uid=2&score=50
     * http://localhost:3100/increScore?uid=3&score=10
     * http://localhost:3100/increScore?uid=4&score=20
     * http://localhost:3100/increScore?uid=5&score=100
     *
     * http://localhost:3100/getScore?uid=1
     * @param uid
     * @param score
     * @return
     */
    @RequestMapping(value = "/increScore")
    public String increScore(String uid, Integer score) {
        rankingService.increSocre(uid, score);
        return "success";
    }

    /**
     * 获取某个用户分数
     * http://localhost:3100/getScore?uid=1
     * @param uid
     * @return
     */
    @RequestMapping(value = "/getScore")
    public Long getScore(String uid) {
        return rankingService.score(uid);
    }

    /**
     * 获取某个用户排行详情
     * http://localhost:3100/rank?uid=1
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/rank")
    public Map<String, Long> rank(String uid) {
        Map<String, Long> map = new HashMap<>();
        map.put(uid, rankingService.rankNum(uid));
        return map;
    }

    /**
     * 获取排行榜
     * http://localhost:3100/scoreByRange?start=0&end=10
     * @param start
     * @param end
     * @return
     */
    @ResponseBody
    @RequestMapping("/scoreByRange")
    public Set<ZSetOperations.TypedTuple<Object>> scoreByRange(Integer start, Integer end) {
        return rankingService.rankWithScore(start,end);
    }
    //-------------------------------rank 不整合DB End---------------------------------------------------

    //---------------------rank 整合DB start-------------------------------------

    /**
     * 添加积分数据
     * http://localhost:3100/sale/increScore?uid=11&score=109
     * http://localhost:3100/sale/increScore?uid=22&score=90
     * http://localhost:3100/sale/increScore?uid=33&score=105
     * http://localhost:3100/sale/increScore?uid=44&score=70
     * http://localhost:3100/sale/increScore?uid=55&score=50
     * http://localhost:3100/sale/increScore?uid=66&score=200
     *
     * @param uid
     * @param score
     * @return
     */
    @ResponseBody
    @RequestMapping("/sale/increScore")
    public String increSaleScore(String uid, Integer score) {
        rankingService.increSaleSocre(uid, score);
        return "success";
    }

    /**
     * 获取用户积分详细信息
     * http://localhost:3100/sale/userScore?uid=11&name=A
     * @param uid
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/sale/userScore")
    public Map<String,Object> userScore(String uid,String name) {
        return rankingService.userRank(uid,name);
    }

    /**
     * 从大到小排行榜
     * http://localhost:3100/sale/top?start=0&end=10
     * @param start
     * @param end
     * @return
     */
    @ResponseBody
    @RequestMapping("/sale/top")
    public List<Map<String,Object>> reverseZRankWithRank(long start, long end) {
        return rankingService.reverseZRankWithRank(start,end);
    }
    //---------------------rank 整合DB end-------------------------------------

    //---------------------订阅发布 start-------------------------------------

    /**
     * http://localhost:3100/redisSend
     *
     * @return
     */
    @RequestMapping("/redisSend")
    public String sendMessage() {
        for(int i = 1; i <= 5; i++) {
            stringRedisTemplate.convertAndSend("channel:clx_test", String.format("我是消息{%d}号: %tT", i, new Date()));
        }
        return "success";
    }
    //---------------------订阅发布 end-------------------------------------
}
