package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import kotlin.text.UStringsKt;

public class admin_add_student extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText ed_name, ed_eml, ed_phn, ed_sem;
    Button b;
    Spinner sp;
    String[] cid,cname;
    String sel_crs="";

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), admin_view_student.class);
        startActivity(i);
    }

    public void load_course(){
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url=sh.getString("url","")+"/and_view_course";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                cid= new String[js.length()];
                                cname= new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    cid[i] = u.getString("id");//dbcolumn name in double quotes
                                    cname[i] = u.getString("cname");
                                }
                                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, cname);
                                sp.setAdapter(ad);

                            } else {
                                Toast.makeText(getApplicationContext(), "No courses added", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);

        ed_name=findViewById(R.id.editTextTextPersonName);
        ed_eml=findViewById(R.id.editTextTextPersonName2);
        ed_phn=findViewById(R.id.editTextTextPersonName3);
        ed_sem=findViewById(R.id.editTextTextPersonName4);
        sp=findViewById(R.id.spinner);
        load_course();
        sp.setOnItemSelectedListener(this);
        b=findViewById(R.id.button3);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name=ed_name.getText().toString();
        String eml=ed_eml.getText().toString();
        String phn=ed_phn.getText().toString();
        String sem=ed_sem.getText().toString();
        if(name.equalsIgnoreCase("")){
            ed_name.setError("Missing");
        } else if(eml.equalsIgnoreCase("")){
            ed_eml.setError("Missing");
        } else if(phn.equalsIgnoreCase("")){
            ed_phn.setError("Missing");
        } else if(phn.length()!=10){
            ed_phn.setError("Must be 10 digits");
        } else if(sem.equalsIgnoreCase("")){
            ed_sem.setError("Missing");
        } else {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("url", "");
            String url = hu + "/and_add_student";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                    Toast.makeText(getApplicationContext(), "Student added", Toast.LENGTH_SHORT).show();
                                    Intent ij = new Intent(getApplicationContext(), admin_view_student.class);
                                    startActivity(ij);

//
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("name", name);
                    params.put("eml", eml);
                    params.put("phn", phn);
                    params.put("cid", sel_crs);
                    params.put("sem", sem);

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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sel_crs=cid[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}