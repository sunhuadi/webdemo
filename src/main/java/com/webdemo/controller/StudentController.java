package com.webdemo.controller;

import com.webdemo.Mapper.StudentMapper;
import com.webdemo.pojo.Allinformation;
import com.webdemo.pojo.Student;
import com.webdemo.pojo.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentMapper stumapper;


    @RequestMapping("/emps.html/{year}")
    public String list(@PathVariable("year")String year,Model model)
    {

        List<Student> stuList=stumapper.queryStuListByyear(year);
        model.addAttribute("emps",stuList);
        model.addAttribute("year",year);
        List<Allinformation> destinationAll=stumapper.querydestinationAll();
        model.addAttribute("destinationAll",destinationAll);
       // System.out.println(stuList);
        return "emp/list";
    }
    @RequestMapping("/emps.html/{year}/{destination}")
    public String listBydestination(@PathVariable("year")String year,
                                    @PathVariable("destination")String destination,
                       Model model)
    {

        List<Allinformation> destinationAll=stumapper.querydestinationAll();
        model.addAttribute("destinationAll",destinationAll);
        List<Student> stuList=stumapper.queryStuListByyearanddestination(year,destination);

        model.addAttribute("emps",stuList);
        model.addAttribute("year",year);
        model.addAttribute("destination",destination);

        // System.out.println(stuList);
        return "emp/list";
    }

    @GetMapping("/add.html/{year}/{role}")
    public String addStu(@PathVariable("year")String year,@PathVariable("role")int role,Student stu,Model model)
    {
        model.addAttribute("year",year);
       // System.out.println("跳转");
        //stumapper.addStu(stu);
        //添加操作
        List<Student> stuList=stumapper.queryStuListByyear(year);
        model.addAttribute("emps",stuList);
        if(role==0)
        {
            model.addAttribute("rolemsg", null);
            return "emp/add";
        }
        else
        {
            model.addAttribute("rolemsg","你没有权限执行此操作");
            return "emp/list";
        }

    }
    @GetMapping("/addf.html/{year}")
    public String addfStu(@PathVariable("year")int year,Student stu,Model model)
    {

       // System.out.println("跳转"); model.addAttribute("year",year);
        model.addAttribute("msg1","该用户id已经存在或者为空");
        //stumapper.addStu(stu);
        //添加操作
        return "emp/add";
    }
    ///addok.html/'+${year}

    @RequestMapping("addok.html/{year}")
    public String addtodo(@PathVariable("year")int year,
                          Student stu,
                          Model modelx)
    {
        List<Student> stuList=stumapper.queryStuList();
      //  System.out.println("表单参数");
      //  System.out.println(stu);
        System.out.println(stu.getId());
        for(Student stu1:stuList)
        {

            if (stu.getId().equals(stu1.getId())&&stu.getYear().equals(stu1.getYear())||stu.getId().equals(""))
            {
                return "redirect:/addf.html/"+year;
            }
        }
        stumapper.addStu(stu);
        //添加操作
        return "redirect:/emps.html/"+year;
    }

    //跳转员工修改页面
    //'/emp/'+${year}+'/'+${emp.getId()
    @GetMapping("/emp/{year}/{id}/{role}")
    public String toUpdate(@PathVariable("year")String year,
                           @PathVariable("id")String id,
                           @PathVariable("role")int role,
                           Model model)
    {
        List<Student> stuList=stumapper.queryStuListByyear(year);
        model.addAttribute("emps",stuList);
        if(role==0)
        {
            model.addAttribute("rolemsg", null);
            Student stu=stumapper.queryStuByIdYear(id,year);
            //查出原来的数据
            model.addAttribute("year",year);
            model.addAttribute("student",stu);
            return "emp/update";
        }
        else
        {
            model.addAttribute("rolemsg","你没有权限执行此操作");
            return "emp/list";
        }

    }
    @PostMapping("updateStu/{year}")
    public String update(Student stu,@PathVariable("year")int year)
    {
       // System.out.println("修改为："+stu);

        stumapper.updateStuById(stu);
        return "redirect:/emps.html/"+year;
    }

    @GetMapping("/delemp/{year}/{id}/{role}")
    public String delet(@PathVariable("id")String id,
                        @PathVariable("role")int role,
                        @PathVariable("year")String year,Model model)
    {
        List<Student> stuList=stumapper.queryStuListByyear(year);
        model.addAttribute("emps",stuList);
        if(role==0)

        {
            stumapper.deleteByIdYear(id,year);
            //查出原来的数据
            return "redirect:/emps.html/"+year;
        }
        else
        {
            model.addAttribute("rolemsg","你没有权限执行此操作");
            return "emp/list";
        }
       // System.out.println(id);

    }

}