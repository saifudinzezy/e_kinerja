package com.example.cyber_net.e_kinerja.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.item.ResponseUser;
import com.example.cyber_net.e_kinerja.model.item.UserItem;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;
import com.example.cyber_net.e_kinerja.shared.SharedLogin;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_LOKER;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_NAMA_ADMIN;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_NIP;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_OPD;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_PASSWORD_ADMIN;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_SUDAH_LOGIN;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_SUDAH_LOGIN2;
import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_UNIT_KERJA;

public class Login extends AppCompatActivity {

    @BindView(R.id.edt_kode)
    EditText edtKode;
    @BindView(R.id.edt_password2)
    ShowHidePasswordEditText edtPassword2;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    SharedLogin sharedLogin;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedLogin = new SharedLogin(this);

        progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        //cek apakah user sudah login
        if (sharedLogin.getSPSudahLogin()) {
            if (sharedLogin.getSPSudahLogin2()) {
                //cek login kedua
                startActivity(new Intent(Login.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
            //cache
            edtKode.setText(sharedLogin.getNip());
            edtPassword2.setText(sharedLogin.getPasswordAdmin());
        }
    }

    private void login(String nama, String password) {
        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponseUser> login = service.login(nama, password);
        login.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                //cek code apakah sukses atau tidak
                progressDialog.dismiss();
                if (response.body().getCode() == 200) {
                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    List<UserItem> dataUser = response.body().getUser();
                    //simpan di sharef pref
                    //simpan id_user, nama_user, email dan password
                    sharedLogin.saveNip(SP_NIP, dataUser.get(0).getNip());
                    sharedLogin.saveNamaAdmin(SP_NAMA_ADMIN, dataUser.get(0).getNama());
                    sharedLogin.savePassAdmin(SP_PASSWORD_ADMIN, edtPassword2.getText().toString());
                    sharedLogin.saveUnitKerja(SP_UNIT_KERJA, dataUser.get(0).getUnitkerja());
                    sharedLogin.saveOpd(SP_OPD, dataUser.get(0).getOpd());
                    sharedLogin.saveLoker(SP_LOKER, dataUser.get(0).getLoker());

                    //buat cache
                    sharedLogin.saveSPBoolean(SP_SUDAH_LOGIN, true);
                    //cek login
                    sharedLogin.saveSPBoolean(SP_SUDAH_LOGIN2, true);
                    //buka home jika berhasil login
                    startActivity(new Intent(Login.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    //hancurkan activity
                    finish();
                } else {
                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                //hilangkan loading
                progressDialog.dismiss();
                Toast.makeText(Login.this, "Koneksi Gagal Keserver", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        progressDialog.show();

        String nip, password;
        if (setError(edtKode, "Masukan NIP") == null || setError(edtPassword2, "Masukan Password") == null) {
            setError(edtKode, "Masukan NIP");
            setError(edtPassword2, "Masukan Password");
        } else {
            login(edtKode.getText().toString(), edtPassword2.getText().toString());
        }
    }

    public static String setError(EditText editText, String error) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError(error);
            return null;
        } else {
            return editText.getText().toString();
        }
    }

    public static String setError(ShowHidePasswordEditText editText, String error) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            editText.setError(error);
            return null;
        } else {
            return editText.getText().toString();
        }
    }
}
