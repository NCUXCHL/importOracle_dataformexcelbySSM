package com.itlike.service;

import com.itlike.domain.Teacher;

import java.util.Map;

public interface TeacherService {
    public int save(Teacher teacher);
    public int saveMap(Map columnMap);

}
