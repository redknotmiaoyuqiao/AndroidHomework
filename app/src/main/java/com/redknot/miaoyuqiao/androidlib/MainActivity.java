package com.redknot.miaoyuqiao.androidlib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.redknot.miaoyuqiao.sqlite.SqlBook;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeMenuListView mListView;
    private List<Book> bookList;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        bookList = new ArrayList<Book>();

        mainAdapter = new MainAdapter(bookList, MainActivity.this);
        mListView.setAdapter(mainAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("book_name", book.getBook_name());
                intent.putExtra("book_author", book.getBook_author());
                intent.putExtra("book_introduce", book.getBook_introduce());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SqlBook sqlBook = new SqlBook(MainActivity.this);
        sqlBook.getBookList(bookList);
        mainAdapter.notifyDataSetChanged();
    }

    private void findView() {
        mListView = (SwipeMenuListView) findViewById(R.id.listView);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                int width = 150;
                SwipeMenuItem updateItem = new SwipeMenuItem(getApplicationContext());
                updateItem.setBackground(new ColorDrawable(Color.rgb(0x03, 0xA9, 0xF4)));
                updateItem.setWidth(dp2px(width));
                updateItem.setIcon(R.drawable.ic_update);
                menu.addMenuItem(updateItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(dp2px(width));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (index == 1) {
                    final Book book = bookList.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    String str = "真的要删除 《" + book.getBook_name() + "》吗？";
                    builder.setMessage(str)
                            .setCancelable(false)
                            .setPositiveButton("否", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("是", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    SqlBook sqlBook = new SqlBook(MainActivity.this);
                                    sqlBook.delBook(book);

                                    sqlBook.getBookList(bookList);

                                    mainAdapter.notifyDataSetChanged();
                                    Snackbar.make(mListView, "已成功删除《" + book.getBook_name() + "》", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setCanceledOnTouchOutside(true);
                    alert.show();
                }
                if (index == 0) {
                    Book book = bookList.get(position);
                    Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                    intent.putExtra("book_id", book.getBook_id());
                    intent.putExtra("book_name", book.getBook_name());
                    intent.putExtra("book_author", book.getBook_author());
                    intent.putExtra("book_introduce", book.getBook_introduce());
                    startActivity(intent);
                }

                return false;
            }
        });

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
