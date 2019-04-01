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
import com.example.cyber_net.e_kinerja.adapter.SKPAdapter;
import com.example.cyber_net.e_kinerja.adapter.spinner.KuantitasAdapter;
import com.example.cyber_net.e_kinerja.helper.Informasi;
import com.example.cyber_net.e_kinerja.model.ResponseInformasi;
import com.example.cyber_net.e_kinerja.model.ResponseInsert;
import com.example.cyber_net.e_kinerja.model.ResponseKuantitas;
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

import static com.example.cyber_net.e_kinerja.helper.CariBulan.cariNamaBulan;
import static com.example.cyber_net.e_kinerja.helper.CariBulan.cariNomerBulan;
import static com.example.cyber_net.e_kinerja.helper.ConvertDate.changeStringToDate;
import static com.example.cyber_net.e_kinerja.helper.ConvertDate.customTanggal;
import static com.example.cyber_net.e_kinerja.helper.YearAndMonth.isWithinRange;

public class SKP2 extends AppCompatActivity implements SKPAdapter.SKPAdapterListener {

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

    //get date
    ArrayAdapter<String> adapterSP, adapterSPBulan;
    SharedLogin sharedLogin;
    ProgressDialog progressDialog;
    ResponseInformasi informasi;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText edtId, edtKegiatan, edtKuantitas, edtSatuan, edtTarget;
    Spinner spKuantitas;
    KuantitasAdapter adapterKuantitas;
    //insert
    List<KuantitasItem> kuantitasItems;
    String year, bulan;
    public String kuantitas2;
    @BindView(R.id.perbandingan)
    TextView perbandingan;
    @BindView(R.id.start_date)
    TextView startDate;
    @BindView(R.id.end_date)
    TextView endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lap);
        ButterKnife.bind(this);

        informasi = new ResponseInformasi();
        sharedLogin = new SharedLogin(this);
        kuantitasItems = new ArrayList<>();

        getSupportActionBar().setTitle("SKP Tahunan");
        //getSupportActionBar().setIcon(R.drawable.notepad);

        rvList.setLayoutManager(new VegaLayoutManager());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu Sebentar...");

        //get data
        //getInformasi();
        informasi = Informasi.getInformasi(progressDialog, SKP2.this);

        getYear();
        getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
        getMonth();
        kuantitasItems = getKuantitas();
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
                    //Toast.makeText(KinerjaProd.this, "" + cariNomerBulan(bulan), Toast.LENGTH_SHORT).show();
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
                        startDate.setText(hasilPesan.get(0).getAwal());
                        endDate.setText(hasilPesan.get(0).getAkhir());

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
                        adapterSP = new ArrayAdapter<>(SKP2.this, R.layout.support_simple_spinner_dropdown_item,
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
        Call<ResponseSKPTahunan> call = apiService.getYear2(nip, tahun, bulan);
        call.enqueue(new Callback<ResponseSKPTahunan>() {
            @Override
            public void onResponse(Call<ResponseSKPTahunan> call, Response<ResponseSKPTahunan> response) {
                List<SkpTahunanItem> hasilPesan = response.body().getSkpTahunan();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        rvList.setVisibility(View.VISIBLE);
                        ArrayList<SkpTahunanItem> list = new ArrayList<>();
                        list.addAll(hasilPesan);

                        SKPAdapter adapterPesan1 = null;
                        if (isWithinRange(changeStringToDate(informasi.getTanggal()), changeStringToDate(startDate),
                                changeStringToDate(endDate))){
                            //System.out.println("betul");
                            //benar jika tangal berada di range ini
                            adapterPesan1 = new SKPAdapter(SKP2.this, list, SKP2.this, true);
                        }else{
                            //System.out.println("salah");
                            adapterPesan1 = new SKPAdapter(SKP2.this, list, SKP2.this, false);
                        }
                        //  swipeMain.setRefreshing(false);
                        rvList.setAdapter(adapterPesan1);
                        adapterPesan1.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    } catch (Exception e) {
                        Toast.makeText(SKP2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                    Toast.makeText(SKP2.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                    rvList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseSKPTahunan> call, Throwable t) {
                Toast.makeText(SKP2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        //spinner
                        adapterSPBulan = new ArrayAdapter<>(SKP2.this, R.layout.support_simple_spinner_dropdown_item,
                                listB);
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

    public void dialogForm(final String id, String nip, final String kegiatan, final String kuantitas,
                           final String satuan, final String target, int sp, String button, String modif) {
        //buat dialog
        dialog = new AlertDialog.Builder(SKP2.this);
        //buat layout
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.insert_skp, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.archive);
        dialog.setTitle(modif);

        //inisialisasi
        edtId = dialogView.findViewById(R.id.edt_id);
        edtKegiatan = dialogView.findViewById(R.id.edt_kegiatan);
        edtKuantitas = dialogView.findViewById(R.id.edt_target_kuantitas);
        edtSatuan = dialogView.findViewById(R.id.edt_satuan);
        edtTarget = dialogView.findViewById(R.id.edt_selesai);
        spKuantitas = dialogView.findViewById(R.id.sp_kuantitas);

        //spiner
        adapterKuantitas = new KuantitasAdapter(SKP2.this, kuantitasItems);
        spKuantitas.setAdapter(adapterKuantitas);
        adapterKuantitas.notifyDataSetChanged();

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

        //cek jika kosong
        if (!id.isEmpty()) {
            //jika id kosong set nilai
            edtId.setText(id);
            edtKegiatan.setText(kegiatan);
            edtKuantitas.setText(kuantitas);
            edtSatuan.setText(satuan);
            edtTarget.setText(target);

            //select default spinner
            for (int i = 0; i < kuantitasItems.size(); i++) {
                if (Integer.parseInt(kuantitasItems.get(i).getId()) == sp) {
                    //get position
                    spKuantitas.setSelection(i);
                }
            }

        } else {
            kosong();
        } //end if

        // setting button
        //save
        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (id.isEmpty()) {
                    if ((!TextUtils.isEmpty(edtKegiatan.getText().toString()) && !TextUtils.isEmpty(edtKuantitas.getText().toString())) &&
                            ((!TextUtils.isEmpty(kuantitas2) && !TextUtils.isEmpty(edtTarget.getText().toString())))) {
                        simpan(sharedLogin.getNip(), informasi.getTanggal(), edtKegiatan.getText().toString(), edtKuantitas.getText().toString(),
                                kuantitas2, edtTarget.getText().toString(), informasi.getIpaddress(), sharedLogin.getNamaAdmin()
                                , informasi.getTanggal());
                    } else {
                        Toast.makeText(SKP2.this, "Data ada yang kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    update(id, edtKegiatan.getText().toString(), edtKuantitas.getText().toString(), kuantitas2,
                            edtTarget.getText().toString(), informasi.getIpaddress(), sharedLogin.getNamaAdmin(), informasi.getTanggal());
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
                kosong();
            }
        });
        //show dialog
        dialog.show();
    }

    @Override
    public void onSelected(SkpTahunanItem data) {
        dialogForm(data.getIdSkp(), data.getNip(), data.getKegiatan(), data.getTargetKuantitas(),
                data.getSatuanKuantitas(), data.getTargetSelesai(), Integer.parseInt(data.getId()), "UBAH", "Ubah Data");
    }

    private void simpan(final String nip, final String tahun, final String kegiatan, final String kuantitas, final String satuan,
                        final String target, final String updateFrom, String updateBy, String updateOn) {
        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.simpanSKP(nip, tahun, kegiatan, kuantitas, satuan, target, updateFrom, updateBy, updateOn);
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    Toast.makeText(SKP2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
                } else {
                    Toast.makeText(SKP2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(SKP2.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(String id, final String kegiatan, final String kuantitas, final String satuan, final String target,
                        final String updateFrom, String updateBy, String updateOn) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.updateSKP(id, kegiatan, kuantitas, satuan, target, updateFrom, updateBy, updateOn);

        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    Toast.makeText(SKP2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
                } else {
                    Toast.makeText(SKP2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(SKP2.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        //load jika ada data baru
                        /*adapter = new KuantitasAdapter(SKP.this, hasilPesan);
                        spKuantitas.setAdapter(adapter);
                        adapter.notifyDataSetChanged();*/
                        kuantitasItems = response.body().getKuantitas();
                    } catch (Exception e) {
                        Toast.makeText(SKP2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                }
            }

            @Override
            public void onFailure(Call<ResponseKuantitas> call, Throwable t) {
                Toast.makeText(SKP2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return kuantitasItems;
    }


    @OnClick({R.id.cari, R.id.tambah})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cari:
                getYear(txtTahun.getText().toString(), sharedLogin.getNip(), txtBulan.getText().toString());
                break;
            case R.id.tambah:
                dialogForm("", "", "", "", "", "", 0, "SIMPAN", "Simpan Data");
                break;
        }
    }

    private void kosong() {
        edtId.setText(null);
        edtKegiatan.setText(null);
        edtKuantitas.setText(null);
        edtSatuan.setText(null);
    }
}
