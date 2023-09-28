package com.rahul.rahul_demo;


import static com.rahul.rahul_demo.MainActivity.h_faculty_name;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    TextView user_name;


    LinearLayout profilepage, studentDetails, faceRecognition, facultySubject;
    Button logout;
    ImageView profile_photo;
    String userID = MainActivity.h_faculty_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ui_xmltojava_connect_D();
        setDashBoard();




        Intent intent = getIntent();


        profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iProfile = new Intent(home.this, ProfilePage.class);
                SharedPreferences prefs =  getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("faculty_name",h_faculty_name);
                startActivity(iProfile);

                Intent intent = new Intent(home.this, ProfilePage.class);
                intent.putExtra("user_id", userID);
                startActivity(intent);
            }
        });

        studentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent istdDetails = new Intent(home.this,student_details.class);
                startActivity(istdDetails);
            }
        });

        facultySubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ifacultySubject = new Intent(home.this,facultySubject.class);
                startActivity(ifacultySubject);
            }
        });

        faceRecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ifaceRec = new Intent(home.this,faceRecognition.class);
                startActivity(ifaceRec);
            }
        });
    }

    private void setDashBoard() {
        user_name.setText("HELLO \n"+ h_faculty_name);
    }

    private void ui_xmltojava_connect_D() {
        user_name = (TextView) findViewById(R.id.facultyname);
        profilepage = (LinearLayout) findViewById(R.id.profileedit);
        studentDetails = (LinearLayout) findViewById(R.id.student_details);
        faceRecognition = (LinearLayout) findViewById(R.id.face_Recognition);
        facultySubject = (LinearLayout) findViewById(R.id.faculty_subjects);
    }


}