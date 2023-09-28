package com.rahul.rahul_demo;


import static com.rahul.rahul_demo.MainActivity.h_faculty_address;
import static com.rahul.rahul_demo.MainActivity.h_faculty_age;
import static com.rahul.rahul_demo.MainActivity.h_faculty_email;
import static com.rahul.rahul_demo.MainActivity.h_faculty_id;
import static com.rahul.rahul_demo.MainActivity.h_faculty_mobile;
import static com.rahul.rahul_demo.MainActivity.h_faculty_name;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rahul.rahul_demo.api.RetrofitClient;
import com.rahul.rahul_demo.pojo.FacultyUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends AppCompatActivity {

    EditText et_fullname,et_email_id, et_phoneNo,et_age,et_address;

    String users_id;
    ImageView imageViewProfile, imageViewHome;
    private static final int REQUEST_IMAGE_GALLERY = 1;

    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;


    Button update, cancel;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);




        ui_xmltojava_connect_P();
        //Do not miss place this
        getFacultyData();

       // prefs = getSharedPreferences("login", MODE_PRIVATE);
        //editor = prefs.edit();
//         String users_id = prefs.getString("usersid", "");
    //      final String s_users_password = prefs.getString("userspassword", "");

        sharedPrefManager = new SharedPrefManager(getApplicationContext());


        //  SharedPreferences prefs =  getSharedPreferences("login", MODE_PRIVATE);
        //  SharedPreferences.Editor editor = prefs.edit();

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);

        // Fetch the saved faculty information
        users_id = prefs.getString("faculty_id", "");


        Log.d("vishal", "facultyId: " + users_id);


        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code to execute when the image view is clicked
                Intent mvFacultyPhotoUpdate = new Intent(ProfilePage.this,FacultyPhotoUpdate.class);
                startActivity(mvFacultyPhotoUpdate);
            }
        });

        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code to execute when the image view is clicked
                Intent mvHome = new Intent(ProfilePage.this,home.class);
                startActivity(mvHome);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePage.this, home.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateFaculty();

            }

        });


    }

    // Method to show the dialog to choose to either pick a photo from the gallery or take a new photo

    // Intent intent = getIntent();

    private void updateFaculty() {


        String fullname = et_fullname.getText().toString().trim();
        String email_id = et_email_id.getText().toString().trim();
        String phone_no = et_phoneNo.getText().toString().trim();
        String age      = et_age.getText().toString().trim();
        String address   = et_address.getText().toString().trim();


        if (fullname.isEmpty()) {
            et_fullname.setError("Please enter your Full Name");
            et_fullname.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_id).matches()) {
            et_email_id.setError("Please enter your personal email");
            et_email_id.requestFocus();
            return;
        }
        if (phone_no.isEmpty()) {
            et_phoneNo.setError("Please enter your mobile no");
            et_phoneNo.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            et_age.setError("Please enter your Age");
            et_age.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            et_address.setError("Please enter your Address");
            et_address.requestFocus();
            return;
        }



        Call<FacultyUpdate> call = RetrofitClient
                .getInstance()
                .getMyApi()
                .facultyDetailsUpdate(h_faculty_id, fullname , email_id, phone_no,
                        age, address
                );


        call.enqueue(new Callback<FacultyUpdate>() {
            @Override
            public void onResponse(Call<FacultyUpdate> call, Response<FacultyUpdate> response) {

                FacultyUpdate facultyUpdate = response.body();
                if (response.isSuccessful()) {
                    if (facultyUpdate.getError().equals("200")) {
                        sharedPrefManager.SaveFaculty(facultyUpdate.getFaculty());
                        MainActivity.saveInfo(facultyUpdate.getFaculty());
                        Toast.makeText(ProfilePage.this, facultyUpdate.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("vishal", facultyUpdate.getMessage());
                    } else {
                        Toast.makeText(ProfilePage.this, facultyUpdate.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("vishal", facultyUpdate.getMessage());
                    }
                } else {
                    Toast.makeText(ProfilePage.this, "failed", Toast.LENGTH_SHORT).show();
                    Log.d("vishal", facultyUpdate.getMessage());
                }
            }

            @Override
            public void onFailure(Call<FacultyUpdate> call, Throwable t) {
                Toast.makeText(ProfilePage.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("vishal", t.getMessage());
            }
        });
    }


    private void getFacultyData() {

        et_fullname.setText(h_faculty_name);
        et_email_id.setText(h_faculty_email);
        et_phoneNo.setText(h_faculty_mobile);
        et_age.setText(h_faculty_age);
        et_address.setText(h_faculty_address);

    }

    private void ui_xmltojava_connect_P() {


        et_fullname = (EditText) findViewById(R.id.fullName);
        et_email_id = (EditText) findViewById(R.id.EmailID);
        et_phoneNo = (EditText) findViewById(R.id.phoneNo);
        et_age = (EditText) findViewById(R.id.age);
        et_address = (EditText) findViewById(R.id.address);


        update = (Button) findViewById(R.id.update);
        cancel = (Button) findViewById(R.id.cancel);

        imageViewProfile = findViewById(R.id.imageViewProfile);
        imageViewHome = findViewById(R.id.imageViewHome);


    }


}