package com.rahul.rahul_demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahul.rahul_demo.api.RetrofitClient;
import com.rahul.rahul_demo.pojo.Student;
import com.rahul.rahul_demo.pojo.fetchStudentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class student_details extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Student> userList;

    Button generate_qr_code_button;

    EditText feedback_link_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        generate_qr_code_button = (Button) findViewById(R.id.generate_qr_code_button);
        feedback_link_edit_text = (EditText) findViewById(R.id.feedback_link_edit_text);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(student_details.this));



        generate_qr_code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedbackLink = feedback_link_edit_text.getText().toString().trim();
                if (TextUtils.isEmpty(feedbackLink)) {
                    feedback_link_edit_text.setError("Feedback link is required");
                    feedback_link_edit_text.requestFocus();
                    return;
                }
                // Generate QR code with feedback link
                Intent intent = new Intent(student_details.this, QRCodeActivity.class);
                intent.putExtra("feedback_link", feedbackLink);
                startActivity(intent);
            }
        });


        /*

        generate_qr_code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedbackLink = feedback_link_edit_text.getText().toString().trim();
                if (TextUtils.isEmpty(feedbackLink)) {
                    feedback_link_edit_text.setError("Feedback link is required");
                    feedback_link_edit_text.requestFocus();
                    return;
                }

                // Get the selected student from the adapter
                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                Student student = userList.get(position);

                // Generate QR code with student details and feedback link
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode("Student ID: " + student.getUsersId() +
                            "\nEmail ID: " + student.getEmailId() +
                            "\nFeedback Link: " + feedbackLink, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(student_details.this, QRCodeActivity.class);
                    intent.putExtra("qr_code_bitmap", bitmap);
                    startActivity(intent);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        */



        //reterofit
        Call<fetchStudentResponse> call = RetrofitClient.getInstance().getMyApi().fetchAllStudent();

        call.enqueue(new Callback<fetchStudentResponse>() {
            @Override
            public void onResponse(Call<fetchStudentResponse> call, Response<fetchStudentResponse> response) {

                if (response.isSuccessful()){
                 //   userList = response.body().getUsers();
                    userList=response.body().getUsers();
                    recyclerView.setAdapter(new UserAdapter(student_details.this,userList));
                }else {
                    Toast.makeText(student_details.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<fetchStudentResponse> call, Throwable t) {
                Toast.makeText(student_details.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}