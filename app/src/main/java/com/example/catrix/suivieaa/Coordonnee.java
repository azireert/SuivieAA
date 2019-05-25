package com.example.catrix.suivieaa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coordonnee implements Serializable {

    @SerializedName("id_medecin")
    private int id_medecin;

    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    public Coordonnee(int id_medecin, String nom, String prenom, String lat, String lng) {
        this.id_medecin = id_medecin;
        this.nom = nom;
        this.prenom = prenom;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }


}
