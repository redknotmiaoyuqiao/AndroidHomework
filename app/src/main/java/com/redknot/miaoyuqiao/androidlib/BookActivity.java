package com.redknot.miaoyuqiao.androidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    private TextView book_name;
    private TextView book_introduce;
    private TextView book_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        book_name = (TextView) findViewById(R.id.book_name);
        book_introduce = (TextView) findViewById(R.id.book_introduce);
        book_author = (TextView) findViewById(R.id.book_author);

        book_name.setText(intent.getStringExtra("book_name"));
        book_author.setText(intent.getStringExtra("book_author"));
        book_introduce.setText(intent.getStringExtra("book_introduce"));

    }

}
