package com.example.catrix.suivieaa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Medecin implements Serializable {

    @SerializedName("id_medecin")
    private int id_medecin;

    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("mail")
    private String mail;

    @SerializedName("telephone")
    private String telephone;

    @SerializedName("mdp")
    private String mdp;

    @SerializedName("sexe")
    private String sexe;

    @SerializedName("date_naissance")
    private String date_naissance;

    @SerializedName("id_cabinet")
    private int id_cabinet;

    public Medecin(){}

    public Medecin(int id_medecin, String nom, String prenom, String mail, String telephone, String mdp, String sexe, String date_naissance, int id_cabinet) {
        this.id_medecin = id_medecin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.mdp = mdp;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.id_cabinet = id_cabinet;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getId_cabinet() {
        return id_cabinet;
    }

    public void setId_cabinet(int id_cabinet) {
        this.id_cabinet = id_cabinet;
    }
}
