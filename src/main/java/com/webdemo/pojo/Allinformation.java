package com.webdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Allinformation {
    String name;
    int num;

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }
}
