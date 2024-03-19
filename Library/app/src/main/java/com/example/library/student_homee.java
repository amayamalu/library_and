package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class student_homee extends AppCompatActivity {

    LinearLayout l_course,l_cat,l_prev,l_logout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homee);

        l_course=findViewById(R.id.l1);
        l_cat=findViewById(R.id.l3);
        l_prev=findViewById(R.id.l4);
        l_logout=findViewById(R.id.l6);

        l_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(),student_view_cat_books.class);
                startActivity(ij);
            }
        });

        l_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(),student_previous_books.class);
                startActivity(ij);
            }
        });



        l_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(),Student_borrowed_books .class);
                startActivity(ij);
            }
        });





        l_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(), login.class);
                startActivity(ij);
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), student_homee.class));
        super.onBackPressed();
    }
}
