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
import android.widget.Button;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_admin_view_students_search extends BaseAdapter {

    String[] sid, sname, eml, phn, crs, sem;
    private Context context;


    public custom_admin_view_students_search(Context applicationContext, String[] sid, String[] sname, String[] eml, String[] phn, String[] crs, String[] sem) {
        this.context = applicationContext;
        this.sid = sid;
        this.sname = sname;
        this.eml = eml;
        this.phn = phn;
        this.crs = crs;
        this.sem = sem;
    }



    @Override
    public int getCount() {
        return sname.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_admin_view_students_search, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView23);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView39);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView7);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView8);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView9);
        Button bt = (Button) gridView.findViewById(R.id.button8);
        bt.setTag(i);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int pos=(int)view.getTag();
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("sid",sid[pos]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),admin_duedate.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });






        ImageView im_delete = (ImageView) gridView.findViewById(R.id.imageView7);

        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);

        tv1.setText(sname[i]);
        tv2.setText(eml[i]);
        tv3.setText(phn[i]);
        tv4.setText(crs[i]);
        tv5.setText(sem[i]);

        return gridView;

    }
}