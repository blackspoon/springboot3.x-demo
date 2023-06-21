package com.clx.springboot30.application.service;

import com.clx.springboot30.application.model.SysUser;
import com.clx.springboot30.application.model.UserScore;
import com.clx.springboot30.application.persistent.mapper.UserMapper;
import com.clx.springboot30.application.persistent.mapper.UserScoreMapper;
import com.clx.springboot30.component.RedisManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class RankingService implements InitializingBean {

    private static final String RANK_NAME = "user_score";

    private static final String SALE_SCORE = "sale_score_rank:";

    private RedisManager redisManager;

    private UserMapper userMapper;

    private UserScoreMapper userScoreMapper;

    public RankingService(RedisManager redisManager, UserMapper userMapper, UserScoreMapper userScoreMapper) {
        this.redisManager = redisManager;
        this.userMapper = userMapper;
        this.userScoreMapper = userScoreMapper;
    }

    //---------------------test redis start-------------------------------------

    public void rankAdd(String uid, Integer score) {
        redisManager.zAdd(RANK_NAME, uid, score);
    }

    public void increSocre(String uid, Integer score) {
        redisManager.incrementScore(RANK_NAME, uid, score);
    }

    public Long rankNum(String uid) {
        return redisManager.zRank(RANK_NAME, uid);
    }

    public Long score(String uid) {
        Long score = redisManager.zSetScore(RANK_NAME, uid).longValue();
        return score;
    }

    public Set<ZSetOperations.TypedTuple<Object>> rankWithScore(Integer start, Integer end) {
        return redisManager.zRankWithScore(RANK_NAME, start, end);
    }
    //---------------------test redis end-------------------------------------

    //---------------------redis start-------------------------------------

    /**
     * add data from mysql to redis
     */
    public void rankSaleAdd() {
        List<UserScore> userScores = userScoreMapper.select();
        userScores.forEach(userScore -> {
            String key = userScore.getUserId() + ":" + userScore.getName();
            redisManager.zAdd(SALE_SCORE, key, userScore.getUserScore());
        });
    }

    /**
     * 添加用户积分
     *
     * @param uid
     * @param score
     */
    public void increSaleSocre(String uid, Integer score) {
        SysUser user = userMapper.find(uid);
        if (user == null) {
            return;
        }
        int uidInt = Integer.parseInt(uid);
        long socreLong = Long.parseLong(score + "");
        String name = user.getUserName();
        String key = uid + ":" + name;
        userScoreMapper.insert(new UserScore(uidInt, socreLong, name));
        redisManager.incrementScore(SALE_SCORE, key, score);
    }

    /**
     * 获取用户信息
     * @param uid
     * @param name
     * @return
     */
    public Map<String, Object> userRank(String uid, String name) {
        Map<String, Object> retMap = new LinkedHashMap<>();
        String key = uid + ":" + name;
        Integer rank = redisManager.zRank(SALE_SCORE, key).intValue();
        Long score = redisManager.zSetScore(SALE_SCORE, key).longValue();
        retMap.put("userId", uid);
        retMap.put("score", score);
        retMap.put("rank", rank);
        return retMap;
    }

    /**
     * 排行榜
     * @param start
     * @param end
     * @return
     */
    public List<Map<String, Object>> reverseZRankWithRank(long start, long end) {
        Set<ZSetOperations.TypedTuple<Object>> setObj = redisManager.reverseZRankWithRank(SALE_SCORE, start, end);
        List<Map<String, Object>> mapList = setObj.stream().map(objectTypedTuple -> {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("userId", objectTypedTuple.getValue().toString().split(":")[0]);
            map.put("userName", objectTypedTuple.getValue().toString().split(":")[1]);
            map.put("score", objectTypedTuple.getScore());
            return map;
        }).collect(Collectors.toList());
        return mapList;
    }
    //---------------------redis end-------------------------------------

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("======enter init bean=======");
        this.rankSaleAdd();
    }

}
