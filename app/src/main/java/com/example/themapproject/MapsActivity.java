package com.example.themapproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.themapproject.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private static final LatLng Mumbai = new LatLng(19.0760,72.8777);
    private static final LatLng Delhi = new LatLng(28.7041, 77.1025);
    private static final LatLng Bangalore = new LatLng(12.9716, 77.5946);

    private Marker mMumbai;
    private Marker mDelhi;
    private Marker mBangalore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        List<Marker> markerList = new ArrayList<>();

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMumbai = mMap.addMarker(new MarkerOptions()
        .position(Mumbai)
        .title("Mumbai"));
        mMumbai.setTag(0);
        markerList.add(mMumbai);

        mDelhi = mMap.addMarker(new MarkerOptions()
                .position(Delhi)
                .title("Delhi")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mDelhi.setTag(0);
        markerList.add(mDelhi);

        mBangalore = mMap.addMarker(new MarkerOptions()
                .position(Bangalore)
                .title("Bangalore")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mBangalore.setTag(0);
        markerList.add(mBangalore);

        mMap.setOnMarkerClickListener(this);

        for(Marker m : markerList)
        {
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,2));
        }

        // Add a marker in Sydney and move the camera
        /*LatLng Ajmer = new LatLng(26.4499, 74.6399);
        mMap.addMarker(new MarkerOptions().position(Ajmer).title("Marker in Ajmer")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Ajmer,13));*/
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Integer clickCount = (Integer)marker.getTag();
        if(clickCount!=null)
        {
            clickCount= clickCount+1;
            marker.setTag(clickCount);

            Toast.makeText(this,marker.getTitle()+" has been clicked "+clickCount+"  times",Toast.LENGTH_LONG).show();
        }

        return false;
    }
}