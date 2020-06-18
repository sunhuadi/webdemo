package com.webdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String year;
    private String id;
    private String sex;
    private String destination;
    private String Acompany;
    private String Wcompany;
    private String Bcompany;
    private String Ccompany;
    private String Vcompany;
    private String Hcompany;
public void setId(String id)
{
    this.id=id;
}
    public String getYear() {
        return year;
    }

    public String getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getDestination() {
        return destination;
    }

    public String getAcompany() {
        return Acompany;
    }

    public String getWcompany() {
        return Wcompany;
    }

    public String getBcompany() {
        return Bcompany;
    }

    public String getCcompany() {
        return Ccompany;
    }

    public String getVcompany() {
        return Vcompany;
    }

    public String getHcompany() {
        return Hcompany;
    }

}
