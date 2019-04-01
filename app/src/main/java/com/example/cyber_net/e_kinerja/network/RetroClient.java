package com.example.cyber_net.e_kinerja.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static Retrofit retrofit = null;

    private static Retrofit getClient(){
        //cek jika retrofit null
        if (retrofit == null){
            //maka buat object dari retrofit
            retrofit = new Retrofit.Builder()
                    //ubah sesuai urlnya
                    .baseUrl("http://192.168.43.74/api_ekinerjaku/")
//                    .baseUrl("http://ekinerjaku.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService(){
        return getClient().create(ApiService.class);
    }
}