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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.adapter.KinerjaProdAdapter;
import com.example.cyber_net.e_kinerja.helper.DialogSKP;
import com.example.cyber_net.e_kinerja.helper.Informasi;
import com.example.cyber_net.e_kinerja.model.ResponseInformasi;
import com.example.cyber_net.e_kinerja.model.ResponseInsert;
import com.example.cyber_net.e_kinerja.model.ResponseKinerjaProduktivitas;
import com.example.cyber_net.e_kinerja.model.item.KinerjaProduktivitasItem;
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

import static com.example.cyber_net.e_kinerja.helper.ConvertDate.customTanggal;

public class KinerjaProduktifitas extends AppCompatActivity implements KinerjaProdAdapter.SKPAdapterListener {
    @BindView(R.id.skp)
    Spinner skp;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.rv_skp)
    RecyclerView rvSkp;
    @BindView(R.id.tambah)
    FloatingActionButton tambah;
    ProgressDialog progressDialog;
    ResponseInformasi informasi;
    ArrayAdapter<String> adapterSP;
    SharedLogin sharedLogin;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText edtId, edtKegiatan, edtKuantitas, edtSatuan;
    String year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skp);
        ButterKnife.bind(this);

        informasi = new ResponseInformasi();
        sharedLogin = new SharedLogin(this);

        getSupportActionBar().setTitle("Lap .Kinerja Produktifitas");
        //getSupportActionBar().setIcon(R.drawable.notepad);

        rvSkp.setLayoutManager(new VegaLayoutManager());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu Sebentar...");

        //get data
        //getInformasi();
        informasi = Informasi.getInformasi(progressDialog, KinerjaProduktifitas.this);

        getYear();
        skp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = adapterSP.getItem(position).toString();
                if (!year.isEmpty()) {
                    getYear(year, sharedLogin.getNip());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // hide fab
        rvSkp.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && tambah.getVisibility() == View.VISIBLE) {
                    tambah.hide();
                } else if (dy < 0 && tambah.getVisibility() != View.VISIBLE) {
                    tambah.show();
                }
            }
        });
    }

    @OnClick(R.id.tambah)
    public void onViewClicked() {
        dialogForm("", "", "", "", "", "SIMPAN", "Simpan Data");
    }

    private void getInformasi() {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseInformasi> call = apiService.getInformasi();
        progressDialog.show();
        call.enqueue(new Callback<ResponseInformasi>() {
            @Override
            public void onResponse(Call<ResponseInformasi> call, Response<ResponseInformasi> response) {
                Log.d("tanggal", response.body().getTanggal());
                try {
                    //Toast.makeText(KinerjaProduktifitas.this, response.body().getTanggal(), Toast.LENGTH_SHORT).show();
                    //get data
                    informasi.setIpaddress(response.body().getIpaddress());
                    informasi.setTanggal(response.body().getTanggal());
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    progressDialog.dismiss();
                    Toast.makeText(KinerjaProduktifitas.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseInformasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KinerjaProduktifitas.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());
            }
        });
    }

    private void getYear() {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseKinerjaProduktivitas> call = apiService.getYearKinerjaProd(sharedLogin.getNip());
        progressDialog.show();
        call.enqueue(new Callback<ResponseKinerjaProduktivitas>() {
            @Override
            public void onResponse(Call<ResponseKinerjaProduktivitas> call, Response<ResponseKinerjaProduktivitas> response) {
                List<KinerjaProduktivitasItem> hasilPesan = response.body().getKinerjaProduktivitas();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        progressDialog.dismiss();
                        ArrayList<String> listB = new ArrayList<String>();

                        for (int j = 0; j < hasilPesan.size(); j++) {
                            Log.d("Tahun", "tahun list A -> " + hasilPesan);
                            String thn = customTanggal(hasilPesan.get(j).getTanggal(), "yyyy-MM-dd HH:mm:ss", "yyyy");
                            //jika data tidak sama (contains) maka masukan data
                            if (!listB.contains(thn)) {
                                listB.add(thn);
                                Log.d("Tahun", "tahun list B -> " + listB);
                            }
                        }
                        //spinner
                        adapterSP = new ArrayAdapter<>(KinerjaProduktifitas.this, R.layout.support_simple_spinner_dropdown_item,
                                listB);
                        //adapterSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //set adapter
                        skp.setAdapter(adapterSP);
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
            public void onFailure(Call<ResponseKinerjaProduktivitas> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void getYear(String tahun, String nip) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseKinerjaProduktivitas> call = apiService.getYearKinerjaProd(nip, tahun);
        call.enqueue(new Callback<ResponseKinerjaProduktivitas>() {
            @Override
            public void onResponse(Call<ResponseKinerjaProduktivitas> call, Response<ResponseKinerjaProduktivitas> response) {
                List<KinerjaProduktivitasItem> hasilPesan = response.body().getKinerjaProduktivitas();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        ArrayList<KinerjaProduktivitasItem> list = new ArrayList<>();
                        list.addAll(hasilPesan);

                        KinerjaProdAdapter adapterPesan1 = new KinerjaProdAdapter(KinerjaProduktifitas.this, list, KinerjaProduktifitas.this);
                        //  swipeMain.setRefreshing(false);
                        rvSkp.setAdapter(adapterPesan1);
                        adapterPesan1.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    } catch (Exception e) {

                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                }
            }

            @Override
            public void onFailure(Call<ResponseKinerjaProduktivitas> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSelected(KinerjaProduktivitasItem data) {
        //Toast.makeText(this, data.getStatus(), Toast.LENGTH_SHORT).show();
        dialogForm(data.getIdLapProd(), data.getNip(), data.getKegiatan(), data.getKuantitas(),
                data.getSatuan(), "UBAH", "Ubah Data");
    }

    public void dialogForm(final String id, String nip, final String kegiatan, final String kuantitas,
                           final String satuan, String button, String modif) {
        //buat dialog
        dialog = new AlertDialog.Builder(KinerjaProduktifitas.this);
        //buat layout
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.insert_kinerja_prod, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.notepad);
        dialog.setTitle(modif);

        //inisialisasi
        edtId = dialogView.findViewById(R.id.edt_id);
        edtKegiatan = dialogView.findViewById(R.id.edt_kegiatan);
        edtKuantitas = dialogView.findViewById(R.id.edt_kuantitas);
        edtSatuan = dialogView.findViewById(R.id.edt_satuan);

        //cek jika kosong
        if (!id.isEmpty()) {
            //jika id kosong set nilai
            edtId.setText(id);
            edtKegiatan.setText(kegiatan);
            edtKuantitas.setText(kuantitas);
            edtSatuan.setText(satuan);
        } else {
            kosong();
        } //end if

        // setting button
        //save
        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (id.isEmpty()) {
                    if ((!TextUtils.isEmpty(edtKegiatan.getText().toString()) && !TextUtils.isEmpty(edtKuantitas.getText().toString()))
                            && !TextUtils.isEmpty(edtSatuan.getText().toString())) {
                        simpan(sharedLogin.getNip(), informasi.getTanggal(), edtKegiatan.getText().toString(), edtKuantitas.getText().toString(),
                                "Belum Disetujui", edtSatuan.getText().toString(), informasi.getIpaddress(), sharedLogin.getNamaAdmin()
                                , informasi.getTanggal());
                    } else {
                        Toast.makeText(KinerjaProduktifitas.this, "Data ada yang kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    update(id, edtKegiatan.getText().toString(), edtKuantitas.getText().toString(), edtSatuan.getText().toString(),
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
                kosong();
            }
        });
        //show dialog
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getYear(year, sharedLogin.getNip());
    }

    private void kosong() {
        edtId.setText(null);
        edtKegiatan.setText(null);
        edtKuantitas.setText(null);
        edtSatuan.setText(null);
    }

    private void simpan(final String nip, final String tanggal, final String kegiatan, final String kuantitas, final String status,
                        final String satuan, final String updateFrom, String updateBy, String updateOn) {
        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.simpanKinerjaProd(nip, tanggal, kegiatan, kuantitas, satuan, status,
                updateFrom, updateBy, updateOn);
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    Toast.makeText(KinerjaProduktifitas.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getYear(year, sharedLogin.getNip());
                } else {
                    Toast.makeText(KinerjaProduktifitas.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(KinerjaProduktifitas.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(String id, final String kegiatan, final String kuantitas, final String satuan,
                        final String updateFrom, String updateBy, String updateOn) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.updateKinerjaProd(id, kegiatan, kuantitas, satuan, updateFrom, updateBy, updateOn);

        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    Toast.makeText(KinerjaProduktifitas.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getYear(year, sharedLogin.getNip());
                } else {
                    Toast.makeText(KinerjaProduktifitas.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(KinerjaProduktifitas.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
