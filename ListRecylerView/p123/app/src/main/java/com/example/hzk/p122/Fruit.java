package com.example.hzk.p122;

/**
 * 创建定义实体类，用于ListView适配器的适配类型。
 */
public class Fruit {
    private String name;//水果名字
    private int imageid;//水果对应的资源id
    public Fruit(String name, int imageid){
        this.imageid = imageid;
        this.name = name;
    }

    public int getImageid() {
        return imageid;
    }

    public String getName() {
        return name;
    }
}
