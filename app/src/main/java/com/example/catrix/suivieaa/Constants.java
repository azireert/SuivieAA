package com.example.catrix.suivieaa;

public class Constants {
    public static final String API_URL = "http://192.168.89.131:3000/";
    public static final String DATABASE_NAME = "gsb";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_CREATE_TABLE_VISITES = "CREATE TABLE visites (id_visite INTEGER PRIMARY KEY AUTOINCREMENT, isRDV INTEGER, heureDebutEntre TEXT, heureDepartCab TEXT, id_medecin TEXT, id_visiteur TEXT)";
}
