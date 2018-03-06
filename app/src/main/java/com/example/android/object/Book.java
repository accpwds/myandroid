package com.example.android.object;

/**
 * Created by Administrator on 2018/3/6.
 */

public class Book {

    private int id;

    private String name;

    private String author;

    private double price;

    private int pages;

    private String press;

    public int getId() {

        return id;
    }

    public String getAuthor() {

        return author;
    }

    public String getName() {

        return name;
    }

    public Double getPrice() {

        return price;
    }

    public int getPages() {

        return pages;
    }

    public String getPress() {

        return press;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    public void setPages(int pages) {

        this.pages = pages;
    }

    public void setPress(String press){

        this.press = press;
    }
}
