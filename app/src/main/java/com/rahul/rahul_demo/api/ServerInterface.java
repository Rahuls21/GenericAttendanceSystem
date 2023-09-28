package com.rahul.rahul_demo.api;

import com.rahul.rahul_demo.pojo.FacultyUpdate;
import com.rahul.rahul_demo.pojo.UpdatePassResponse;
import com.rahul.rahul_demo.pojo.UpdatePhotoResponse;
import com.rahul.rahul_demo.pojo.User;
import com.rahul.rahul_demo.pojo.fetchStudentResponse;
import com.rahul.rahul_demo.pojo.fetchSubjectResponse;
import com.rahul.rahul_demo.pojo.reteriveprofileresponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ServerInterface {
      static String BASE_URL = "https://ganericsystemworld.com/ganericsystemworld.com/Rahul/";
    //static String BASE_URL = "https://harish1018.000webhostapp.com/faculty_app/";
   // static String BASE_URL = "https://harish1018.000webhostapp.com/Rahul/";

    @FormUrlEncoded
    @POST("simplelogin2.php")
    public Call<User> getUserLogin(
            @Field("email_id") String email_id,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("facultyChangePassword.php")
    Call<UpdatePassResponse> updateFacultypass(
            @Field("email_id") String email_id,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("facultyupdate.php")
    public Call<FacultyUpdate> facultyDetailsUpdate(
            @Field("users_id") String users_id,
            @Field("fullname") String fullname,
            @Field("email_id") String email_id,
            @Field("phone_no") String phone_no,
            @Field("age") String age,
            @Field("address") String address


    );
/*
    @Multipart
  //  @POST("fu1.php")
    @POST("update_photo.php")
    Call<UpdatePhotoResponse> updateFacultyPhoto(
            @Part MultipartBody.Part photo,
            @Part("users_id") RequestBody users_id
    );

   */

    @Multipart
    @POST("update_photo.php")
    Call<UpdatePhotoResponse> updateFacultyPhoto(
            @Part MultipartBody.Part photo, String userID);


    @GET("fetchstudent.php")
    public Call<fetchStudentResponse> fetchAllStudent();


    @GET("fetchSubject.php")
    public Call<fetchSubjectResponse> fetchFacultySubject();

    @GET("get_photo.php")
    Call<reteriveprofileresponse> getPhoto(@Query("users_id") int users_Id);

    }
