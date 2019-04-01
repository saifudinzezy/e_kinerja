package com.example.cyber_net.e_kinerja.activity.menu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.adapter.LapKinerjaSKPAdapter;
import com.example.cyber_net.e_kinerja.adapter.spinner.KuantitasAdapter;
import com.example.cyber_net.e_kinerja.adapter.spinner.SKPTahunanAdapter;
import com.example.cyber_net.e_kinerja.helper.Informasi;
import com.example.cyber_net.e_kinerja.helper.YearAndMonth;
import com.example.cyber_net.e_kinerja.model.item.LapKinerjaSkpItem;
import com.example.cyber_net.e_kinerja.model.ResponseInformasi;
import com.example.cyber_net.e_kinerja.model.ResponseInsert;
import com.example.cyber_net.e_kinerja.model.ResponseKuantitas;
import com.example.cyber_net.e_kinerja.model.ResponseLapKinerjaSKP;
import com.example.cyber_net.e_kinerja.model.ResponseMaxDate;
import com.example.cyber_net.e_kinerja.model.ResponseSKPTahunan;
import com.example.cyber_net.e_kinerja.model.item.KuantitasItem;
import com.example.cyber_net.e_kinerja.model.item.MaxDateItem;
import com.example.cyber_net.e_kinerja.model.item.SkpTahunanItem;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;
import com.example.cyber_net.e_kinerja.shared.SharedLogin;
import com.google.gson.Gson;
import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.e_kinerja.helper.CariBulan.cariNomerBulan;
import static com.example.cyber_net.e_kinerja.helper.ConvertDate.customTanggal;
import static com.example.cyber_net.e_kinerja.helper.FunctionError.getTextEditText;

public class LapKinerjaSKP extends AppCompatActivity implements LapKinerjaSKPAdapter.SKPAdapterListener{

