package com.rahul.rahul_demo;

import android.content.Context;
import android.content.SharedPreferences;

import com.rahul.rahul_demo.pojo.User;

public class SharedPrefManager {

    //private static final String RAHUL = "non";
    private static String  SHARED_PREF_NAME = "rahulsalunke";
    private SharedPreferences sharedPreferences;

    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    void SaveFaculty(User user){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("link", user.getLink());
        editor.putString("message", user.getMessage());
        editor.putString("users_id",user.getUsersId());
        editor.putString("role_id",user.getRoleId());
        editor.putString("fullname", user.getFullname());
        editor.putString("username", user.getUsername());


        editor.putString("email_id",user.getEmailId());
        editor.putString("phone_no",user.getPhoneNo());
        editor.putString("password", user.getPassword());
        editor.putString("profile_photo", (String) user.getProfilePhoto());
        editor.putString("gender",user.getGender());
       // editor.putString("age"),user.getAge();
      //  editor.putString("address"),user.getAddress();
      //  editor.putString("dor"),user.getDor();


        editor.putString("status",user.getStatus());

        editor.putBoolean("logged",true);
        editor.apply();




    }

    boolean isLoggedIn(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }

    public User getFacultyDetails(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  new User(sharedPreferences.getString("users_id", null),
                sharedPreferences.getString("role_id",null),
                sharedPreferences.getString("fullname",null),
                sharedPreferences.getString("username",null),

                sharedPreferences.getString("email_id",null),
                sharedPreferences.getString("phone_no",null),
                sharedPreferences.getString("password",null),


                sharedPreferences.getString("faculty_specialization",null),
                sharedPreferences.getString("faculty_experience",null),
                sharedPreferences.getString("faculty_personal_email",null),
                sharedPreferences.getString("profile_photo",null),
                sharedPreferences.getString("age",null),
                sharedPreferences.getString("address",null),
                sharedPreferences.getString("dor",null),


                sharedPreferences.getString("faculty_status",null)



        );


    }

    void logout(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

