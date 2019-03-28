package com.gongyou.firstcode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hezijie on 2019/3/27.
 */

public class Author implements Parcelable{

    private String name;
    private int age;

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Author(Parcel in) {
        name = in.readString();
        age = in.readInt();
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

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
