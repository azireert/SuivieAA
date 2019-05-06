package com.example.catrix.suivieaa;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VisiteActivity extends AppCompatActivity implements OnMapReadyCallback, DateDialogFragment.DateInterface, TimeDialogFragment.TimeInterface, ChooseDialogFragment.ChooseDialogFragmentInterface {

    Visiteur visiteur;

    Button datePickerButton;
    Button timePickerButton;
    Button timePicker1Button;
    Button buttonComboMedecin;
    Button buttonDeconnexion;
    TextView userText;

    Medecin[] ListMedecin;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visite);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        visiteur = (Visiteur)intent.getSerializableExtra("visiteur");

        ListMedecin = (Medecin[])intent.getSerializableExtra("medecin");


        userText = findViewById(R.id.userText);
        datePickerButton = findViewById(R.id.datePickerButton);
        timePickerButton = findViewById(R.id.timePickerButton);
        buttonComboMedecin = findViewById(R.id.buttonComboMedecin);
        buttonDeconnexion = findViewById(R.id.buttonDeconnexion);

        userText.setText("Connecté en tant que : "+visiteur.getNom()+" "+visiteur.getPrenom());
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
        LatLng cab1 = new LatLng(46.227638, 2.213730);
        LatLng cab2 = new LatLng(50.6333, 3.0667);
        LatLng cab3 = new LatLng(48.8534, 2.3488);
        mMap.addMarker(new MarkerOptions().position(cab1).title("Julien Ecrin"));
        mMap.addMarker(new MarkerOptions().position(cab2).title("Jean Valere"));
        mMap.addMarker(new MarkerOptions().position(cab3).title("Yves Dupond"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cab1, 5.0f));
    }

    public void datePickerClicked(View v) {
        DateDialogFragment dialogFragment = new DateDialogFragment();
        dialogFragment.setDateInterface(this);
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void timePickerClicked(View timePickerButton) {
        TimeDialogFragment dialogFragment = new TimeDialogFragment();
        dialogFragment.setTimeInterface(this);
        dialogFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onModalButtonClick(View view) {
        ChooseDialogFragment fragment = new ChooseDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listMedecin",ListMedecin);
        fragment.setArguments(bundle);
        fragment.setDataInterface(this);
        fragment.show(getSupportFragmentManager(), "ChooseModal");
    }

    public void onClickDeconnexion(View view) {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void getDateFromDateFragment(int year, int month, int day) {
        datePickerButton.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
    }

    @Override
    public void getTimeFromTimeFragment(int hour, int minute) {
        timePickerButton.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
    }

    @Override
    public void onChooseDone(String choose) {
        buttonComboMedecin.setText(choose);
    }


}
