package com.example.azeaage.mobileapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by AZeaage on 3/26/2017.
 */

public class User {
    private String first_name ;
    private String last_name ;
    private String email ;
    private String phone ;
    private String password ;
    private LatLng latLng;



    public User() {
        this.first_name = "";
        this.last_name = "";
        this.email = "";
        this.phone = "";
        this.password = "";
        this.latLng = null;
    }

    public User(String first_name, String last_name, String email, String phone, String password, LatLng latLng) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.latLng = latLng;
    }

    public User(String first_name, String last_name, String email, String phone, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", latLng=" + latLng +
                '}';
    }
}
