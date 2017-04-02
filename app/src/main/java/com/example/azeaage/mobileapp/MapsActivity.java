package com.example.azeaage.mobileapp;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import objects.Customer;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private BroadcastReceiver broadcastReceiver;
    String location ="";
    Customer customer;
    private LatLng center;
    private Circle circle;
    private MarkerOptions marker;
    EditText location_et;
    Button continuous_button,search_b;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer user) {
        this.customer = user;
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    location=intent.getExtras().get("coordinates").toString();

                  //  user.setLatLng((LatLng) intent.getExtras().get("coordinates"));

                }

            };}
            registerReceiver(broadcastReceiver,new IntentFilter("location_update"));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.WRITE_CALENDAR);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //this method will start the map service
         location_et =(EditText)findViewById(R.id.search);
        customer=Register_Activity.new_Customer;
        search_b= (Button)findViewById(R.id.search_b);
        continuous_button=(Button)findViewById(R.id.continuous_button);
        search_b.setOnClickListener(this);
        continuous_button.setOnClickListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                takeLocation();
            }
            else{
                runtime_permissions();
            }
        }
    }

    private void takeLocation() {
        Intent i = new Intent(getApplication(),GPS_Service.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

// Launch the new activity and add the additional flags to the intent

     getApplication().startActivity(i);


    }


    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getApplication(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void onSearch(View view){

        String location =location_et.getText().toString();
        List<Address>addressesList=null;
        if(location==null ||location.equals("")){


            Geocoder geocoder = new Geocoder(this);
            try {
               addressesList= geocoder.getFromLocationName(location,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressesList.get(0);//lat , long
            LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
            if(marker!=null){
                mMap.clear();
            }
            else{
            marker=new MarkerOptions().position(latLng).title("Marker");
            mMap.addMarker(marker);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            customer.setLatLng(latLng);}
        }
        else{

        }
    }

   /* public void onGpsStatusChanged(int event){
        switch (event){
            case GpsStatus.GPS_EVENT_FIRST_FIX:
                Log.d(getFragmentManager(),"GPS First Fix");
                break;
            case GpsStatus.GPS_EVENT_STARTED:
                Log.d(MapsActivity,"GPS First Started");
                break;
            case GpsStatus.GPS_EVENT_STOPPED:
                Log.d(MapsActivity,"GPS First Stopped");
                break;
            case GpsStatus.GPS_EVENT_SATELLITE_STATUS:

                break;

        }
    }*/
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
        // initially the focus will be on Riyadh city.
        LatLng riyadh = new LatLng(24.6796205, 46.6981272);
       // mMap.addMarker(new MarkerOptions().position(riyadh).title("Marker in Riyadh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(riyadh));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                //Log.d("WorkArea*******","in map onCLICKLISTER @@@@@@@@@@@@@@");
                // TODO Auto-generated method stub
                double lat = point.latitude;
                double lng = point.longitude;
                center = null;

                //center = new LatLng(lat,lng);// PARAMETER point;
                center = point;
                System.out.println("locationnnnn of click &&&&&&&&&&&"+center);
                if(marker!=null){
                    mMap.clear();
                }

                marker=new MarkerOptions().position(center).title("your location ");

                mMap.addMarker(marker);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12));



            }
        });



        Criteria criteria = new Criteria();
        // get last known location
        Location location =   locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

        // create variable to store the user's location
        UserLocation currentLocation = new UserLocation();

        // set values of our location variable
        currentLocation.setLatitude(location.getLatitude());
        currentLocation.setLongitude(location.getLongitude());
        //set the location for the user
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        customer.setLatLng(latLng);
        if(marker!=null){
            mMap.clear();
        }

            marker = new MarkerOptions().position(latLng).title("your location ");
            mMap.addMarker(marker);
            System.out.println("user_Name&&&&&&&&&&&" + customer);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.search_b)
        onSearch(v);
        else if(v.getId()==R.id.continuous_button)
        {
            Intent main_intent = new Intent(this,MainActivity.class);
            startActivity(main_intent);
            finish();
        }
    }
}
