package com.example.catrix.suivieaa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Visiteur implements Serializable {

    @SerializedName("id_visiteur")
    private int id_visiteur;

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

    public Visiteur(){}

    public Visiteur(int id,String nom,String prenom,String mail,String telephone,String mdp){
        this.id_visiteur = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.mdp = mdp;
    }

    public int getId_visiteur() {
        return id_visiteur;
    }

    public void setId_visiteur(int id_visiteur) {
        this.id_visiteur = id_visiteur;
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




}
