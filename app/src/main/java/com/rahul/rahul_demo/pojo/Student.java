package com.rahul.rahul_demo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("role_id")
    @Expose
    private String roleId;
    @SerializedName("college_id")
    @Expose
    private String collegeId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("semesters")
    @Expose
    private String semesters;
    @SerializedName("profile_photo")
    @Expose
    private String profilePhoto;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("dor")
    @Expose
    private String dor;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     */
    public Student() {
    }

    /**
     * @param address
     * @param gender
     * @param roleId
     * @param dor
     * @param emailId
     * @param phoneNo
     * @param password
     * @param profilePhoto
     * @param collegeId
     * @param usersId
     * @param fullname
     * @param semesters
     * @param age
     * @param username
     * @param status
     */
    public Student(String usersId, String roleId, String collegeId, String fullname, String username, String emailId, String phoneNo, String password, String semesters, String profilePhoto, String gender, String age, String address, String dor, String status) {
        super();
        this.usersId = usersId;
        this.roleId = roleId;
        this.collegeId = collegeId;
        this.fullname = fullname;
        this.username = username;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.password = password;
        this.semesters = semesters;
        this.profilePhoto = profilePhoto;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.dor = dor;
        this.status = status;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSemesters() {
        return semesters;
    }

    public void setSemesters(String semesters) {
        this.semesters = semesters;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
