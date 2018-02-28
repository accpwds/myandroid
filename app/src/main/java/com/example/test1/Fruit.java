package com.example.test1;

/**
 * Created by Administrator on 2018/2/26.
 */

public class Fruit {

    private String name;
    private int imageId;
    private String content;

    public Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public Fruit(String content,String name, int imageId){
        this.name = name;
        this.imageId = imageId;
        this.content = content;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getContent(){
        return content;
    }
}
