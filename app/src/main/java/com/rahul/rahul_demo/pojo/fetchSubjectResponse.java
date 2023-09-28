package com.rahul.rahul_demo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class fetchSubjectResponse {
    @SerializedName("subject")
    @Expose
    private List<Subject> subject;
    @SerializedName("error")
    @Expose
    private String error;


    public fetchSubjectResponse(List<Subject> subject, String error) {
        super();
        this.subject = subject;
        this.error = error;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}



