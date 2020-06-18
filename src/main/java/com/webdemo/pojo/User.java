package com.webdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String privilege;
    private String realname;
    private String field;
    private String id;
    private String qq;
    private String email;
    private String images;

    public String getId() {
        return id;
    }

    public String getQq() {
        return qq;
    }

    public String getEmail() {
        return email;
    }

    public String getImages() {
        return images;
    }

    public String getRealname() {
        return realname;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getField() {
        return field;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getPrivilege() {
        return privilege;
    }

}
