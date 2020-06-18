package com.webdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analyze {
    private String name;
    int summ;

    public String getName() {
        return name;
    }

    public int getSumm() {
        return summ;
    }
}
