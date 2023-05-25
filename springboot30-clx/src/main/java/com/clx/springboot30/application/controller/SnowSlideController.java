package com.clx.springboot30.application.controller;

import com.clx.springboot30.application.model.People;
import com.clx.springboot30.application.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

@RestController
public class SnowSlideController {

    @Autowired
    private PeopleService peopleService;

    // 令牌
    static Semaphore semaphore = new Semaphore(200);

    // 并发数
    int currency = 2000;

    /**
     * http://localhost:3100/getAllPeopleMapper1
     *
     * 异常，具体参照SnowSlide.png
     * @return
     */
    @RequestMapping("/getAllPeopleMapper1")
    public String getAllPeopleMapper1() {

        // 循环屏障 J.U.C并发编程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(currency);

        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName() + "--------我准备好了--------");

                    // 等待一起出发
                    try {
                        cyclicBarrier.await(); // 等待，栅栏机制，拦住主线程，等待栅栏开启
                        List<People> peopleList = peopleService.getAllPeopleMapper();
                        System.out.println(peopleList.toString());
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        return "success";
    }

    /**
     * http://localhost:3100/getAllPeopleMapper2
     *
     * @return
     */
    @RequestMapping("/getAllPeopleMapper2")
    public String getAllPeopleMapper2() {

        // 循环屏障 J.U.C并发编程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(currency);

        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName() + "--------我准备好了--------");

                    // 等待一起出发
                    try {
                        cyclicBarrier.await(); // 等待，栅栏机制，拦住主线程，等待栅栏开启
                        semaphore.acquire(); // 多线程争取获取令牌，令牌没有了，等待---类似于lock
                        List<People> peopleList = peopleService.getAllPeopleMapper();
                        System.out.println(peopleList.toString());
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release(); // 释放令牌
                    }
                }
            }).start();
        }
        return "success";
    }
}
