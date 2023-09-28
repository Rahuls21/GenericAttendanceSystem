package com.rahul.rahul_demo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultyUpdate {
    @SerializedName("faculty")
    @Expose
    private User faculty;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("message")
    @Expose
    private String message;


    public FacultyUpdate() {
    }

    public FacultyUpdate(User faculty, String error, String message) {
        super();
        this.faculty = faculty;
        this.error = error;
        this.message = message;
    }

    public User getFaculty() {
        return faculty;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
