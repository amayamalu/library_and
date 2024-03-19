package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_admin_view_books extends BaseAdapter {

    String []bid,name,author,edition,status,cat,image;
    private Context context;


    public custom_admin_view_books(Context applicationContext, String[] bid, String[] name, String[] author, String[] edition, String[] status, String[] cat, String[] image) {

        this.bid=bid;
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
            gridView = inflator.inflate(R.layout.activity_custom_admin_view_books, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView23);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView39);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView22);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView24);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView25);
        Button bt=(Button)gridView.findViewById(R.id.button7);
        bt.setTag(i);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               int pos=(int)view.getTag();
               SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
               SharedPreferences.Editor ed=sh.edit();
               ed.putString("bid",bid[pos]);
               ed.commit();
               Intent i=new Intent(context.getApplicationContext(),admin_view_students_search.class);
               i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i);

            }
        });

        ImageView im_delete = (ImageView) gridView.findViewById(R.id.imageView7);

        ImageView im = (ImageView) gridView.findViewById(R.id.imageView10);
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


        im_delete.setTag(i);
        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("url", "");
                String url = hu + "/and_delete_book";

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                        Intent ij = new Intent(context, admin_view_book.class);
                                        ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(ij);



//
                                    }else {
                                        Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("bid", bid[pos]);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);
            }
        });

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
        String u=sh.getString("url","");

        String url=u+image[i];
//        Toast.makeText(context, ""+url, Toast.LENGTH_SHORT).show();

        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);


        return gridView;


    }
}


