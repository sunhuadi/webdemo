package com.webdemo.controller;

import com.webdemo.Mapper.StudentMapper;
import com.webdemo.Mapper.UserMapper;
import com.webdemo.pojo.Student;
import com.webdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TestMybatis {

    @Autowired
    private StudentMapper stuMapper;
    @ResponseBody
    @GetMapping("/testquery")
    public List<Student> querylist()
    {

       List<Student> stuList=stuMapper.queryStuList();
       for(Student stu : stuList)
       {
           System.out.println(stu);
       }
        return stuList;

    }
    @GetMapping("/testadd")
    public String add()
    {
       // Student stu=new Student(5,"sunwei","嘿嘿");
        //System.out.println(stu);
        //stuMapper.addStu(stu);
        return "ok";
    }
    @GetMapping("/testid")
    public String testid()
    {
        System.out.println("id查询结果");
        Student stu=stuMapper.queryStuById("55");
            System.out.println(stu);
        //System.out.println(stu);
        return "ok";
    }
    @GetMapping("/testup")
    public String up()
    {

        return "ok";
    }
}
