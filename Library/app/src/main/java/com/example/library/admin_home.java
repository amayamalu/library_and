package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class admin_home extends AppCompatActivity {
    LinearLayout l_course, l_stud, l_cat, l_book, l_borrow, l_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        l_course=findViewById(R.id.l1);
        l_stud=findViewById(R.id.l2);
        l_cat=findViewById(R.id.l3);
        l_book=findViewById(R.id.l4);
        l_borrow=findViewById(R.id.l5);
        l_logout=findViewById(R.id.l6);

        l_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(), admin_view_course.class);
                startActivity(ij);
            }
        });

        l_stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(), admin_view_student.class);
                startActivity(ij);
            }
        });

        l_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(), admin_view_category.class);
                startActivity(ij);
            }
        });

        l_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(),admin_view_book.class);
                startActivity(ij);

            }
        });

        l_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ij=new Intent(getApplicationContext(), admin_borrow_books.class);
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
        startActivity(new Intent(getApplicationContext(), admin_home.class));
        super.onBackPressed();
    }
}