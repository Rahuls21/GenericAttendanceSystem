package com.rahul.rahul_demo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {

    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("specialization_id")
    @Expose
    private Object specializationId;
    @SerializedName("semester_id")
    @Expose
    private Object semesterId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("subject_credit")
    @Expose
    private String subjectCredit;
    @SerializedName("subject_code")
    @Expose
    private String subjectCode;
    @SerializedName("subject_type")
    @Expose
    private String subjectType;

    /**
     * No args constructor for use in serialization
     */
    public Subject() {
    }

    /**
     * @param semesterId
     * @param subjectCredit
     * @param specializationId
     * @param subjectCode
     * @param subjectType
     * @param subjectId
     * @param subjectName
     */
    public Subject(String subjectId, Object specializationId, Object semesterId, String subjectName, String subjectCredit, String subjectCode, String subjectType) {
        super();
        this.subjectId = subjectId;
        this.specializationId = specializationId;
        this.semesterId = semesterId;
        this.subjectName = subjectName;
        this.subjectCredit = subjectCredit;
        this.subjectCode = subjectCode;
        this.subjectType = subjectType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Object getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Object specializationId) {
        this.specializationId = specializationId;
    }

    public Object getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Object semesterId) {
        this.semesterId = semesterId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCredit() {
        return subjectCredit;
    }

    public void setSubjectCredit(String subjectCredit) {
        this.subjectCredit = subjectCredit;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }


}
