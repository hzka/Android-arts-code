package com.example.hzk.myapplication02;

import java.io.Serializable;

/**
 * Created by hzk on 2019/6/5.
 */

public class Person implements Serializable{
    private static final long serialVersionUID =1223424231123123L;
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
