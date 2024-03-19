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

public class custom_stud extends BaseAdapter {
    String[] sid, sname, eml, phn, crs, sem;
    private Context context;

    public custom_stud(Context applicationContext, String[] sid, String[] sname, String[] eml, String[] phn,
                       String[] crs, String[] sem) {

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
        return sid.length;
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
            gridView = inflator.inflate(R.layout.custom_student, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView23);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView39);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView7);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView8);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView9);
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

        im_delete.setTag(i);
        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("url", "");
                String url = hu + "/and_delete_stud";

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
                                        Intent ij = new Intent(context, admin_view_student.class);
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

                        params.put("sid", sid[pos]);

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


        return gridView;


    }
}


