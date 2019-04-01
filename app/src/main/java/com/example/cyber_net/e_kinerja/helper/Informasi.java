package com.example.cyber_net.e_kinerja.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.model.ResponseInformasi;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Informasi {

    public static ResponseInformasi getInformasi(final ProgressDialog progressDialog, final Context context) {
        final ResponseInformasi informasi = new ResponseInformasi();

        ApiService apiService = RetroClient.getApiService();
        Call<ResponseInformasi> call = apiService.getInformasi();
        progressDialog.show();
        call.enqueue(new Callback<ResponseInformasi>() {
            @Override
            public void onResponse(Call<ResponseInformasi> call, Response<ResponseInformasi> response) {
                Log.d("tanggal", response.body().getTanggal());
                try {
                    //Toast.makeText(context, response.body().getTanggal(), Toast.LENGTH_SHORT).show();
                    //get data
                    informasi.setIpaddress(response.body().getIpaddress());
                    informasi.setTanggal(response.body().getTanggal());
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    progressDialog.dismiss();
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseInformasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());
            }
        });
        return informasi;
    }
}