    @BindView(R.id.txt_bulan)
    TextView txtBulan;
    @BindView(R.id.txt_tahun)
    TextView txtTahun;
    @BindView(R.id.sp_bulan)
    Spinner spBulan;
    @BindView(R.id.sp_tahun)
    Spinner spTahun;
    @BindView(R.id.cari)
    ImageButton cari;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.txt_notif)
    TextView txtNotif;
    @BindView(R.id.tambah)
    FloatingActionButton tambah;

    //
    ProgressDialog progressDialog;
    ResponseInformasi informasi;
    ArrayAdapter<String> adapterSP, adapterSPBulan;
    SharedLogin sharedLogin;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText edtId, edtKegiatan, edtJumlah;
    Spinner spKuantitas, spSKPTahunan;
    String year, bulan;
    KuantitasAdapter adapterKuantitas;
    SKPTahunanAdapter adapterSKPTahunan;
    List<KuantitasItem> kuantitasItems;
    List<SkpTahunanItem> lapKinerjaSkpItems;
    public String kuantitas2, skpTahunan2, spKesesuain2, spStatus2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lap);
        ButterKnife.bind(this);

        informasi = new ResponseInformasi();
        sharedLogin = new SharedLogin(this);
        kuantitasItems = new ArrayList<>();

        getSupportActionBar().setTitle("Lap .Kinerja SKP");
        rvList.setLayoutManager(new VegaLayoutManager());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu Sebentar...");

        //get data
        //getInformasi();
        informasi = Informasi.getInformasi(progressDialog, LapKinerjaSKP.this);

        getYear();
        getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
        getMonth();
        kuantitasItems = getKuantitas();
        lapKinerjaSkpItems = getSkpTahunan();

        spTahun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = adapterSP.getItem(position).toString();
                if (!year.isEmpty()) {
                    //getYear(year, sharedLogin.getNip());
                    txtTahun.setText(year);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bulan = adapterSPBulan.getItem(position).toString();
                if (!bulan.isEmpty()) {
                    //getYear(year, sharedLogin.getNip());
                    //Toast.makeText(LapKinerjaSKP.this, "" + cariNomerBulan(bulan), Toast.LENGTH_SHORT).show();
                    txtBulan.setText(cariNomerBulan(bulan));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // hide fab
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && tambah.getVisibility() == View.VISIBLE) {
                    tambah.hide();
                    txtNotif.setVisibility(View.GONE);
                } else if (dy < 0 && tambah.getVisibility() != View.VISIBLE) {
                    tambah.show();
                    txtNotif.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getYear() {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseMaxDate> call = apiService.getMaxDate();
        progressDialog.show();
        call.enqueue(new Callback<ResponseMaxDate>() {
            @Override
            public void onResponse(Call<ResponseMaxDate> call, Response<ResponseMaxDate> response) {
                List<MaxDateItem> hasilPesan = response.body().getMaxDate();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        progressDialog.dismiss();
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
                        //spinner
                        adapterSP = new ArrayAdapter<>(LapKinerjaSKP.this, R.layout.support_simple_spinner_dropdown_item,
                                listB);
                        //adapterSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //set adapter
                        spTahun.setAdapter(adapterSP);
                        //load jika ada data baru
                        adapterSP.notifyDataSetChanged();
                    } catch (Exception e) {
                        progressDialog.dismiss();
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaxDate> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void getYear(String tahun, String nip, String bulan) {
        pb.setVisibility(View.VISIBLE);
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseLapKinerjaSKP> call = apiService.getYearLapKinerjaSKP(nip, tahun, bulan);
        call.enqueue(new Callback<ResponseLapKinerjaSKP>() {
            @Override
            public void onResponse(Call<ResponseLapKinerjaSKP> call, Response<ResponseLapKinerjaSKP> response) {
                List<LapKinerjaSkpItem> hasilPesan = response.body().getLapKinerjaSkp();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        rvList.setVisibility(View.VISIBLE);
                        ArrayList<LapKinerjaSkpItem> list = new ArrayList<>();
                        list.addAll(hasilPesan);

                        LapKinerjaSKPAdapter adapterPesan1 = new LapKinerjaSKPAdapter(LapKinerjaSKP.this, list, LapKinerjaSKP.this);
                        //  swipeMain.setRefreshing(false);
                        rvList.setAdapter(adapterPesan1);
                        adapterPesan1.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    } catch (Exception e) {
                        //Toast.makeText(LapKinerjaSKP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                    Toast.makeText(LapKinerjaSKP.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                    rvList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseLapKinerjaSKP> call, Throwable t) {
                //Toast.makeText(LapKinerjaSKP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
                rvList.setVisibility(View.GONE);
            }
        });
    }

    private void getMonth() {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseMaxDate> call = apiService.getMaxDate();
        progressDialog.show();
        call.enqueue(new Callback<ResponseMaxDate>() {
            @Override
            public void onResponse(Call<ResponseMaxDate> call, Response<ResponseMaxDate> response) {
                List<MaxDateItem> hasilPesan = response.body().getMaxDate();
                Log.e("Bulan", "Hasil bulan List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        progressDialog.dismiss();
                        //spinner
                        adapterSPBulan = new ArrayAdapter<>(LapKinerjaSKP.this, R.layout.support_simple_spinner_dropdown_item,
                                YearAndMonth.getMonth(hasilPesan));
                        //adapterSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //set adapter
                        spBulan.setAdapter(adapterSPBulan);
                        //load jika ada data baru
                        adapterSPBulan.notifyDataSetChanged();
                    } catch (Exception e) {
                        progressDialog.dismiss();
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaxDate> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private List<KuantitasItem> getKuantitas() {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseKuantitas> call = apiService.getKuantitas();

        call.enqueue(new Callback<ResponseKuantitas>() {
            @Override
            public void onResponse(Call<ResponseKuantitas> call, Response<ResponseKuantitas> response) {
                //hasilPesan = response.body().getKuantitas();
                //Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));
                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {

                        kuantitasItems = response.body().getKuantitas();
                    } catch (Exception e) {
                        Toast.makeText(LapKinerjaSKP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                }
            }

            @Override
            public void onFailure(Call<ResponseKuantitas> call, Throwable t) {
                Toast.makeText(LapKinerjaSKP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return kuantitasItems;
    }

    private List<SkpTahunanItem> getSkpTahunan() {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseSKPTahunan> call = apiService.getYear(sharedLogin.getNip());

        call.enqueue(new Callback<ResponseSKPTahunan>() {
            @Override
            public void onResponse(Call<ResponseSKPTahunan> call, Response<ResponseSKPTahunan> response) {
                //hasilPesan = response.body().getKuantitas();
                //Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));
                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {

                        lapKinerjaSkpItems = response.body().getSkpTahunan();
                    } catch (Exception e) {
                        Toast.makeText(LapKinerjaSKP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                }
            }

            @Override
            public void onFailure(Call<ResponseSKPTahunan> call, Throwable t) {
                Toast.makeText(LapKinerjaSKP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return lapKinerjaSkpItems;
    }

    @OnClick({R.id.cari, R.id.tambah})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cari:
                getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
                break;
            case R.id.tambah:
                dialogForm("" , "", "", 0,0, "",
                        "SIMPAN", "Simpan Data");
                break;
        }
    }

    @Override
    public void onSelected(LapKinerjaSkpItem data) {
        dialogForm(data.getIdLapSkp(), data.getKegiatanSkp(), data.getKuantitasSkp(), data.getSatuanSkp(),
                data.getSatuanSkp(), data.getStatusSkp(), "UBAH", "Ubah Data");
    }

    public void dialogForm(final String id, final String kegiatan, final String kuantitas, final int spsatuan,
                           final int spkuantitas, String status, String button, String modif) {
        //buat dialog
        dialog = new AlertDialog.Builder(LapKinerjaSKP.this);
        //buat layout
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.insert_lap_kinerja_skp, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.clipboard);
        dialog.setTitle(modif);

        //inisialisasi
        edtId = dialogView.findViewById(R.id.edt_id);
        edtKegiatan = dialogView.findViewById(R.id.edt_kegiatan);
        edtJumlah = dialogView.findViewById(R.id.edt_jumlah);
        spKuantitas = dialogView.findViewById(R.id.sp_kuantitas);
        spSKPTahunan = dialogView.findViewById(R.id.sp_skp_tahunan);

        //spiner kuantitas
        adapterKuantitas = new KuantitasAdapter(LapKinerjaSKP.this, kuantitasItems);
        spKuantitas.setAdapter(adapterKuantitas);
        adapterKuantitas.notifyDataSetChanged();

        //spiner skp
        adapterSKPTahunan = new SKPTahunanAdapter(LapKinerjaSKP.this, lapKinerjaSkpItems);
        spSKPTahunan.setAdapter(adapterSKPTahunan);
        adapterSKPTahunan.notifyDataSetChanged();

        spKuantitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SKP.this, kuantitasItems.get(position).getKuantitas(), Toast.LENGTH_SHORT).show();
                kuantitas2 = kuantitasItems.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSKPTahunan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SKP.this, kuantitasItems.get(position).getKuantitas(), Toast.LENGTH_SHORT).show();
                skpTahunan2 = lapKinerjaSkpItems.get(position).getIdSkp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //cek jika kosong
        if (!id.isEmpty()) {
            //jika id kosong set nilai
            edtId.setText(id);
            edtKegiatan.setText(kegiatan);
            edtJumlah.setText(kuantitas);
            //select default spinner kuantitas
            for (int i = 0; i < kuantitasItems.size(); i++) {
                if (Integer.parseInt(kuantitasItems.get(i).getId()) == spkuantitas) {
                    //get position
                    spKuantitas.setSelection(i);
                }
            }

            //select default spinner skp tahunan
            for (int i = 0; i < lapKinerjaSkpItems.size(); i++) {
                if (Integer.parseInt(lapKinerjaSkpItems.get(i).getId()) == spsatuan) {
                    //get position
                    spSKPTahunan.setSelection(i);
                }
            }
            /*if (kesesuaian != null) {
                //ambil posisi sesuai isi string
                int posisi = adapterKesesuain.getPosition(kesesuaian);
                //set select awal spiner
                spKesesuain.setSelection(posisi);
            }*/
        } else {
            //kosong();
        } //end if

        // setting button
        //save
        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (id.isEmpty()) {
                    if ((!TextUtils.isEmpty(edtKegiatan.getText().toString()) && !TextUtils.isEmpty(edtJumlah.getText().toString()))
                            && !TextUtils.isEmpty(kuantitas2)) {
                        simpan(sharedLogin.getNip(), informasi.getTanggal(), getTextEditText(edtKegiatan), getTextEditText(edtJumlah),
                                kuantitas2, skpTahunan2, informasi.getIpaddress(), sharedLogin.getNamaAdmin()
                                , informasi.getTanggal());
                    } else {
                        Toast.makeText(LapKinerjaSKP.this, "Data ada yang kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    update(id, edtKegiatan.getText().toString(), edtJumlah.getText().toString(), kuantitas2, skpTahunan2,
                            informasi.getIpaddress(), sharedLogin.getNamaAdmin(), informasi.getTanggal());
                }
                //keluar
                dialog.dismiss();
            }
        });

        //setting button
        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //keluar
                dialog.dismiss();
                //kosong();
            }
        });
        //show dialog
        dialog.show();
    }

    private void simpan(final String nip, final String tanggal, final String kegiatan, final String kuantitas, final String satuan,
                        final String idSkp, final String updateFrom, String updateBy, String updateOn) {
        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.simpanLapSKP(nip, tanggal, kegiatan, kuantitas, satuan, idSkp, updateFrom, updateBy, updateOn);
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    Toast.makeText(LapKinerjaSKP.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
                } else {
                    Toast.makeText(LapKinerjaSKP.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(LapKinerjaSKP.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(String id, final String kegiatan, final String kuantitas, final String satuan,
                        final String idSkp, final String updateFrom, String updateBy, String updateOn) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.updateLapSKP(id, kegiatan, kuantitas, satuan, idSkp, updateFrom, updateBy,
                updateOn);
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    Toast.makeText(LapKinerjaSKP.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
                } else {
                    Toast.makeText(LapKinerjaSKP.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(LapKinerjaSKP.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
