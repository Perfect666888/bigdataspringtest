package com.bigdata.demo.Controller;

import com.bigdata.demo.bean.Student_score;
import com.bigdata.demo.service.StudentService;
import com.bigdata.demo.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//标记
@RestController
public class QueryController {

    //标记
    @GetMapping("/query")
    public static List<Student_score> queryScore(String name){
        //创建服务层对象
        StudentService studentService = new StudentServiceImpl();

        //调用方法
        List<Student_score> student_scores = studentService.queryScore(name);
        return student_scores;
    }

}
