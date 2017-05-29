package com.alford.bushidoapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener
        ,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final int REQUEST_LOCATION = 11;


    private FirebaseAuth mFirebaseAuth;
    private TextView mUserEmailTextView;
    private TextView mLocationText;
    private Button mLogOutButton;

    private final static int CONNECTION_RES_REQUEST = 100;
    private GoogleApiClient mGoogleApiClient;
    private LocationListener mLocationListener;
    private LocationRequest mLocationRequest;
    private double currenLong;
    private double currentLat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

//
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .addApi(LocationServices.API)
//                    .build();

            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


            mLocationText = (TextView) findViewById(R.id.LOCATION);
            //getLocation().toString();


            Toolbar mainTool = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mainTool);


            mFirebaseAuth = FirebaseAuth.getInstance();

            if (mFirebaseAuth.getCurrentUser() == null) {
                finish();
                startActivity(new Intent(this, Login.class));
            }

            FirebaseUser user = mFirebaseAuth.getCurrentUser();


            //TODO DISPLAY USERNAME


            //String welcome = getResources().getString(R.string.Welcome);


            mUserEmailTextView = (TextView) findViewById(R.id.USEREMAIL);
            mUserEmailTextView.setText(String.format("%s%s", getResources()
                    .getString(R.string.welcome), user.getEmail()));

            // mLogOutButton = (Button) findViewById(R.id.LOGOUT_BTN);

//        mLogOutButton.setOnClickListener(this);


            BottomNavigationView bottomNavigationView = (BottomNavigationView)
                    findViewById(R.id.bottom_navigation);

            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {

                                case R.id.profile:
                                    break;


                                case R.id.projects:
                                    Intent registerProjectsIntent = new Intent(ProfileActivity.this, RegisterProjectWithFirebase.class);
                                    startActivity(registerProjectsIntent);
                                    break;
                                case R.id.nearby:
                                    Intent intent = new Intent(ProfileActivity.this, ListOfProjectsActivity.class);
                                    startActivity(intent);
                                    break;

                            }
                            return true;
                        }
                    });


        }
    }



    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {







            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (lastLocation == null) {
                //Log.e(TAG, "moveMapToCurrentLocation: Last Location was null");
                LocationRequest locationRequest = LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
            }else {
                currenLong = lastLocation.getLongitude();
                currentLat = lastLocation.getLatitude();
//
//            Toast.makeText(this, "YOUR Latitude IS" + currentLat, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "YOUR Longitude IS" + currenLong, Toast.LENGTH_SHORT).show();


            }

        }

        Geocoder geocoder = new Geocoder(this, Locale.US);

        try{



            List<Address> addresses = geocoder.getFromLocation(currentLat,currenLong, 1);

            if(addresses != null) {

                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();

                if(addresses != null) {
                    String getAddress = addresses.get(0).getLocality();

                    mLocationText.setText(getAddress);


                }else

                    mLocationText.setText("...Location");

            }

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Could not get address..!", Toast.LENGTH_LONG).show();
        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //TODO DO STUFF
        int id = item.getItemId();

        if ( id == R.id.log_out_in_menu){
            mFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class)); {

            }

        }



        return super.onOptionsItemSelected(item);
    }


//    private void setUpUserProfileInfo(){
//
//        mProfileNameDisplay = (TextView) findViewById(R.id.user_profile_name);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            //Url image = (Url) user.getPhotoUrl();
//
//            mProfileNameDisplay.setText(name);
//
//
//
//        }
//
//    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient != null){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onClick(View v) {

        if (v == mLogOutButton) {
            mFirebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        currentLat = location.getLatitude();
        currenLong = location.getLongitude();

    }
}
