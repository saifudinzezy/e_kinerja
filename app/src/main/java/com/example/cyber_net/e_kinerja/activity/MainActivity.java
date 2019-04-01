package com.example.cyber_net.e_kinerja.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.activity.menu.UbahAtasan;
import com.example.cyber_net.e_kinerja.activity.menu.UbahPass;
import com.example.cyber_net.e_kinerja.adapter.MenuAdapter;
import com.example.cyber_net.e_kinerja.model.MenuModel;
import com.example.cyber_net.e_kinerja.shared.SharedLogin;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.cyber_net.e_kinerja.helper.Contans.TABEL_ID_LAP_PROD;
import static com.example.cyber_net.e_kinerja.helper.Contans.TABEL_LAP_KINERJA_PRODUKTIVITAS;
import static com.example.cyber_net.e_kinerja.helper.ListMenu.getMenu;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_SUDAH_LOGIN2;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnSelect{

    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nama)
    TextView nama;
    SharedLogin sharedLogin;
    LinearLayout pass, atasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMenu.setLayoutManager(gridLayoutManager);
        List<MenuModel> listMenu = new ArrayList<>();
        listMenu = getMenu(listMenu);
        MenuAdapter adapter = new MenuAdapter(this, listMenu, this);
        rvMenu.setAdapter(adapter);

        sharedLogin = new SharedLogin(this);
        Spanned nama = Html.fromHtml("<b>" + sharedLogin.getNamaAdmin() + "</b>");
        this.nama.setText("Selamat Datang,  " + nama +
                "(" + sharedLogin.getNip() + ")"
                + "\n" + sharedLogin.getUnitKerja() + "\n" + sharedLogin.getOpd());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            alert();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void alert() {
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(MainActivity.this);
        //settting judul dan pesan
        aleBuilder.setTitle("Keluar");
        aleBuilder
                .setMessage("Apakah anda yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedLogin.saveSPBoolean(SP_SUDAH_LOGIN2, false);
                        startActivity(new Intent(MainActivity.this, Login.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //cancel
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }

    @Override
    public void select() {
        //Toast.makeText(this, "klil", Toast.LENGTH_SHORT).show();
        View view = getLayoutInflater().inflate(R.layout.item_settings, null);

        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        pass = view.findViewById(R.id.pass);
        atasan = view.findViewById(R.id.atasan);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UbahPass.class));
            }
        });
        
        atasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UbahAtasan.class));
            }
        });
        

        dialog.setContentView(view);
        dialog.show();}
}
