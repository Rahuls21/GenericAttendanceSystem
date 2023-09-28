package com.rahul.rahul_demo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rahul.rahul_demo.api.RetrofitClient;
import com.rahul.rahul_demo.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    EditText etemailid, etpassword;
    TextView changePassword;
    Button loginbtn;
    String emailid, password;
    static final String KEY_EMPTY = "";

    String users_id;

    String userID = MainActivity.h_faculty_id;

    ProgressDialog pDialog;
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;




    // SharedPrefManager sharedPrefManager;
    public static String    h_faculty_id, h_role_id ,h_faculty_name, h_faculty_username,
                             h_faculty_email,h_faculty_mobile,h_faculty_password,h_faculty_DOB,
                            h_faculty_specialization, h_faculty_experience,h_faculty_personal_email,
                            h_faculty_photo,h_faculty_status,h_faculty_gender,h_faculty_10th_pctg,
                            h_faculty_12th_pctg,h_faculty_UG_degree,h_faculty_UG_cgpa,h_faculty_PG_degree,
                            h_faculty_PG_cgpa,h_faculty_dor,h_faculty_age,h_faculty_address;
    public static String g_p_s1 = "No network connection available.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("login", MODE_PRIVATE);
        editor = prefs.edit();
        users_id = prefs.getString("usersid", "");


        final String s_users_password = prefs.getString("userspassword", "");
        //Already Loged In
        if (users_id.length() > 0 && s_users_password.length() > 0) {
            Intent intent = new Intent(MainActivity.this, home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        ui_xmltojava_connect_L();
        validInputs_L();


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChangePass = new Intent(MainActivity.this,ChangePassword.class);
                startActivity(iChangePass);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isDeviceOnline()) {
                    activate_online_device();
                } else {
                    //Retrieve the data entered in the edit texts
                    emailid = etemailid.getText().toString().trim();
                    password = etpassword.getText().toString().trim();
                    if (validInputs_L()) {
                        login();

                    }
                }
            }
        });



    }



    private void login() {

        displayLoader();


        Call<User> call = RetrofitClient
                .getInstance()
                .getMyApi()
                .getUserLogin(emailid, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                pDialog.dismiss();
                User users = response.body();
                parseLoginData(users);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //By this method we will retrieve  the values from json Object
    private void parseLoginData(User response) {
        try {
            if (response.getLink().equals("true")) {

                saveInfo(response);

                Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Intent iHome = new Intent(MainActivity.this, home.class);

                iHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(iHome);

                this.finish();
            } else {
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                cleartext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void activate_online_device() {
        AlertDialog.Builder alert112 = new AlertDialog.Builder(this);
        alert112.setTitle("Network Error");
       // alert112.setIcon(R.drawable.logo);
        alert112.setMessage(g_p_s1);
        alert112.setPositiveButton("Activate Internet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface alert, int which) {
                //Do something
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                startActivityForResult(settingsIntent, 9003);
                alert.dismiss();
            }
        });
        alert112.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert112.show();
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void cleartext() {
        etemailid.setText("");
        etpassword.setText("");
    }



    public static void saveInfo(User data) {


        editor.putString("link", data.getLink());
        editor.putString("message", data.getMessage());
        editor.putString("users_id",data.getUsersId());
        editor.putString("role_id",data.getRoleId());
        editor.putString("fullname", data.getFullname());
        editor.putString("username", data.getUsername());


        editor.putString("email_id",data.getEmailId());
        editor.putString("phone_no",data.getPhoneNo());
        editor.putString("password", data.getPassword());
        editor.putString("profile_photo", (String) data.getProfilePhoto());
        editor.putString("gender",data.getGender());
/*
        editor.putString("faculty_id", data.getFacultyId());
        editor.putString("role_id", data.getRoleId());
        editor.putString("faculty_name", data.getFacultyName());
        editor.putString("faculty_username", data.getFacultyUsername());
        editor.putString("faculty_email", data.getFacultyEmail());
        editor.putString("faculty_mobile", data.getFacultyMobile());
        editor.putString("faculty_password", data.getFacultyPassword());
        editor.putString("faculty_DOB", data.getFacultyDOB());
        editor.putString("faculty_specialization", data.getFacultySpecialization());
        editor.putString("faculty_experience", data.getFacultyExperience());

        editor.putString("faculty_personal_email", data.getFacultyPersonalEmail());
        editor.putString("faculty_photo", (String) data.getFacultyPhoto());
        editor.putString("faculty_status", data.getFacultyStatus());
        editor.putString("faculty_gender", data.getFacultyGender());
        editor.putString("faculty_10th_pctg", data.getFaculty10thPctg());
        editor.putString("faculty_12th_pctg", data.getFaculty12thPctg());
        editor.putString("faculty_UG_degree", data.getFacultyUGDegree());
        editor.putString("faculty_UG_cgpa", data.getFacultyUGCgpa());
        editor.putString("faculty_PG_degree", data.getFacultyPGDegree());
        editor.putString("faculty_PG_cgpa", data.getFacultyPGCgpa());
        editor.putString("faculty_dor", data.getFacultyDor());
*/
        //Data for Dashboard
        h_faculty_id = data.getUsersId().trim();
        h_role_id = data.getRoleId().trim();
        h_faculty_name = data.getFullname().trim();
        h_faculty_username = data.getUsername().trim();
        h_faculty_email = data.getEmailId().trim();
        h_faculty_mobile = data.getPhoneNo().trim();
        h_faculty_password = data.getPassword();
        h_faculty_photo = (String) data.getProfilePhoto();
        h_faculty_status = data.getStatus().trim();
        h_faculty_gender = data.getGender();
        h_faculty_dor = data.getDor();
        h_faculty_age = data.getAge();
        h_faculty_address = data.getAddress();


        editor.commit();
    }

    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean validInputs_L() {
        if (KEY_EMPTY.equals(emailid)) {
            etemailid.setError("EmailId cannot be empty");
            etemailid.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            etpassword.setError("Password cannot be empty");
            etpassword.requestFocus();
            return false;
        }
        return true;
    }



    private void ui_xmltojava_connect_L() {
        etemailid = (EditText) findViewById(R.id.lemailid);
        etpassword = (EditText) findViewById(R.id.lpassword);
        loginbtn = (Button) findViewById(R.id.login);
        changePassword = (TextView) findViewById(R.id.resetPassword);
    }
}