package objects;

import android.os.Parcel;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by AZeaage on 3/26/2017.
 */

public class Customer implements Serializable{
    private String first_name ;
    private String last_name ;
    private String email ;
    private String phone ;
    private String password ;
    private LatLng coordinates;

    public Customer() {
        this.first_name = "";
        this.last_name = "";
        this.email = "";
        this.phone = "";
        this.password = "";
        this.coordinates = null;
    }

    public Customer(String first_name, String last_name, String email, String phone, String password, LatLng coordinates) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.coordinates = coordinates;
    }

    public Customer(String first_name, String last_name, String email, String phone, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    protected Customer(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
        coordinates = in.readParcelable(LatLng.class.getClassLoader());
    }


    public LatLng getLatLng() {
        return coordinates;
    }

    public void setLatLng(LatLng coordinates) {
        this.coordinates = coordinates;
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
                ", coordinates=" + coordinates +
                '}';
    }



}
