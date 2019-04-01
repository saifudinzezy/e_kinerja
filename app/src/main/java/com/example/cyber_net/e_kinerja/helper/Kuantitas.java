package com.example.cyber_net.e_kinerja.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.model.ResponseKuantitas;
import com.example.cyber_net.e_kinerja.model.item.KuantitasItem;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kuantitas {

    public static List<KuantitasItem> getKuantitas(final Context context, final List<KuantitasItem> kuantitasItems) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseKuantitas> call = apiService.getKuantitas();

        call.enqueue(new Callback<ResponseKuantitas>() {
            @Override
            public void onResponse(Call<ResponseKuantitas> call, Response<ResponseKuantitas> response) {
                //hasilPesan = response.body().getKuantitas();
                //Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));
                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        //load jika ada data baru
                        /*adapter = new KuantitasAdapter(context, hasilPesan);
                        spKuantitas.setAdapter(adapter);
                        adapter.notifyDataSetChanged();*/
//                        kuantitasItems = response.body().getKuantitas();
                    } catch (Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                }
            }

            @Override
            public void onFailure(Call<ResponseKuantitas> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return kuantitasItems;
    }

}
