package com.webdemo.Mapper;

import com.webdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//表示mybatis中的mapper类
@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();
    User queryUserByname(String username);
    //User queryPsw(String username);
    void insertUser1(User user);
    int addUser(User user);
    int updata(User user);
    int delet(User user);
    //String getPsw(User user);


}
