package com.example.catrix.suivieaa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ChooseDialogFragment extends DialogFragment {

    ChooseDialogFragmentInterface dataInterface;

    String[] arrayNameMedecin = new String[]{};

    public void setDataInterface(ChooseDialogFragmentInterface dataInterface) {
        this.dataInterface = dataInterface;
    }

    public ChooseDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final String[] arrayOfChooses = ConvertMedecinToString((Medecin[])this.getArguments().getSerializable("listMedecin"));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choisir un médecin")
                .setItems(arrayOfChooses, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataInterface.onChooseDone(arrayOfChooses[which]);
                    }
                });
        return builder.create();
    }


    public interface ChooseDialogFragmentInterface {
        void onChooseDone(String choose);
    }


    public String[] ConvertMedecinToString(Medecin[] listmedecin){
        String[] arrayMedecinName = new String[listmedecin.length];
        for(int i = 0;i<listmedecin.length;i++){
            arrayMedecinName[i] = listmedecin[i].getPrenom()+" "+listmedecin[i].getNom();
        }
        return  arrayMedecinName;
    }
}


