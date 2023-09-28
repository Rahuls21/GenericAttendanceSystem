package com.rahul.rahul_demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rahul.rahul_demo.api.RetrofitClient;
import com.rahul.rahul_demo.pojo.UpdatePassResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    String users_id, email_id;
    Button updateFacultyPassword;
    EditText cp_faculty_email, cp_faculty_password;

    SharedPrefManager sharedPrefManager;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        sharedPrefManager = new SharedPrefManager(this);

        users_id = sharedPrefManager.getFacultyDetails().getUsersId();
        email_id = sharedPrefManager.getFacultyDetails().getEmailId();

        Log.d("pwd", "id : " + users_id);
        Log.d("pwd", "email : " + email_id);

        ui_xmltojava_connect_ChangePassword();

        updateFacultyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void updatePassword() {
        String email_id = cp_faculty_email.getText().toString().trim();
        String password = cp_faculty_password.getText().toString().trim();

        if (email_id.isEmpty()) {
            cp_faculty_email.setError("Enter faculty email");
            cp_faculty_email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            cp_faculty_password.setError("Enter new Password");
            cp_faculty_password.requestFocus();
            return;
        }
        if (password.length() < 8) {
            cp_faculty_password.setError("Enter 8 digit Password");
            cp_faculty_password.requestFocus();
            return;
        }

        displayLoader();

        Call<UpdatePassResponse> call = RetrofitClient.getInstance().getMyApi()
                .updateFacultypass(email_id, password);

        call.enqueue(new Callback<UpdatePassResponse>() {
            @Override
            public void onResponse(Call<UpdatePassResponse> call, Response<UpdatePassResponse> response) {
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    UpdatePassResponse passwordResponse = response.body();

                    if (passwordResponse != null && passwordResponse.getError().equals("200")) {
                        Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                        Intent pcIntent = new Intent(ChangePassword.this, MainActivity.class);
                        startActivity(pcIntent);
                        finish(); // close this activity
                    } else {
                        Toast.makeText(ChangePassword.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangePassword.this, "Failed to change password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePassResponse> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(ChangePassword.this, "Failed to change password: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void ui_xmltojava_connect_ChangePassword() {

        //for update password
        cp_faculty_email =       (EditText)findViewById(R.id.updt_facultyEmail);
        cp_faculty_password =    (EditText)  findViewById(R.id.updt_facultyPassword);
        updateFacultyPassword =   (Button)  findViewById(R.id.changePassword);



    }

}