package com.itlike.test;

import com.itlike.service.TeacherService;
import com.itlike.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class testTest1 {
    private TeacherService teacherService1;
    @Test
    public void test0(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        teacherService1 = ac.getBean(TeacherService.class);
        System.out.println(teacherService1.toString());
       // System.out.println(teacherService1.findFields1("teacher").toString());
    }
    @Test
    public  void test1(){
        //动态初始化数据
        int j=2;
        int i=j;
        String books[] = new String[i];
        books[0] = "Thinking in Java";
        books[1] = "Effective Java";


        System.out.println(books[1]);
    }
    @Test
    public  void test2(){
        Map<String,Object> map=new HashMap<>();
        map.put("1",1);
        map.put("1",2);



        System.out.println(map.get("1"));
    }



   }