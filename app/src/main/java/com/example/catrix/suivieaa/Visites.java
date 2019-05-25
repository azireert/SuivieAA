package com.example.catrix.suivieaa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Visites implements Serializable {

    @SerializedName("estRDV")
    private int estRDV;

    @SerializedName("heureDebutEntr")
    private String heureDebutEntr;

    @SerializedName("heureDepartCab")
    private String heureDepartCab;

    @SerializedName("id_medecin")
    private String id_medecin;

    @SerializedName("id_visiteur")
    private String id_visiteur;


    public int getEstRDV() {
        return estRDV;
    }

    public void setEstRDV(int estRDV) {
        this.estRDV = estRDV;
    }

    public String getHeureDebutEntr() {
        return heureDebutEntr;
    }

    public void setHeureDebutEntr(String heureDebutEntr) {
        this.heureDebutEntr = heureDebutEntr;
    }

    public String getHeureDepartCab() {
        return heureDepartCab;
    }

    public void setHeureDepartCab(String heureDepartCab) {
        this.heureDepartCab = heureDepartCab;
    }

    public String getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(String id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getId_visiteur() {
        return id_visiteur;
    }

    public void setId_visiteur(String id_visiteur) {
        this.id_visiteur = id_visiteur;
    }

    public Visites(int estRDV, String heureDebutEntr, String heureDepartCab, String id_medecin, String id_visiteur) {
        this.estRDV = estRDV;
        this.heureDebutEntr = heureDebutEntr;
        this.heureDepartCab = heureDepartCab;
        this.id_medecin = id_medecin;
        this.id_visiteur = id_visiteur;
    }

}
