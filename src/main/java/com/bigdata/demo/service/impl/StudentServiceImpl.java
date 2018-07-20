package com.bigdata.demo.service.impl;

import com.bigdata.demo.bean.Student_score;
import com.bigdata.demo.dao.StudentDao;
import com.bigdata.demo.dao.impl.StudentDaoImpl;
import com.bigdata.demo.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    //创建数据访问层对象
    private StudentDao studentDao = new StudentDaoImpl();


    @Override
    public  List<Student_score> queryScore(String name) {

        //调用访问层对象方法
        List<Student_score> lists = studentDao.queryScore(name);
        return lists;

    }
}
