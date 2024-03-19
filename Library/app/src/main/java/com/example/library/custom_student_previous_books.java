package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class custom_student_previous_books extends BaseAdapter {


    String []date,due_date,book_info,status;
    private Context context;

    public custom_student_previous_books(Context applicationContext, String[] date, String[] due_date, String[] book_info, String[] status) {

        this.context=applicationContext;
        this.date=date;
        this.due_date=due_date;
        this.book_info=book_info;
        this.status=status;

    }



    @Override
    public int getCount() {
        return status.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custom_student_previous_books, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView12);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView14);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView16);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView18);


        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(date[i]);
        tv2.setText(due_date[i]);
        tv3.setText(book_info[i]);
        tv4.setText(status[i]);


        return gridView;

    }
}