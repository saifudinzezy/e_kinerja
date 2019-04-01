package com.example.cyber_net.e_kinerja.activity.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.adapter.BawahanAdapter;
import com.example.cyber_net.e_kinerja.model.ResponseNamaBawahan;
import com.example.cyber_net.e_kinerja.model.item.NamaBawahanItem;
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

public class NamaBawahan extends AppCompatActivity implements BawahanAdapter.OnFunction, SearchView.OnQueryTextListener,
        MenuItem.OnActionExpandListener{

    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    BawahanAdapter adapter;
    SharedLogin sharedLogin;
    List<NamaBawahanItem> hasilPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persetujuan_atasan);
        ButterKnife.bind(this);

        sharedLogin = new SharedLogin(this);
        rvList.setLayoutManager(new VegaLayoutManager());
        getSupportActionBar().setTitle("Nama PNS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hasilPesan = new ArrayList<>();

        getBawahan(sharedLogin.getNip());
    }

    private void getBawahan(String nip) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseNamaBawahan> call = apiService.getNamaBawahan(nip);
        call.enqueue(new Callback<ResponseNamaBawahan>() {
            @Override
            public void onResponse(Call<ResponseNamaBawahan> call, Response<ResponseNamaBawahan> response) {
                hasilPesan = response.body().getNamaBawahan();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getResponse().equalsIgnoreCase("true")) {
                    try {
                        ArrayList<NamaBawahanItem> list = new ArrayList<>();
                        list.addAll(hasilPesan);

                        adapter = new BawahanAdapter(list, NamaBawahan.this, NamaBawahan.this);
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
            public void onFailure(Call<ResponseNamaBawahan> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSelected(NamaBawahanItem data) {
        SharedLogin sharedLogin = new SharedLogin(NamaBawahan.this);
        sharedLogin.saveLoker2(SharedLogin.SP_LOKER2, data.getNip());
        startActivity(new Intent(this, ListLaporan.class));
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.  search, menu);
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

        ArrayList<NamaBawahanItem> filteredValues = new ArrayList<>(hasilPesan);
        for (NamaBawahanItem value : hasilPesan) {
            if (!value.getNama().toLowerCase().contains(newText.toLowerCase())) {
                filteredValues.remove(value);
            }
        }

        adapter = new BawahanAdapter(filteredValues, NamaBawahan.this, NamaBawahan.this);
        rvList.setAdapter(adapter);

        return false;
    }

    public void resetSearch() {
        adapter = new BawahanAdapter(hasilPesan, NamaBawahan.this, NamaBawahan.this);
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
}