package com.example.cyber_net.e_kinerja.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.activity.menu.LapKinerjaSKP;
import com.example.cyber_net.e_kinerja.adapter.LapKinerjaSKPAdapter;
import com.example.cyber_net.e_kinerja.adapter.persetujuan.PersLapSKPAdapter;
import com.example.cyber_net.e_kinerja.helper.YearAndMonth;
import com.example.cyber_net.e_kinerja.model.ResponseLapKinerjaSKP;
import com.example.cyber_net.e_kinerja.model.ResponseMaxDate;
import com.example.cyber_net.e_kinerja.model.item.LapKinerjaSkpItem;
import com.example.cyber_net.e_kinerja.model.item.MaxDateItem;
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
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.e_kinerja.helper.CariBulan.cariNomerBulan;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersLapKinerSKP extends Fragment{


    @BindView(R.id.perbandingan)
    TextView perbandingan;
    @BindView(R.id.start_date)
    TextView startDate;
    @BindView(R.id.end_date)
    TextView endDate;
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
    Unbinder unbinder;

    //
    ProgressDialog progressDialog;
    ArrayAdapter<String> adapterSP, adapterSPBulan;
    String year, bulan;
    PersLapSKPAdapter adapterPersLapKinerSKP;
    SharedLogin sharedLogin;

    public PersLapKinerSKP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_all_lap, container, false);
        unbinder = ButterKnife.bind(this, view);

        tambah.setVisibility(View.GONE);
        txtNotif.setText(getActivity().getResources().getString(R.string.notif_hold));
        pb.setVisibility(View.GONE);
        rvList.setLayoutManager(new VegaLayoutManager());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Tunggu Sebentar...");
        sharedLogin = new SharedLogin(getActivity());

        getMonthAndYear();

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
                    txtNotif.setVisibility(View.GONE);
                } else if (dy < 0 && tambah.getVisibility() != View.VISIBLE) {
                    txtNotif.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getMonthAndYear() {
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
                        adapterSPBulan = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item,
                                YearAndMonth.getMonth(hasilPesan));
                        //adapterSP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //set adapter
                        spBulan.setAdapter(adapterSPBulan);
                        //load jika ada data baru
                        adapterSPBulan.notifyDataSetChanged();

                        //tahun
                        //spinner
                        adapterSP = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item,
                                YearAndMonth.getYear(hasilPesan));
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


    @OnClick(R.id.cari)
    public void onViewClicked() {
        getYear(txtTahun.getText().toString(), sharedLogin.getLoker2(), txtBulan.getText().toString());
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

                        adapterPersLapKinerSKP = new PersLapSKPAdapter(getActivity(), list, new PersLapSKPAdapter.OnFunction() {
                            @Override
                            public void onSetuju(LapKinerjaSkpItem data, Integer yes, Integer no) {
                                Toast.makeText(getActivity(), ""+no, Toast.LENGTH_SHORT).show();
                            }
                        });
                        //  swipeMain.setRefreshing(false);
                        rvList.setAdapter(adapterPersLapKinerSKP);
                        adapterPersLapKinerSKP.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    } catch (Exception e) {
                        //Toast.makeText(LapKinerjaSKP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                    Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
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
}
