package com.itlike.mapper;

import com.itlike.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    int insert(Teacher record);

    List<Teacher> selectAll();

    int insertTeacher(Map columnMap);

   // void insertData(@Param("dataMap") Map<String, String> dataMap);

}