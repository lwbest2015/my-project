package com.qf.my.project.regist.web;

import com.qf.constant.RedisConstant;
import com.qf.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MyProjectRegistWebApplicationTests {

    static int arr[] = new int[10];


    @Test
    public void test1() {

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //转换成List，方便使用lambda
        List<Integer> list = Arrays.asList(array);
        //用于存放*10后的元素的集合
        List<Integer> myList = new ArrayList<>(list.size());
        list.forEach(item -> {
            myList.add(item * 10);
        });
        //排序
        Collections.sort(myList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        //输出前5个
        for (int i = 0; i < 5; i++) {
            System.out.println(myList.get(i));
        }
    }

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        String s="13201605310";
        System.out.println(RedisUtil.getRedisKey(RedisConstant.REGIST_SMS,s));
        redisTemplate.opsForValue().set(RedisUtil.getRedisKey(RedisConstant.REGIST_SMS,s),"7788",20, TimeUnit.MINUTES);
    }

}
