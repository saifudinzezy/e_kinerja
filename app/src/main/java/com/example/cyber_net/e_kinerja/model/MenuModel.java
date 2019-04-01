package com.example.cyber_net.e_kinerja.model;


public class MenuModel {
    private String namaMenu;
    private int iconMenu;

    public MenuModel() {
    }

    public MenuModel(String namaMenu, int iconMenu) {
        this.namaMenu = namaMenu;
        this.iconMenu = iconMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public int getIconMenu() {
        return iconMenu;
    }

    public void setIconMenu(int iconMenu) {
        this.iconMenu = iconMenu;
    }
}
