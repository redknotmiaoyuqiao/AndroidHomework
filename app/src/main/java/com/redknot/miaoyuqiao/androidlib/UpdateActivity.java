package com.redknot.miaoyuqiao.androidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.redknot.miaoyuqiao.sqlite.SqlBook;

public class UpdateActivity extends AppCompatActivity {

    private TextInputLayout ti_book_name;
    private TextInputLayout ti_book_author;
    private TextInputLayout ti_book_introduce;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();

        ti_book_name = (TextInputLayout) findViewById(R.id.ti_book_name);
        ti_book_name.setHint("请输入书名：");
        final EditText et_book_name = ti_book_name.getEditText();
        et_book_name.setText(intent.getStringExtra("book_name"));

        ti_book_author = (TextInputLayout) findViewById(R.id.ti_book_author);
        ti_book_author.setHint("请输入作者：");
        final EditText et_book_author = ti_book_author.getEditText();
        et_book_author.setText(intent.getStringExtra("book_author"));

        ti_book_introduce = (TextInputLayout) findViewById(R.id.ti_book_introduce);
        ti_book_introduce.setHint("请输入简介：");
        final EditText et_book_introduce = ti_book_introduce.getEditText();
        et_book_introduce.setText(intent.getStringExtra("book_introduce"));

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlBook sqlBook = new SqlBook(UpdateActivity.this);
                Book book = new Book(10,et_book_name.getText().toString(),et_book_author.getText().toString(),et_book_introduce.getText().toString());
                sqlBook.updateBook(intent.getIntExtra("book_id",0),book);
                finish();
            }
        });

    }

}
