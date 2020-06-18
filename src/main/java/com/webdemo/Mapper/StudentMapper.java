package com.webdemo.Mapper;

import com.webdemo.pojo.Allinformation;
import com.webdemo.pojo.Analyze;
import com.webdemo.pojo.Student;
import com.webdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    List<Student> queryStuList();
    List<Student> queryStuListByyear(String year);
    List<Student> queryStuListByyearanddestination(String year,String destination);
    public void addStu(Student student);
    public Student queryStuById(String id);
    public Student queryStuByIdYear(String id,String year);
   public void updateStuByIdYear(String id,String year);
    public void updateStuById(Student student);
    public void deleteById(String id);
    public void deleteByIdYear(String id,String year);
    List<Allinformation> queryCcompanyAll();
    List<Allinformation> querydestinationAll();
    List<Analyze> queryAnalyzeDestination(String year);
    List<Analyze> queryAnalyzePlace();
    List<Analyze> queryAnalyzeVcompany(int year);
    List<Analyze> queryAnalyzeYearByCcompany(String Ccompany);
}
