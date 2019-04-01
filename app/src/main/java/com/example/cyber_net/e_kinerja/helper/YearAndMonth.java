package com.example.cyber_net.e_kinerja.helper;

import android.util.Log;

import com.example.cyber_net.e_kinerja.model.item.MaxDateItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.cyber_net.e_kinerja.helper.CariBulan.cariNamaBulan;
import static com.example.cyber_net.e_kinerja.helper.ConvertDate.customTanggal;

public class YearAndMonth {
    public static ArrayList<String> getYear(List<MaxDateItem> hasilPesan) {
        ArrayList<String> listB = new ArrayList<String>();

        for (int j = 0; j < hasilPesan.size(); j++) {
            Log.d("Tahun", "tahun list A -> " + hasilPesan);
            String thn = customTanggal(hasilPesan.get(j).getAwal(), "yyyy-MM-dd", "yyyy");
            //jika data tidak sama (contains) maka masukan data
            if (!listB.contains(thn)) {
                listB.add(thn);
                Log.d("Tahun", "tahun list B -> " + listB);
            }
        }
        return listB;
    }

    public static ArrayList<String> getMonth(List<MaxDateItem> hasilPesan) {
        ArrayList<String> listB = new ArrayList<String>();

        for (int j = 0; j < hasilPesan.size(); j++) {
            Log.d("bulan", "bulan list A -> " + hasilPesan);
            String thn = cariNamaBulan(customTanggal(hasilPesan.get(j).getAwal(), "yyyy-MM-dd", "MM"));
            //jika data tidak sama (contains) maka masukan data
            if (!listB.contains(thn)) {
                listB.add(thn);
                Log.d("bulan", "bulan list B -> " + listB);
            }
        }
        return listB;
    }

    public static boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
        //Jika date1 adalah tanggal sebelum date2, metode before akan mengembalikan nilai true
        //return !(testDate.before(startDate) || testDate.after(endDate));
        //return testDate.after(startDate) && testDate.before(endDate);
        return (testDate.before(endDate) && testDate.after(startDate));
    }
}
