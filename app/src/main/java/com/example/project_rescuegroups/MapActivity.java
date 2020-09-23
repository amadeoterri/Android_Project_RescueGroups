package com.example.project_rescuegroups;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_rescuegroups.model.Organization;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Context context;
    private Address adresMetLocatie;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        Organization organization = intent.getParcelableExtra("organization");
        if (organization != null) {
            adresMetLocatie = convertLocation(
                    organization.getOrganizationAddress() + " "
                            + organization.getOrganizationCity() + " "
                            + organization.getOrganizationState()
                            + organization.getOrganizationZip());
        }
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(MapActivity.this);
    }

    private Address convertLocation(String adres) {
        WifiManager wifi = (WifiManager) this.context.getSystemService(Context.WIFI_SERVICE);
        if (wifi != null) {
            if (!wifi.isWifiEnabled()) {
                wifi.setWifiEnabled(true);
            }
        }

        Geocoder geocoder = new Geocoder(this);
        Address result = null;
        try {
            List<Address> adresses = geocoder.getFromLocationName(adres, 5);
            if (adresses == null) {
                return null;
            }
            result = adresses.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(adresMetLocatie.getLatitude(),
                adresMetLocatie.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Info")
                .snippet(adresMetLocatie.getAddressLine(0)));
//                      .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_groen)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
    }
}
