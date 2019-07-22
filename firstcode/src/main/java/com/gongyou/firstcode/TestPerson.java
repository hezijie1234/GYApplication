package com.gongyou.firstcode;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hezijie on 2019/7/22.
 */

public class TestPerson implements Parcelable {

    private boolean sex;
    private String name;
    private int age;
    // ***** 注意: 这里如果是集合 ,一定要初始化 *****
    private List<Author> mData = new ArrayList<>();
    private List<String> testList = new ArrayList<>();
    private Author author;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.sex ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeTypedList(this.mData);
        dest.writeStringList(this.testList);
        dest.writeParcelable(this.author, flags);
    }

    public TestPerson() {
    }

    protected TestPerson(Parcel in) {
        this.sex = in.readByte() != 0;
        this.name = in.readString();
        this.age = in.readInt();
        this.mData = in.createTypedArrayList(Author.CREATOR);
        this.testList = in.createStringArrayList();
        this.author = in.readParcelable(Author.class.getClassLoader());
    }

    public static final Creator<TestPerson> CREATOR = new Creator<TestPerson>() {
        @Override
        public TestPerson createFromParcel(Parcel source) {
            return new TestPerson(source);
        }

        @Override
        public TestPerson[] newArray(int size) {
            return new TestPerson[size];
        }
    };
}
