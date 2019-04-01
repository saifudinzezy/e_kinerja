package com.example.cyber_net.e_kinerja.activity.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.adapter.AtasanAdapter;
import com.example.cyber_net.e_kinerja.model.ResponseAtasan;
import com.example.cyber_net.e_kinerja.model.ResponseInsert;
import com.example.cyber_net.e_kinerja.model.ResponseNamaAtasan;
import com.example.cyber_net.e_kinerja.model.item.AtasanItem;
import com.example.cyber_net.e_kinerja.model.item.NamaAtasanItem;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;
import com.example.cyber_net.e_kinerja.shared.SharedLogin;
import com.google.gson.Gson;
import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.e_kinerja.helper.FunctionError.getTextView;
import static com.example.cyber_net.e_kinerja.helper.FunctionError.setTextView;

public class UbahAtasan extends AppCompatActivity implements AtasanAdapter.OnFunction, SearchView.OnQueryTextListener,
        MenuItem.OnActionExpandListener {

    @BindView(R.id.txt_nip)
    TextView txtNip;
    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_loker)
    TextView txtLoker;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    AtasanAdapter adapter;
    SharedLogin sharedLogin;
    @BindView(R.id.txt_jabatan)
    TextView txtJabatan;
    List<AtasanItem> hasilPesan;
    @BindView(R.id.txt_id)
    TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_atasan);
        ButterKnife.bind(this);

        sharedLogin = new SharedLogin(this);
        rvList.setLayoutManager(new VegaLayoutManager());
        getSupportActionBar().setTitle("Ubah Atasan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hasilPesan = new ArrayList<>();

        getNamaAtasan(sharedLogin.getNip());
        getAtasan(sharedLogin.getLoker());
    }

    private void getAtasan(String loker) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseAtasan> call = apiService.getNamaAtasan(loker);
        call.enqueue(new Callback<ResponseAtasan>() {
            @Override
            public void onResponse(Call<ResponseAtasan> call, Response<ResponseAtasan> response) {
                hasilPesan = response.body().getAtasan();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        ArrayList<AtasanItem> list = new ArrayList<>();
                        list.addAll(hasilPesan);

                        adapter = new AtasanAdapter(list, UbahAtasan.this, UbahAtasan.this);
                        //  swipeMain.setRefreshing(false);
                        rvList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    } catch (Exception e) {

                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                }
            }

            @Override
            public void onFailure(Call<ResponseAtasan> call, Throwable t) {

            }
        });
    }

    private void getNamaAtasan(String nip) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseNamaAtasan> call = apiService.getNamaAtasan2(nip);
        call.enqueue(new Callback<ResponseNamaAtasan>() {
            @Override
            public void onResponse(Call<ResponseNamaAtasan> call, Response<ResponseNamaAtasan> response) {
                List<NamaAtasanItem> data = response.body().getNamaAtasan();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        setTextView(txtId, data.get(0).getId());
                        setTextView(txtNama, data.get(0).getNama());
                        setTextView(txtNip, data.get(0).getNip());
                        setTextView(txtJabatan, data.get(0).getNamaJab());
                        setTextView(txtLoker, data.get(0).getLoker());
                        pb.setVisibility(View.GONE);
                    } catch (Exception e) {

                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                }
            }

            @Override
            public void onFailure(Call<ResponseNamaAtasan> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSelected(AtasanItem data) {
        setTextView(txtNama, data.getNama());
        setTextView(txtNip, data.getNip());
        setTextView(txtJabatan, data.getNamaJab());
        setTextView(txtLoker, data.getLoker());
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_and_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Nama PNS");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return false;
            case R.id.action_add:
                simpan(getTextView(txtId), getTextView(txtNip));
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            resetSearch();
            return false;
        }

        ArrayList<AtasanItem> filteredValues = new ArrayList<>(hasilPesan);
        for (AtasanItem value : hasilPesan) {
            if (!value.getNama().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }

        adapter = new AtasanAdapter(filteredValues, UbahAtasan.this, UbahAtasan.this);
        rvList.setAdapter(adapter);

        return false;
    }

    public void resetSearch() {
        adapter = new AtasanAdapter(hasilPesan, UbahAtasan.this, UbahAtasan.this);
        rvList.setAdapter(adapter);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }

    private void simpan(final String id, final String nip) {
        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.updateAtasan(id, nip);
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    Toast.makeText(UbahAtasan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UbahAtasan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(UbahAtasan.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
