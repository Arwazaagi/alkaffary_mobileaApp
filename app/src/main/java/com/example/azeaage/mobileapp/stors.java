package com.example.azeaage.mobileapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import objects.Branches;

public class stors extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Branches [] branches;
    LatLng [] latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stors);
        branches =new Branches[5];
        latLng=new LatLng[5];
         latLng[0] = new LatLng(24.719340, 46.768753);
         latLng[1] = new LatLng(24.657844, 46.813284);
         latLng[2] = new LatLng(24.632667, 46.777195);;
         latLng[3] = new LatLng(24.61261, 46.704022);
         latLng[4] = new LatLng(24.614929, 46.7059);
        branches[0]=new Branches(1,"Alkaffary","Riyadh","",latLng[0]);
        branches[1]=new Branches(2,"Sulay Mustoda 1","Riyadh","",latLng[1]);
        branches[2]=new Branches(3,"Uweda","Riyadh","",latLng[2]);
        branches[3]=new Branches(4,"Al Rajhi-Bin Dail Mall","Riyadh","",latLng[3]);
        branches[4]=new Branches(5,"Bindayel","Riyadh","",latLng[4]);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng riyadh = new LatLng(24.713415, 46.675243);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(riyadh)             // Sets the center of the map to Mountain View
                .zoom(10)                   // Sets the zoom
                .bearing(0)                 // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        for(int i=0;i<5;i++) {
            mMap.addMarker(new MarkerOptions().position(branches[i].getCoordinates()).title(branches[i].getBranchName() + " " + branches[i].getBranchCity()));
        }
       //while(cameraPosition.zoom==10)

        System.out.println("++++++++++++"+cameraPosition.zoom+" "+cameraPosition.bearing+" "+cameraPosition.tilt);

    }
}
