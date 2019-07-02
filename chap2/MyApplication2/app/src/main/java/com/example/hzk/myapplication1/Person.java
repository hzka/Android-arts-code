package com.example.hzk.myapplication1;

import java.io.Serializable;

/**
 * Created by hzk on 2019/6/5.
 */

public class Person implements Serializable{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
