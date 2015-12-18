package com.redknot.miaoyuqiao.androidlib;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.redknot.miaoyuqiao.sqlite.SqlBook;

/**
 * Created by miaoyuqiao on 15/12/19.
 */
public class AddActivity extends AppCompatActivity {

    private TextInputLayout ti_book_name;
    private TextInputLayout ti_book_author;
    private TextInputLayout ti_book_introduce;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ti_book_name = (TextInputLayout) findViewById(R.id.ti_book_name);
        ti_book_name.setHint("请输入书名：");
        final EditText et_book_name = ti_book_name.getEditText();

        ti_book_author = (TextInputLayout) findViewById(R.id.ti_book_author);
        ti_book_author.setHint("请输入作者：");
        final EditText et_book_author = ti_book_author.getEditText();

        ti_book_introduce = (TextInputLayout) findViewById(R.id.ti_book_introduce);
        ti_book_introduce.setHint("请输入简介：");
        final EditText et_book_introduce = ti_book_introduce.getEditText();

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlBook sqlBook = new SqlBook(AddActivity.this);

                String name = et_book_name.getText().toString();
                String author = et_book_author.getText().toString();
                String introduce = et_book_introduce.getText().toString();

                if(name.equals("")){
                    Snackbar.make(v, "书名不能为空", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                if(author.equals("")){
                    Snackbar.make(v, "作者不能为空", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                if(introduce.equals("")){
                    Snackbar.make(v, "介绍不能为空", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }


                Book book = new Book(10,name,author,introduce);
                sqlBook.addBook(book);
                finish();
            }
        });
    }
}
