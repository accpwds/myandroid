package com.example.android.object;

/**
 * Created by Administrator on 2018/3/6.
 */

public class Gategory {

    private int id;

    private String categoryName;

    private int categoryCode;

    public int getId(){

        return id;
    }

    public String getCategoryName(){

        return categoryName;
    }

    public int getCategoryCode(){

        return categoryCode;
    }

    public void setId(int id){

        this.id = id;
    }

    public void setCategoryName(String categoryName){

        this.categoryName = categoryName;
    }

    public void setCategoryCode(int categoryCode){

        this.categoryCode = categoryCode;
    }
}
