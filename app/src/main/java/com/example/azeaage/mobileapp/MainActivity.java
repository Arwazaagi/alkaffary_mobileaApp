package com.example.azeaage.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Offers_Fragment.OnFragmentInteractionListener,
        Products_Fragment.OnFragmentInteractionListener,
        Maintenance_Fragment.OnFragmentInteractionListener,
        Wholesale_Fragment.OnFragmentInteractionListener,
        Stores_Fragmant.OnFragmentInteractionListener,
        Contact_Fragment.OnFragmentInteractionListener,
        About_Fragment.OnFragmentInteractionListener{



    @Override
    public void onFragmentInteraction(String data) {
        this.getSupportActionBar().setTitle(data);
    }

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                  //  mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent shopping_intent =new Intent(getApplication(), shopping_cart.class);
                    startActivity(shopping_intent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notification_intent =new Intent(getApplication(), notification.class);
                    startActivity(notification_intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = getLayoutInflater().inflate(R.layout.nav_header_main, navigationView);
        TextView nameTV= (TextView)headerView.findViewById(R.id.name);
        nameTV.setText("Arwa alzeaagi");
        ImageView imageView =(ImageView)headerView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent =new Intent(getApplication(), Profile.class);
                startActivity(profile_intent);
                System.out.println("dfhajksdk66666666666666666666666");
            }
        });
      /*  LinearLayout linearLayout =(LinearLayout)headerView.findViewById(R.id.profile_nav);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopping_intent =new Intent(getApplication(), shopping_cart.class);
                startActivity(shopping_intent);
                System.out.println("dfhajksdk66666666666666666666666");
            }
        });*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_products) {
            ExpandableLVFragment ELVF= new ExpandableLVFragment();
            Toast.makeText(this,"ExpandableLVFragment",Toast.LENGTH_SHORT).show();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.RelativeLayout_for_Fragment,ELVF,ELVF.getTag()).commit();

        } else if (id == R.id.nav_offers) {
            Offers_Fragment offers_fragment= new Offers_Fragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.RelativeLayout_for_Fragment,offers_fragment,offers_fragment.getTag()).commit();

        } else if (id == R.id.nav_maintenance_installation) {
            Maintenance_Fragment maintenance_fragment= new Maintenance_Fragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.RelativeLayout_for_Fragment,maintenance_fragment,maintenance_fragment.getTag()).commit();
        } else if (id == R.id.nav_wholesale){
            Wholesale_Fragment wholesale_fragment= new Wholesale_Fragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.RelativeLayout_for_Fragment,wholesale_fragment,wholesale_fragment.getTag()).commit();
        }else if (id == R.id.nav_stores) {
            Stores_Fragmant stores_fragmant= new Stores_Fragmant();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.RelativeLayout_for_Fragment,stores_fragmant,stores_fragmant.getTag()).commit();

        } else if (id == R.id.nav_about) {
            About_Fragment about_fragment= new About_Fragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.RelativeLayout_for_Fragment,about_fragment,about_fragment.getTag()).commit();


        }else if (id == R.id.nav_contact) {
            Contact_Fragment contact_fragment= new Contact_Fragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.RelativeLayout_for_Fragment,contact_fragment,contact_fragment.getTag()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    //form open new fragment
    private void openFragment(Fragment fragment,int fragmentId){
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(fragmentId,fragment,fragment.getTag()).commit();

    }
}
