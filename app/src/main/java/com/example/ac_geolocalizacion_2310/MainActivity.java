package com.example.ac_geolocalizacion_2310;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.view.View;

import com.example.ac_geolocalizacion_2310.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myMap;

    // Coordenadas (clase sigue casi igual, solo agrego estos campos)
    private LatLng miCasa;
    private LatLng tiasBasilio;
    private LatLng tiasCaral;
    private LatLng abuela;
    private LatLng burrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Clicks de la lista (ya no están comentados)
        findViewById(R.id.item1).setOnClickListener(v -> moveTo(miCasa));
        findViewById(R.id.item2).setOnClickListener(v -> moveTo(tiasBasilio));
        findViewById(R.id.item3).setOnClickListener(v -> moveTo(tiasCaral));
        findViewById(R.id.item4).setOnClickListener(v -> moveTo(abuela));
        findViewById(R.id.item5).setOnClickListener(v -> moveTo(burrito));
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        myMap = googleMap;

        // Coordenadas exactas
        miCasa      = new LatLng(-17.724320799499974, -63.15568343121466);
        tiasBasilio = new LatLng(-16.494309761392127, -68.2029533763114);
        tiasCaral   = new LatLng(-17.420413974438436, -66.14284258254136);
        abuela      = new LatLng(-20.73944379713988, -67.66088331811669);
        burrito     = new LatLng(-20.709437635755233, -67.70732123909214);

        // Cámara inicial
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miCasa, 16f));

        // Markers con el MISMO tono de los botones (convierte color a HUE)
        addColoredMarker(miCasa,      "Mi casa — Santa Cruz", R.color.green_mid_3);
        addColoredMarker(tiasBasilio, "Tías — Basilio",       R.color.olive_dark);
        addColoredMarker(tiasCaral,   "Tías — Caral",         R.color.olive_2);
        addColoredMarker(abuela,      "Abuela",               R.color.olive_3);
        addColoredMarker(burrito,     "Burrito",              R.color.green_dark_1);
    }

    private void addColoredMarker(LatLng pos, String title, int colorRes){
        MarkerOptions options = new MarkerOptions().position(pos).title(title);
        float hue = colorToHue(ContextCompat.getColor(this, colorRes));
        options.icon(BitmapDescriptorFactory.defaultMarker(hue));
        myMap.addMarker(options);
    }

    private float colorToHue(int colorInt){
        float[] hsv = new float[3];
        android.graphics.Color.colorToHSV(colorInt, hsv);
        return hsv[0];
    }

    private void moveTo(LatLng target){
        if (myMap != null && target != null) {
            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(target, 16f));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {
        super.onPointerCaptureChanged(hasCapture);
    }
}
