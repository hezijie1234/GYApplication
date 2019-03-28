package com.gongyou.firstcode;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hezijie on 2019/3/27.
 * 参考文章：https://blog.csdn.net/justin_1107/article/details/72903006
 *
 * Parcel提供了一套机制，可以将序列化之后的数据写入到一个共享内存中，其他进程通过Parcel可以从这块共享内存中读出字节流，并反序列化成对象
 */

public class Person implements Parcelable{

    private boolean sex;
    private String name;
    private int age;
    // ***** 注意: 这里如果是集合 ,一定要初始化 *****
    private List<Author> mData = new ArrayList<>();
    private List<String> testList = new ArrayList<>();
    private Author author;

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mData=" + mData +
                ", testList=" + testList +
                ", author=" + author +
                '}';
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setmData(List<Author> mData) {
        this.mData = mData;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Author> getmData() {
        return mData;
    }

    public List<String> getTestList() {
        return testList;
    }

    public Author getAuthor() {
        return author;
    }

    public Person() {
    }

    protected Person(Parcel in) {
        sex = in.readByte() != 0;
        name = in.readString();
        age = in.readInt();
        //读取集合也分为两类,对应写入的两类
        //这一类需要用相应的类加载器去获取
        //in.readList(mData, Author.class.getClassLoader()); // 对应writeList
        in.readTypedList(mData,Author.CREATOR);//对应writeTypeList
        //mData = in.createTypedArrayList(Author.CREATOR);//对应writeTypeList
        testList = in.createStringArrayList();
        author = in.readParcelable(Author.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (sex ? 1 : 0));
        dest.writeString(name);
        dest.writeInt(age);
        // 序列化一个对象的集合有两种方式,以下两种方式都可以

        //这些方法们把类的信息和数据都写入Parcel，以使将来能使用合适的类装载器重新构造类的实例.所以效率不高
//        dest.writeList(mData);
        //这些方法不会写入类的信息，取而代之的是：读取时必须能知道数据属于哪个类并传入正确的Parcelable.Creator来创建对象
        // 而不是直接构造新对象。（更加高效的读写单个Parcelable对象的方法是：
        // 直接调用Parcelable.writeToParcel()和Parcelable.Creator.createFromParcel()）
        dest.writeTypedList(mData);
        dest.writeStringList(testList);
        // 序列化对象的时候传入要序列化的对象和一个flag,
        // 这里的flag几乎都是0,除非标识当前对象需要作为返回值返回,不能立即释放资源
        dest.writeParcelable(author,flags);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
