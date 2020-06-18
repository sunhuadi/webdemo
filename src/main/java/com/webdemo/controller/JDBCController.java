package com.webdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @GetMapping("/usrList")
    public  String userlist(){
        String sql="select * from logim";
        List<Map<String,Object>> lis_map=jdbcTemplate.queryForList(sql);
        lis_map.getClass();
        String authorStr="Null";
        for(int i = 0;i < lis_map.size();i++) {
            Map<String, Object> map = lis_map.get(i);
            authorStr = map.get("password").toString();
            //System.out.println("author" + " : " + authorStr);
        }
        return authorStr;
    }
    @GetMapping("/add")
    public String addlogin()
    {
        String sql="insert into login.logim(username,password) values ('xiaoming','123456')";
        jdbcTemplate.update(sql);
        return "update";
    }
    @GetMapping("/update/{id}")
    public String updatelogin(@PathVariable("id") String id)
    {

        String sql="update login.logim set  username=?,password=? where username='"+id+"'";

        //封装
        Object[] objects = new Object[2];
        objects[0]="xiaoming3";
        objects[1]="111111";
        jdbcTemplate.update(sql,objects);
        return "update-ok";
    }
    @GetMapping("/delet/{text}")
    public String deletelogin(@PathVariable("text") String txt)
    {
        String sql="delete from login.logim where username=?";

        //封装
        jdbcTemplate.update(sql,txt);
        return "delet-ok";
    }
}
