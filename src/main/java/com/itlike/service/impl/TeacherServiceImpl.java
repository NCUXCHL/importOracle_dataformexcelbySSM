package com.itlike.service.impl;

import com.alibaba.fastjson.JSON;
import com.itlike.domain.Teacher;
import com.itlike.mapper.TeacherMapper;
import com.itlike.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    /*注入mapper*/
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherMapper getTeacherMapper() {
        return teacherMapper;
    }

    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public int save(Teacher teacher) {
        /*调用mapper*/
        System.out.println("来到了服务层");
        int i = 0;
        System.out.println(teacher.toString());
        try {
          //  i = teacherMapper.insert(teacher);
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            System.out.println("此处有异常");
            //e.printStackTrace();
        }
        System.out.println(i);
        return i;

    }

    @Override
    public int saveMap(Map map){
        System.out.println(JSON.toJSONString(map));

        int i = 0;
            i = teacherMapper.insertTeacher(map);

        System.out.println(i);
        return i;

    }


}
