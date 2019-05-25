package com.example.catrix.suivieaa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    List<Visiteur> Listvisiteur ;
    List<Medecin> Listmedecin;
    List<Coordonnee> Listcoordonnees;

    EditText MailText;
    EditText PasswordText;
    Button ButtonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MailText = findViewById(R.id.editTextMail);
        PasswordText = findViewById(R.id.editTextPassword);
        ButtonConnexion = findViewById(R.id.buttonConnexion);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<List<Visiteur>> call = webServicesInterface.listVisiteurs();
        call.enqueue(new Callback<List<Visiteur>>() {
            @Override
            public void onResponse(Call<List<Visiteur>> call, Response<List<Visiteur>> response) {
                Listvisiteur = response.body();
            }

            @Override
            public void onFailure(Call<List<Visiteur>> call, Throwable t) {
                Log.d("JLE", "Ca passe pas");
            }
        });

        Call<List<Medecin>> call1 = webServicesInterface.listMedecins();
        call1.enqueue(new Callback<List<Medecin>>() {
            @Override
            public void onResponse(Call<List<Medecin>> call, Response<List<Medecin>> response) {
                Listmedecin = response.body();
            }

            @Override
            public void onFailure(Call<List<Medecin>> call, Throwable t) {
                Log.d("JLE", "Ca passe pas");
            }
        });

        Call<List<Coordonnee>> call2 = webServicesInterface.listCoordonnees();
        call2.enqueue(new Callback<List<Coordonnee>>() {
            @Override
            public void onResponse(Call<List<Coordonnee>> call, Response<List<Coordonnee>> response) {
                Listcoordonnees = response.body();
            }

            @Override
            public void onFailure(Call<List<Coordonnee>> call, Throwable t) {
                Log.d("JLE", "Ca passe pas");
            }
        });

        ButtonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetConnectedVisiteur(String.valueOf(MailText.getText()),String.valueOf(PasswordText.getText()),Listvisiteur);

            }
        });



        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginPassword",
                Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("Login","");
        String mdp = sharedPreferences.getString("Password","");

        MailText.setText(login);
        PasswordText.setText(mdp);
    }

    private boolean isWifiCheck(){
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            return true;
        } else {
            Toast.makeText(this,"Le Wifi est désactivé", Toast.LENGTH_LONG).show();
            return false;

        }
    }


    public void GetConnectedVisiteur(String mail, String mdp, List<Visiteur> visiteurs){
            Medecin[] listMedecin = Listmedecin.toArray(new Medecin[Listmedecin.size()]);
            Coordonnee[] listCoordonnees = Listcoordonnees.toArray(new Coordonnee[Listcoordonnees.size()]);
            for(int i = 0; i < visiteurs.size(); i++){
                if(visiteurs.get(i).getMail().equals(mail) && visiteurs.get(i).getMdp().equals(mdp)){
                    SaveDataInphone(mail,mdp);
                    Intent VisiteActivityIntent = new Intent(MainActivity.this , VisiteActivity.class);
                    VisiteActivityIntent.putExtra("visiteur",visiteurs.get(i));
                    VisiteActivityIntent.putExtra("medecin",listMedecin);
                    VisiteActivityIntent.putExtra("coordonnee",listCoordonnees);
                    startActivity(VisiteActivityIntent);
                }
            }
    }

    public void SaveDataInphone(String mail, String password){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginPassword",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Login",mail);
        editor.putString("Password", password);
        editor.commit();
    }

    public interface WebServicesInterface {
        @GET("visiteur")
        Call<List<Visiteur>> listVisiteurs();
        @GET("medecin")
        Call<List<Medecin>> listMedecins();
        @GET("coordonnee")
        Call<List<Coordonnee>> listCoordonnees();
    }
}
