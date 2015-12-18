package com.redknot.miaoyuqiao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.redknot.miaoyuqiao.androidlib.Book;
import com.redknot.miaoyuqiao.androidlib.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miaoyuqiao on 15/12/19.
 */
public class SqlBook {

    private DatabaseHelper helper;

    public SqlBook(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    public void updateBook(int id, Book book) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("book_name", book.getBook_name());
        cv.put("book_author", book.getBook_author());
        cv.put("book_introduce", book.getBook_introduce());

        String[] args = {String.valueOf(id)};
        db.update("table_book", cv, "book_id=?", args);
    }

    public void getBookList(List<Book> list) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from table_book", new String[]{});
        list.clear();
        while (cursor.moveToNext()) {
            int book_id = cursor.getInt(0);
            String book_name = cursor.getString(1);
            String book_author = cursor.getString(2);
            String book_introduce = cursor.getString(3);

            Book book = new Book(book_id, book_name, book_author, book_introduce);
            list.add(book);
        }

        cursor.close();
        db.close();

    }

    public boolean addBook(Book book) {
        SQLiteDatabase db = this.helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("book_name", book.getBook_name());
        values.put("book_author", book.getBook_author());
        values.put("book_introduce", book.getBook_introduce());
        db.insert("table_book", null, values);

        db.close();

        return true;
    }

    public boolean delBook(Book book) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        String[] arr = {book.getBook_id() + ""};
        db.delete("table_book", "book_id = ?", arr);
        db.close();
        return true;
    }
}
