package com.redknot.miaoyuqiao.androidlib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;

import java.util.List;

/**
 * Created by miaoyuqiao on 15/12/19.
 */
public class MainAdapter extends BaseSwipListAdapter {

    private List<Book> bookList = null;
    private Context context = null;

    public MainAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(this.context, R.layout.listview_main, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Book book = this.bookList.get(position);
        holder.book_name.setText(book.getBook_name());
        holder.book_author.setText(book.getBook_author());
        holder.book_introduce.setText(book.getBook_introduce());
        return convertView;
    }

    class ViewHolder {
        TextView book_name;
        TextView book_author;
        TextView book_introduce;

        public ViewHolder(View view) {
            book_name = (TextView) view.findViewById(R.id.book_name);
            book_author = (TextView) view.findViewById(R.id.book_author);
            book_introduce = (TextView) view.findViewById(R.id.book_introduce);
            view.setTag(this);
        }
    }
}
