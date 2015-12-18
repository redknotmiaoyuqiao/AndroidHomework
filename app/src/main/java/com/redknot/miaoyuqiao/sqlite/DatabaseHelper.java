package com.redknot.miaoyuqiao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by miaoyuqiao on 15/12/19.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "book.db"; //数据库名称
    private static final int version = 1; //数据库版本

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table table_book (" +
                "book_id integer PRIMARY KEY autoincrement," +
                "book_name varchar(50)," +
                "book_author varchar(50)," +
                "book_introduce varchar(250)" +
                ");";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("book_name", "钢铁是怎样练成的");
        values.put("book_author", "尼古拉·奥斯特洛夫斯基");
        values.put("book_introduce", "《钢铁是怎样炼成的》是前苏联作家尼古拉·奥斯特洛夫斯基所著的一部长篇小说，于1933年写成。小说通过记叙保尔·柯察金的成长道路告诉人们，一个人只有在革命的艰难困苦中战胜敌人也战胜自己，只有在把自己的追求和祖国、人民的利益联系在一起的时候，才会创造出奇迹，才会成长为钢铁战士。");

        db.insert("table_book", null, values);
        db.insert("table_book", null, values);
        db.insert("table_book", null, values);
        db.insert("table_book", null, values);
        db.insert("table_book", null, values);

        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
