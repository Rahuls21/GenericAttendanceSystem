package com.rahul.rahul_demo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("role_id")
    @Expose
    private String roleId;
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
     *
     */
    public User(String faculty_id, String role_id, String faculty_name, String faculty_username, String faculty_email, String faculty_mobile, String faculty_password, String faculty_dob, String faculty_specialization, String faculty_experience, String faculty_personal_email, String faculty_photo, String faculty_status, String faculty_gender, String faculty_10th_pctg, String faculty_12th_pctg, String faculty_ug_degree, String faculty_ug_cgpa, String faculty_pg_degree, String faculty_pg_cgpa, String faculty_dor) {
    }

    /**
     *
     * @param address
     * @param gender
     * @param roleId
     * @param dor
     * @param link
     * @param emailId
     * @param message
     * @param phoneNo
     * @param password
     * @param profilePhoto
     * @param usersId
     * @param fullname
     * @param age
     * @param username
     * @param status
     */
    public User(String link, String message, String usersId, String roleId, String fullname, String username, String emailId, String phoneNo, String password, String profilePhoto, String gender, String age, String address, String dor, String status) {
        super();
        this.link = link;
        this.message = message;
        this.usersId = usersId;
        this.roleId = roleId;
        this.fullname = fullname;
        this.username = username;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.dor = dor;
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

}
