package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b;
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button);
        ed=(EditText)findViewById(R.id.editText);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed.setText(sh.getString("ip",""));
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String ip=ed.getText().toString();
        if(ip.length()==0){
            ed.setError("IP Address required");
        }
        else {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ip);
            ed.putString("url", "http://" + ip + ":8000");
            ed.commit();
            Intent ij = new Intent(getApplicationContext(), login.class);
            startActivity(ij);
        }
    }
}