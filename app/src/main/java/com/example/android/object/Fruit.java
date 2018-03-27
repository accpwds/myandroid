package com.example.android.object;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/26.
 *
 * 传递对象 Serializable
 *
 */

public class Fruit implements Serializable{

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
