package com.rahul.rahul_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rahul.rahul_demo.api.RetrofitClient;
import com.rahul.rahul_demo.pojo.UpdatePhotoResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyPhotoUpdate extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;

    private ImageView facultyImageView;
    private Button uploadButton;
    private ProgressBar progressBar;

    private RetrofitClient retrofitClient;
    private Bitmap selectedBitmap;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_photo_update);

        facultyImageView = findViewById(R.id.facultyImageView);
        uploadButton = findViewById(R.id.ImageUpload);
        progressBar = findViewById(R.id.progressBar);

        retrofitClient = RetrofitClient.getInstance();

        // Retrieve the user ID from shared preferences
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userID = preferences.getString("user_id", "");

        facultyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_IMAGE_PICK);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedBitmap != null) {
                    uploadPhoto(selectedBitmap, userID);
                } else {
                    Toast.makeText(FacultyPhotoUpdate.this, "Please select an image first", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            try {
                Uri selectedImage = data.getData();
                selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                facultyImageView.setImageBitmap(selectedBitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(FacultyPhotoUpdate.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void uploadPhoto(Bitmap bitmap, String userID) {
        progressBar.setVisibility(View.VISIBLE);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
        MultipartBody.Part part = MultipartBody.Part.createFormData("photo", "photo.jpg", requestBody);

        retrofitClient.getMyApi().updateFacultyPhoto(part, userID).enqueue(new Callback<UpdatePhotoResponse>() {
            @Override
            public void onResponse(Call<UpdatePhotoResponse> call, Response<UpdatePhotoResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    UpdatePhotoResponse updatePhotoResponse = response.body();

                    if (updatePhotoResponse.isStatus()) {
                        Toast.makeText(FacultyPhotoUpdate.this, updatePhotoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FacultyPhotoUpdate.this, "Error: " + updatePhotoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FacultyPhotoUpdate.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePhotoResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(FacultyPhotoUpdate.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
