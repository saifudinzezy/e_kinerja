package com.example.cyber_net.e_kinerja.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedLogin {
    //key
    public static final String SP_LEY = "Login";

    //key value
    public static final String SP_ID_ADMIN = "id_admin";
    public static final String SP_NAMA_ADMIN = "nama";
    public static final String SP_PASSWORD_ADMIN = "password";
    public static final String SP_SUDAH_LOGIN = "sudah_login";
    public static final String SP_SUDAH_LOGIN2 = "sudah_login2";
    public static final String SP_NIP = "nip";
    public static final String SP_UNIT_KERJA = "unit_kerja";
    public static final String SP_OPD = "opd";
    public static final String SP_LOKER = "loker";
    public static final String SP_LOKER2 = "loker2";

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    //buat sharefpref dari class lain
    public SharedLogin(Context context) {
        //dengan key sampahku
        sp = context.getSharedPreferences(SP_LEY, Context.MODE_PRIVATE);
        this.context = context;
        spEditor = sp.edit();
    }

    public void saveIDAdmin(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveNip(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveUnitKerja(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveOpd(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveLoker(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveLoker2(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveNamaAdmin(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void savePassAdmin(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Boolean getSPSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public Boolean getSPSudahLogin2() {
        return sp.getBoolean(SP_SUDAH_LOGIN2, false);
    }

    public String getIDAdmin() {
        return sp.getString(SP_ID_ADMIN, "");
    }

    public String getNamaAdmin() {
        return sp.getString(SP_NAMA_ADMIN, "");
    }

    public String getPasswordAdmin() {
        return sp.getString(SP_PASSWORD_ADMIN, "");
    }

    public String getNip() {
        return sp.getString(SP_NIP, "");
    }

    public String getUnitKerja() {
        return sp.getString(SP_UNIT_KERJA, "");
    }

    public String getOpd() {
        return sp.getString(SP_OPD, "");
    }

    public String getLoker() {
        return sp.getString(SP_LOKER, "");
    }

    public String getLoker2() {
        return sp.getString(SP_LOKER2, "");
    }
}