package com.gongyou.localbroadcastdemo;

import java.io.Serializable;

/**
 * Created by hezijie on 2018/6/19.
 */

public class Student implements Serializable {
    private static final long serialVersionUID = 8803986058338626422L;
    String name;
    String age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
