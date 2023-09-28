package com.rahul.rahul_demo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class fetchStudentResponse {

    @SerializedName("users")
    @Expose
    private List<Student> users;
    @SerializedName("error")
    @Expose
    private String error;

    /**
     * No args constructor for use in serialization
     *
     */
    public fetchStudentResponse() {
    }

    /**
     *
     * @param error
     * @param users
     */
    public fetchStudentResponse(List<Student> users, String error) {
        super();
        this.users = users;
        this.error = error;
    }

    public List<Student> getUsers() {
        return users;
    }

    public void setUsers(List<Student> users) {
        this.users = users;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}



