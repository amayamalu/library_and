package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class custom_student_books extends BaseAdapter {

    String []name,author,edition,status,cat,image;
    private Context context;


    public custom_student_books(Context applicationContext, String[] name, String[] author, String[] edition, String[] status,String[] cat, String[] image) {


        this.context=applicationContext;
        this.name=name;
        this.author=author;
        this.edition=edition;
        this.status=status;
        this.cat=cat;

        this.image=image;
    }



    @Override
    public int getCount() {
        return cat.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_student_books, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView27);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView28);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView29);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView30);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView26);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView8);


        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);

        tv1.setText(name[i]);
        tv2.setText(author[i]);
        tv3.setText(edition[i]);
        tv4.setText(status[i]);
        tv5.setText(cat[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
        String u=sh.getString("url","");

        String url=u+image[i];
        Toast.makeText(context, ""+url, Toast.LENGTH_SHORT).show();

        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);


        return gridView;

    }
}