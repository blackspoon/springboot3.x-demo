package com.clx.springboot30;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 20)
@EnableScheduling
public class Springboot30ClxApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot30ClxApplication.class, args);
    }

}
