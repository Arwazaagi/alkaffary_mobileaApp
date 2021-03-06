package com.example.azeaage.mobileapp.fragments;


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
import android.widget.Toast;

import com.example.azeaage.mobileapp.MainActivity;
import com.example.azeaage.mobileapp.R;
import com.example.azeaage.mobileapp.Services.GPS_Service;
import com.example.azeaage.mobileapp.UserLocation;
import com.example.azeaage.mobileapp.background;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    Button continuous_button;
    private LatLng latLng;
    String DocNum;
    char callActivity;
    GPS_Service gps;
    String url="http://hr.alkaffary.com:664/AlKaffaryMobileService.svc?wsdl";

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
        Intent intent =getIntent();
        customer=(Customer) intent.getSerializableExtra("customer");
        DocNum=(String) getIntent().getSerializableExtra("docNum");
        System.out.println(DocNum+" doc num");
        callActivity=(char)getIntent().getSerializableExtra("setInvoiceLocation");

        System.out.println(callActivity+" callActivity");
        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.WRITE_CALENDAR);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //this method will start the map service

       // customer=Register_Activity.new_Customer;
        System.out.println("NewUser"+customer);
        continuous_button=(Button)findViewById(R.id.continuous_button);

        continuous_button.setOnClickListener(this);


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode ==100){

            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                takeLocation();
            }
        }
    }

    private void takeLocation() {
        Intent i = new Intent(getApplication(),GPS_Service.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

// Launch the new activity and add the additional flags to the intent

    // getApplication().startActivity(i);


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
        runtime_permissions();

    }

   /* private void onSearch(View view){

        String location =location_et.getText().toString();
        List<Address>addressesList=null;
        if(location.equals("")){


            Geocoder geocoder = new Geocoder(this);
            try {
               addressesList= geocoder.getFromLocationName(location,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert addressesList != null;
            Address address = addressesList.get(0);//lat , long
            latLng = new LatLng(address.getLatitude(),address.getLongitude());
            if(marker!=null){
                mMap.clear();
            }
            else{
            setLocation(latLng);
            }
        }

    }
*/
    private void setLocation(LatLng point) {
        mMap.clear();
        marker=new MarkerOptions().position(point).title("Marker");
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,18));
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
        // initially the focus will be on Riyadh city.
        LatLng riyadh = new LatLng(24.6796205, 46.6981272);
       // mMap.addMarker(new MarkerOptions().position(riyadh).title("Marker in Riyadh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(riyadh,10));
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
        //take the location by click in the map
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                //Log.d("WorkArea*******","in map onCLICKLISTER @@@@@@@@@@@@@@");
                // TODO Auto-generated method stub
                center = null;

                //center = new LatLng(lat,lng);// PARAMETER point;
                center = point;

                latLng=center;
                if(marker!=null){
                    mMap.clear();
                }

                marker=new MarkerOptions().position(center).title("your location ");
                mMap.addMarker(marker);
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12));
            }
        });

      //  setCurrentLocation() ;

      /*  Criteria criteria = new Criteria();
        // get last known location
        Location location =   locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

        // create variable to store the user's location
        UserLocation currentLocation = new UserLocation();
      //  System.out.println(location.getLatitude()+","+location.getLongitude()+"    current location ");
        // set values of our location variable
        currentLocation.setLatitude(location.getLatitude());
        currentLocation.setLongitude(location.getLongitude());
        //set the location for the user
        latLng = new LatLng(location.getLatitude(),location.getLongitude());
        setLocation(latLng);*/

    }
    private void setCurrentLocation() {
        gps = new GPS_Service(MapsActivity.this);

        if(gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(
                    getApplicationContext(),
                    "Your Location is -\nLat: " + latitude + "\nLong: "
                            + longitude, Toast.LENGTH_LONG).show();
            System.out.println("My location"+gps.getLatitude());
          LatLng latLng=new LatLng(latitude,longitude);
            setLocation(latLng);

        } else {
            gps.showSettingsAlert();
        }
    }
    @Override
    public void onClick(View v) {

       /* if(v.getId()==R.id.search_b)
        onSearch(v);*/
         if(v.getId()==R.id.continuous_button)
        {
            if(callActivity=='I'){
                background b=new background(this);

                    ///pass the location of the customer
                    if(latLng!=null){
                        String result= null;
                        try {
                            result = b.execute("SaveGPSLocation",DocNum, latLng.latitude+"",latLng.longitude+"").get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(this,result,Toast.LENGTH_LONG).show();

            }
            else {
                        Toast.makeText(this,"يجب عليك تحديد موقع لتوصيل الطلب ",Toast.LENGTH_LONG).show();

                    }}
            else {
            customer.setLatLng(latLng);
            System.out.println(customer+"   new customer ");
            background b1=new background(this);
            try {
               String result= b1.execute("register",customer.getFull_name(),customer.getEmail(),customer.getFirstPhone(),customer.getSecondPhone(),customer.getPassword(),"Riyadh","Riyadh",customer.getCoordinates().toString()).get();
               Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            //insert the customer to database
            Intent main_intent = new Intent(this,MainActivity.class);
            startActivity(main_intent);
            finish();}
        }
    }
}
