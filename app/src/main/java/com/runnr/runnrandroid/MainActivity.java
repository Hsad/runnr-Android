package com.runnr.runnrandroid;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Run> runs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        runs = new ArrayList<>();
        Button nwButton = (Button) findViewById(R.id.nwButton);
        nwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(),
                        "Total runs: " + runs.size(), Toast.LENGTH_SHORT).show();

            }
        });
        Button neButton = (Button) findViewById(R.id.neButton);
        neButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(),
                        "Open History", Toast.LENGTH_SHORT).show();

            }
        });
        Button swButton = (Button) findViewById(R.id.swButton);
        swButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(),
                        "Open Leaderboards", Toast.LENGTH_SHORT).show();

            }
        });
        Button seButton = (Button) findViewById(R.id.seButton);
        seButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(),
                        "Open Settings", Toast.LENGTH_SHORT).show();

            }
        });
        Button runButton = (Button) findViewById(R.id.startRun);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Run thisRun = new Run();
                for(int i = 0; i < 10; i++){
                    LatLng rpiPrime = new LatLng(42.730676 + 0.00013*i, -73.677429 + 0.00005*i);
                    thisRun.addPoint(rpiPrime);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(rpiPrime));
                    mMap.addMarker(new MarkerOptions()
                            .position(rpiPrime)
                            .title("Run marker " + i));
                    try {
                        Thread.sleep(1000);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
                runs.add(thisRun);
                Toast.makeText(getApplicationContext(),
                        "Total distance: " + thisRun.getTotalDistance(), Toast.LENGTH_LONG).show();

            }
        });
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
        LatLng rpi = new LatLng(42.730676, -73.677429);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rpi, 17.0f));
    }
}
