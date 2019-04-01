package com.example.cyber_net.e_kinerja.helper;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.MenuModel;

import java.util.List;

public class ListMenu {

    static String[] namaMenu = {"SKP TAHUNAN", "LAP. KINERJA SKP",
            "LAP. KINERJA PRODUKTIFITAS", "PERSETUJUAN ATASAN", "PENILAIAN PERILAKU",
            "SETTING"};

    static int[] gambarMenu = {R.drawable.archive, R.drawable.clipboard, R.drawable.notepad, R.drawable.list,
            R.drawable.stopwatch, R.drawable.settings};


    public static List<MenuModel> getMenu(List<MenuModel> listMenu) {
        for (int i = 0; i < namaMenu.length; i++) {
            MenuModel menu = new MenuModel(namaMenu[i], gambarMenu[i]);
            listMenu.add(menu);
        }
        return listMenu;
    }
}
