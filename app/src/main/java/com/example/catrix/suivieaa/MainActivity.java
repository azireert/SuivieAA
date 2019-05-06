package com.example.catrix.suivieaa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        ButtonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetConnectedVisiteur(String.valueOf(MailText.getText()),String.valueOf(PasswordText.getText()),Listvisiteur);

            }
        });
    }


    public void GetConnectedVisiteur(String mail, String mdp, List<Visiteur> visiteurs){
        Medecin[] listMedecin = Listmedecin.toArray(new Medecin[Listmedecin.size()]);
        for(int i = 0; i < visiteurs.size(); i++){
            if(visiteurs.get(i).getMail().equals(mail) && visiteurs.get(i).getMdp().equals(mdp)){
                Intent VisiteActivityIntent = new Intent(MainActivity.this , VisiteActivity.class);
                VisiteActivityIntent.putExtra("visiteur",visiteurs.get(i));
                VisiteActivityIntent.putExtra("medecin",listMedecin);
                startActivity(VisiteActivityIntent);
            }
        }
    }

    public interface WebServicesInterface {
        @GET("visiteur")
        Call<List<Visiteur>> listVisiteurs();
        @GET("medecin")
        Call<List<Medecin>> listMedecins();
    }
}
