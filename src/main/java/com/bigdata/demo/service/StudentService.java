package com.bigdata.demo.service;

import com.bigdata.demo.bean.Student_score;

import java.util.List;

public interface StudentService {
    //创建查询方法
    public List<Student_score> queryScore(String name);
}
