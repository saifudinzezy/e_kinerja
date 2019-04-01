package com.example.cyber_net.e_kinerja.network;

import com.example.cyber_net.e_kinerja.model.ResponseAtasan;
import com.example.cyber_net.e_kinerja.model.ResponseDelete;
import com.example.cyber_net.e_kinerja.model.ResponseInsert;
import com.example.cyber_net.e_kinerja.model.ResponseInformasi;
import com.example.cyber_net.e_kinerja.model.ResponseKinerjaProduktivitas;
import com.example.cyber_net.e_kinerja.model.ResponseKuantitas;
import com.example.cyber_net.e_kinerja.model.ResponseLapKinerjaSKP;
import com.example.cyber_net.e_kinerja.model.ResponseMaxDate;
import com.example.cyber_net.e_kinerja.model.ResponseNamaAtasan;
import com.example.cyber_net.e_kinerja.model.ResponseNamaBawahan;
import com.example.cyber_net.e_kinerja.model.ResponseSKPTahunan;
import com.example.cyber_net.e_kinerja.model.item.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("read/informasi.php")
    Call<ResponseInformasi> getInformasi();

    @GET("read/kuantitas.php")
    Call<ResponseKuantitas> getKuantitas();

    @GET("read/getmaxdate.php")
    Call<ResponseMaxDate> getMaxDate();

    @FormUrlEncoded
    @POST("read/login.php")
    Call<ResponseUser> login(@Field("username") String nama, @Field("password") String password);

    @FormUrlEncoded
    @POST("read/atasan.php")
    Call<ResponseAtasan> getNamaAtasan(@Field("loker") String loker);

    @FormUrlEncoded
    @POST("read/bawahan.php")
    Call<ResponseNamaBawahan> getNamaBawahan(@Field("nip") String nip);

    @FormUrlEncoded
    @POST("read/atasan2.php")
    Call<ResponseNamaAtasan> getNamaAtasan2(@Field("nip") String nip);

    //get
    //skp tahunan
    @FormUrlEncoded
    @POST("read/skp_tahunan3.php")
    Call<ResponseSKPTahunan> getYear(@Field("nip") String nip);

    @FormUrlEncoded
    @POST("read/skp_tahunan3.php")
    Call<ResponseSKPTahunan> getYear(@Field("nip") String nip, @Field("thn") String tahun);

    @FormUrlEncoded
    @POST("read/skp_tahunan2.php")
    Call<ResponseSKPTahunan> getYear2(@Field("nip") String nip, @Field("thn") String tahun,
                                                           @Field("bulan") String bulan);

    //get
    //lap kinerja prod
    @FormUrlEncoded
    @POST("read/kinerja_produktifitas.php")
    Call<ResponseKinerjaProduktivitas> getYearKinerjaProd(@Field("nip") String nip);

    @FormUrlEncoded
    @POST("read/kinerja_produktifitas.php")
    Call<ResponseKinerjaProduktivitas> getYearKinerjaProd(@Field("nip") String nip, @Field("thn") String tahun);

    @FormUrlEncoded
    @POST("read/kinerja_produktifitas2.php")
    Call<ResponseKinerjaProduktivitas> getYearKinerjaProd2(@Field("nip") String nip, @Field("thn") String tahun,
                                                           @Field("bulan") String bulan);

    //get lap kinerja SKP
    @FormUrlEncoded
    @POST("read/lap_skp_tahunan.php")
    Call<ResponseLapKinerjaSKP> getYearLapKinerjaSKP(@Field("nip") String nip, @Field("thn") String tahun,
                                                     @Field("bulan") String bulan);

    //save lap kinerja prod
    @FormUrlEncoded
    @POST("create/kinerja_prod.php")
    Call<ResponseInsert> simpanKinerjaProd(@Field("nip") String nip, @Field("tanggal") String tanggal, @Field("Kegiatan") String Kegiatan,
                                           @Field("kuantitas") String kuantitas, @Field("satuan") String satuan, @Field("status") String status,
                                           @Field("update_from") String updateFrom, @Field("update_by") String updateBy, @Field("update_on") String updateOn);

    //save skp tahunan
    @FormUrlEncoded
    @POST("create/skp_tahunan.php")
    Call<ResponseInsert> simpanSKP(@Field("nip") String nip, @Field("tahun") String tahun, @Field("Kegiatan") String Kegiatan,
                                           @Field("kuantitas") String kuantitas,  @Field("satuan") String satuan, @Field("target") String target,
                                           @Field("update_from") String updateFrom, @Field("update_by") String updateBy, @Field("update_on") String updateOn);

    //save skp tahunan
    @FormUrlEncoded
    @POST("create/lap_skp_tahunan.php")
    Call<ResponseInsert> simpanLapSKP(@Field("nip") String nip, @Field("tanggal") String tanggal, @Field("Kegiatan") String Kegiatan,
        @Field("kuantitas") String kuantitas, @Field("satuan") String satuan, @Field("id_skp") String idSkp,
                                      @Field("update_from") String updateFrom, @Field("update_by") String updateBy, @Field("update_on") String updateOn);

    //update lapkinerja prod
    @FormUrlEncoded
    @POST("update/kinerja_prod.php")
    Call<ResponseInsert> updateKinerjaProd(@Field("id") String id, @Field("Kegiatan") String Kegiatan, @Field("kuantitas") String kuantitas, @Field("satuan") String satuan,
                                           @Field("update_from") String updateFrom, @Field("update_by") String updateBy, @Field("update_on") String updateOn);

    //update skp
    @FormUrlEncoded
    @POST("update/skp_tahunan.php")
    Call<ResponseInsert> updateSKP(@Field("id") String id, @Field("Kegiatan") String Kegiatan, @Field("kuantitas") String kuantitas, @Field("satuan") String satuan,
                                   @Field("target") String target, @Field("update_from") String updateFrom, @Field("update_by") String updateBy,
                                   @Field("update_on") String updateOn);

    //update Lap skp
    @FormUrlEncoded
    @POST("update/lap_skp_tahunan.php")
    Call<ResponseInsert> updateLapSKP(@Field("id") String id, @Field("Kegiatan") String Kegiatan, @Field("kuantitas") String kuantitas,
                                      @Field("satuan") String satuan, @Field("id_skp") String idSkp, @Field("update_from") String updateFrom,
                                      @Field("update_by") String updateBy, @Field("update_on") String updateOn);

    //update
    @FormUrlEncoded
    @POST("update/user.php")
    Call<ResponseInsert> updateUser(@Field("nip") String nip, @Field("pass") String pass);

    //update
    @FormUrlEncoded
    @POST("update/atasan.php")
    Call<ResponseInsert> updateAtasan(@Field("id") String id, @Field("nip_atasan1") String nip_atasan1);

    //delete
    @FormUrlEncoded
    @POST("delete/delete.php")
    Call<ResponseDelete> delete(@Field("tabel") String tabel, @Field("cari") String cari, @Field("id_data") String idData);
}