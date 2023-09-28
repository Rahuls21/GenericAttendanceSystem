package com.rahul.rahul_demo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahul.rahul_demo.api.RetrofitClient;
import com.rahul.rahul_demo.pojo.Subject;
import com.rahul.rahul_demo.pojo.fetchSubjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class facultySubject extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Subject> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_subject);

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(facultySubject.this));

        //reterofit
        Call<fetchSubjectResponse> call = RetrofitClient.getInstance().getMyApi().fetchFacultySubject();

        call.enqueue(new Callback<fetchSubjectResponse>() {
            @Override
            public void onResponse(Call<fetchSubjectResponse> call, Response<fetchSubjectResponse> response) {

                if (response.isSuccessful()){
                    subjectList = response.body().getSubject();
                   // userList=response.body().getSubject();
                    recyclerView.setAdapter(new SubjectAdapter(facultySubject.this,subjectList));

                }else {
                    Toast.makeText(facultySubject.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<fetchSubjectResponse> call, Throwable t) {
                Toast.makeText(facultySubject.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }




}