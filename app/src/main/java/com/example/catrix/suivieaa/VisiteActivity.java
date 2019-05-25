package com.example.catrix.suivieaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class VisiteActivity extends AppCompatActivity implements OnMapReadyCallback, DateDialogFragment.DateInterface, TimeDialogFragment.TimeInterface, ChooseDialogFragment.ChooseDialogFragmentInterface {

    Visiteur visiteur;

    Button datePickerButton;
    Button timePickerButton;
    Button timePickerButton1;
    Button buttonComboMedecin;
    CheckBox checkBox;
    Button buttonDeconnexion;
    TextView userText;
    int pressButton;

    String dateSQL;
    String datedebutSQL;
    String dateFinSQL;
    String medecinChoose;
    int isRDV = 0;

    Medecin[] ListMedecin;
    Coordonnee[] ListCoordonnee;
    Retrofit retrofit;

    VisiteActivity.WebServicesInterface1 webServicesInterface;

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

        ListCoordonnee = (Coordonnee[])intent.getSerializableExtra("coordonnee");


        userText = findViewById(R.id.userText);
        datePickerButton = findViewById(R.id.datePickerButton);
        timePickerButton = findViewById(R.id.timePickerButton);
        timePickerButton1 = findViewById(R.id.timePicker1Button);
        buttonComboMedecin = findViewById(R.id.buttonComboMedecin);
        buttonDeconnexion = findViewById(R.id.buttonDeconnexion);
        checkBox = findViewById(R.id.checkBoxVisite);

        userText.setText("Connecté en tant que : "+visiteur.getNom()+" "+visiteur.getPrenom());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServicesInterface = retrofit.create(VisiteActivity.WebServicesInterface1.class);

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
        /*LatLng cab1 = new LatLng(46.227638, 2.213730);
        LatLng cab2 = new LatLng(50.6333, 3.0667);
        LatLng cab3 = new LatLng(48.8534, 2.3488);
        mMap.addMarker(new MarkerOptions().position(cab1).title("Julien Ecrin"));
        mMap.addMarker(new MarkerOptions().position(cab2).title("Jean Valere"));
        mMap.addMarker(new MarkerOptions().position(cab3).title("Yves Dupond"));*/

        for(int i = 0;i<ListCoordonnee.length;i++){
            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(ListCoordonnee[i].getLat()),Double.valueOf(ListCoordonnee[i].getLng()))).title(ListCoordonnee[i].getPrenom()+" "+ListCoordonnee[i].getNom()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.227638, 2.213730), 5.0f));
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
        pressButton = 0;
    }

    public void timePickerClicked1(View timePickerButton) {
        TimeDialogFragment dialogFragment = new TimeDialogFragment();
        dialogFragment.setTimeInterface(this);
        dialogFragment.show(getSupportFragmentManager(), "timePicker");
        pressButton = 1;
    }

    public void confirmClick(View view){
        if(checkBox.isChecked()){
            isRDV = 1;
        }
        Visites visite = new Visites(isRDV,dateSQL+" "+datedebutSQL,dateSQL+" "+dateFinSQL,medecinChoose,String.valueOf(visiteur.getId_visiteur()));
        webServicesInterface.savePost(visite).enqueue(new Callback<Visites>() {
            @Override
            public void onResponse(Call<Visites> call, Response<Visites> response) {

                if(response.isSuccessful()) {
                    resetForm();

                }
            }

            @Override
            public void onFailure(Call<Visites> call, Throwable t) {
            }
        });
        Toast.makeText(this,"Visite Enregistrée", Toast.LENGTH_LONG).show();
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
        dateSQL = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
    }

    @Override
    public void getTimeFromTimeFragment(int hour, int minute) {
        if(pressButton == 0){
            timePickerButton.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
            datedebutSQL = String.valueOf(hour) + ":" + String.valueOf(minute)+":"+"00";
        }else {
            timePickerButton1.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
            dateFinSQL = String.valueOf(hour) + ":" + String.valueOf(minute)+":"+"00";
        }

    }

    @Override
    public void onChooseDone(String choose) {
        buttonComboMedecin.setText(choose);
        medecinChoose = findIDbyName(choose);
    }

    public String findIDbyName(String choose){
        String id = "";
        for(int i =0;i<ListMedecin.length;i++){
            if(choose.equals(ListMedecin[i].getPrenom()+" "+ListMedecin[i].getNom())){
                id = String.valueOf(ListMedecin[i].getId_medecin());
            }
        }
        return id;
    }


    public void resetForm(){
        buttonComboMedecin.setText("Choisir médecin");
        timePickerButton.setText("Heure début");
        timePickerButton1.setText("Heure arrivée");
        datePickerButton.setText("Choisir date");
        checkBox.setChecked(false);
    }

    public interface WebServicesInterface1 {
        @POST("/visite")
        Call<Visites> savePost(@Body Visites visite);
    }

}
