package com.example.android.object;

import android.os.Parcel;
import android.os.Parcelable;

import okio.Source;

/**
 * Created by Administrator on 2018/3/26.
 *
 * 传递对象  Parcelable
 *
 */

public class Person implements Parcelable {

    private String name;

    private int age;

    public void setName(String name){

        this.name = name;
    }

    public String getName(){

        return name;
    }

    public void setAge(int age){

        this.age = age;
    }

    public int getAge(){

        return age;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            Person person = new Person();
            person.name = in.readString();
            person.age = in.readInt();
            return person;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
