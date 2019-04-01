package com.example.cyber_net.e_kinerja.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cyber_net.e_kinerja.fragment.PersKineProd;
import com.example.cyber_net.e_kinerja.fragment.PersLapKinerSKP;

/**
 * Created by Cyber_net on 24/01/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numberTabs;

    public PagerAdapter(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //Mengembalikan Fragment yang terkait dengan posisi tertentu
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PersLapKinerSKP();
            case 1:
                return new PersKineProd();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberTabs;
    }
}
