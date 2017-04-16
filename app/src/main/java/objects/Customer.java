package objects;

import android.os.Parcel;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by AZeaage on 3/26/2017.
 */

public class Customer implements Serializable{
    private String full_name ;
    private String email ;
    private String firstPhone ;
    private String secondPhone;
    private String password ;
    private LatLng coordinates;

    public Customer(String full_name, String email, String firstPhone, String secondPhone, String password) {
        this.full_name = full_name;
        this.email = email;
        this.firstPhone = firstPhone;
        this.secondPhone = secondPhone;
        this.password = password;
    }

    public Customer(String full_name, String email, String firstPhone, String secondPhone, String password, LatLng coordinates) {
        this.full_name = full_name;
        this.email = email;
        this.firstPhone = firstPhone;
        this.secondPhone = secondPhone;
        this.password = password;
        this.coordinates = coordinates;
    }

    protected Customer(Parcel in) {
        full_name = in.readString();

        email = in.readString();
        firstPhone = in.readString();
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
        return full_name;
    }

    public void setFirst_name(String first_name) {
        this.full_name = first_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public void setLast_name(String last_name) {
        this.full_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return firstPhone;
    }

    public void setPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", firstPhone='" + firstPhone + '\'' +
                ", secondPhone='" + secondPhone + '\'' +
                ", password='" + password + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
