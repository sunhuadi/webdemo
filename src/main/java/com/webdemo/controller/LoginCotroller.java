package com.webdemo.controller;
import com.webdemo.Mapper.UserMapper;
import com.webdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class LoginCotroller {
    @Autowired
    //JdbcTemplate jdbcTemplate;
    private UserMapper userMapper;
    @RequestMapping("/conlogin.html")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        User tuser = userMapper.queryUserByname(username);
       // System.out.println(tuser);
        if(tuser!=null&&tuser.getPassword().equals(password)){
            session.setAttribute("loginUser",username);
            if(tuser.getPrivilege().equals("0"))
            {
                session.setAttribute("rolename","管理员");
                session.setAttribute("tuser","管理员"+tuser.getRealname());
            }
            else {
                session.setAttribute("tuser","用户"+tuser.getRealname());
                session.setAttribute("rolename","用户");
            }
            model.addAttribute("showuser",tuser);
            session.setAttribute("role",tuser.getPrivilege());
            model.addAttribute("role",tuser.getPrivilege());
            return "index";
        }
        else{
            model.addAttribute("msg","密码错误，请重新输入密码！！");
            return "login";
        }
    }
    //注册控制
    @RequestMapping("/signup")
    public String signup(@RequestParam("repassword")String repassword ,
                         @RequestParam("myfile") MultipartFile file,
                         User user,Model model)
    {

        InputStream in=null;
        OutputStream out=null;
        if(userMapper.queryUserByname(user.getUsername())!=null)
        {
            model.addAttribute("msg","该用户已经存在！！");
            return "signup";
        }
        else if(user.getUsername().equals(""))
        {
            model.addAttribute("msg","用户名不能为空！！");
            return "signup";
        }
        else if(user.getPassword().equals(""))
        {
            model.addAttribute("msg","密码不能为空！！");
            return "signup";
        }
        else if(user.getRealname().equals(""))
        {
            model.addAttribute("msg","姓名不能为空！！");
            return "signup";
        }
        else if(user.getEmail().equals(""))
        {
            model.addAttribute("msg","邮件不能为空！！");
            return "signup";
        }
        else if(user.getId().equals(""))
        {
            model.addAttribute("msg","学号不能为空！！");
            return "signup";
        }
        else if(user.getQq().equals(""))
        {
            model.addAttribute("msg","QQ不能为空！！");
            return "signup";
        }
        else if(file.getOriginalFilename().equals(""))
        {
            model.addAttribute("msg","请选择你的头像！！");
            return "signup";
        }
        else if(user.getField().equals(""))
        {
            model.addAttribute("msg","请选择你所在的地区");
            return "signup";
        }
        else if(file==null||file.getOriginalFilename().equals(""))
        {
            model.addAttribute("msg","请上传头像");
        }
        else if(user.getPassword().equals(repassword))
        {
            String path=new String();
            try{
                in=file.getInputStream();
               File targetFile=new File("images/user/"+file.getOriginalFilename());//服务器上传方式
               // File targetFile=new File("src\\main\\resources\\static\\images\\user\\"+file.getOriginalFilename());//本地上传方式
                path="images/user/"+file.getOriginalFilename();
                out =new FileOutputStream(targetFile);
                FileCopyUtils.copy(in,out);
                model.addAttribute("result","上传成功");
            }catch (IOException e)
            {
                e.printStackTrace();
            model.addAttribute("result","上传失败");
            }finally {
                if (in !=null)
                {
                    try {
                        out.close();
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            user.setImages(path);
            user.setPrivilege("1");
            userMapper.insertUser1(user);
        }
        else {
            model.addAttribute("msg","两次密码输入不同，请重新尝试！！");
            return "signup";
        }
        return "login";
    }
    @GetMapping("/zhuye")
    public String zhuye(Model model, HttpServletRequest request)
    {
        Object loginUser = request.getSession().getAttribute("loginUser");
        User tuser=userMapper.queryUserByname(loginUser.toString());
        System.out.println(tuser);
        model.addAttribute("showuser",tuser);
        return "index";
    }

}
