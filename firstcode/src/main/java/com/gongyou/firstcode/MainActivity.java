package com.gongyou.firstcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void intentClick(View view) {
        Person person = new Person();
        person.setAge(18);
        person.setName("子杰");
        person.setSex(true);
        Author author = new Author("曦俊",20);
        person.setAuthor(author);
        List<String> mNameList = new ArrayList<>();
        mNameList.add("小明");
        mNameList.add("小红");
        person.setTestList(mNameList);
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author("阿贵",25));
        authorList.add(new Author("曾凡人",30));
        person.setmData(authorList);
        startActivity(new Intent(this,SingleTaskActvitiy.class).putExtra("data",person));
    }
}
