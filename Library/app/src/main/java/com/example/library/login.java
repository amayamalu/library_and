package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class login extends AppCompatActivity implements View.OnClickListener {

    Button b;
    EditText ed_usr, ed_psw;


    @Override
    public void onBackPressed() {
        Intent ij=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(ij);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b=(Button)findViewById(R.id.button2);
        ed_usr=(EditText)findViewById(R.id.editText3);
        ed_psw=(EditText)findViewById(R.id.editText2);

        ed_usr.setText("admin");
        ed_psw.setText("admin");
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==b) {

            final String usr = ed_usr.getText().toString();
            final String psw = ed_psw.getText().toString();

            if (usr.length() == 0) {
                ed_usr.setError("Missing");
            } else if (psw.length() == 0) {
                ed_psw.setError("Missing");
            } else {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("url", "");
                String url = hu + "/and_login";

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
                                        String lid = jsonObj.getString("lid");
                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", lid);
                                        ed.commit();
                                        String typ=jsonObj.getString("type");
                                        if(typ.equalsIgnoreCase("admin")){
                                            Intent ij = new Intent(getApplicationContext(), admin_home.class);
                                            startActivity(ij);
                                        }
                                        if(typ.equalsIgnoreCase("student")){
                                            Intent ij = new Intent(getApplicationContext(), student_homee.class);
                                            startActivity(ij);
                                        }



//
                                    } else if (jsonObj.getString("status").equalsIgnoreCase("no")) {
                                        Toast.makeText(getApplicationContext(), "Access permission denied to app", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid Details...", Toast.LENGTH_LONG).show();
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

                        params.put("u", usr);
                        params.put("p", psw);

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
    }
}
