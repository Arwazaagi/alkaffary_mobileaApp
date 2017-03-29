package com.example.azeaage.mobileapp;
/**
 * Created by AZeaage on 3/26/2017.
 */

public class UserLocation {
    private String name; // name of the location
    private double latitude; // latitude (-90 to +90)
    private double longitude; // longitude (-180 to +180)
    private String address; // address of the location

    public UserLocation(String name, double latitude, double longitude, String address) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public UserLocation() {
        this.name = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.address = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}