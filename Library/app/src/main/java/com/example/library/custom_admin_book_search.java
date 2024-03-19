package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_admin_book_search extends BaseAdapter {

    String []nm,auth,edition,status,image;
    private  Context context;

    public custom_admin_book_search(Context applicationContext, String[] nm, String[] auth, String[] edition, String[] status, String[] image) {

        this.context=applicationContext;
        this.nm=nm;
        this.auth=auth;
        this.edition=edition;
        this.status=status;
        this.image=image;
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
            gridView = inflator.inflate(R.layout.activity_custom_admin_book_search, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView27);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView28);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView29);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView30);
        ImageView im = (ImageView) gridView.findViewById(R.id.imageView8);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);

        tv1.setText(nm[i]);
        tv2.setText(auth[i]);
        tv3.setText(edition[i]);
        tv4.setText(status[i]);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000/static/"+image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);


        return gridView;


    }
}



