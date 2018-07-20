package com.bigdata.demo.dao.impl;

import com.bigdata.demo.bean.Student_score;
import com.bigdata.demo.dao.StudentDao;
import com.bigdata.demo.util.DBUtils;

import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public List<Student_score> queryScore(String name) {
        //创建查询语句
        String sql="SELECT a.id,a.name,a.clazz,c.name as cname,b.score from student as a join score as b on a.id =b.studentId join cource as c on b.courceId=c.id where a.name =?";
        //调用方法传入需要的参数
        List<Student_score> lists = DBUtils.select(sql, Student_score.class, name);
        return lists;
    }
}
