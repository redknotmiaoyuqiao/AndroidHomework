package com.redknot.miaoyuqiao.androidlib;

/**
 * Created by miaoyuqiao on 15/12/19.
 */
public class Book {
    private int book_id;
    private String book_name;
    private String book_author;
    private String book_introduce;

    public Book(int book_id, String book_name, String book_author, String book_introduce) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_introduce = book_introduce;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_introduce() {
        return book_introduce;
    }

    public void setBook_introduce(String book_introduce) {
        this.book_introduce = book_introduce;
    }
}
