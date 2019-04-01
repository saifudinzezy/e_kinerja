package com.example.cyber_net.e_kinerja.activity.menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.ResponseInsert;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;
import com.example.cyber_net.e_kinerja.shared.SharedLogin;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.e_kinerja.shared.SharedLogin.SP_PASSWORD_ADMIN;

public class UbahPass extends AppCompatActivity {

    @BindView(R.id.edt_pass1)
    EditText edtPass1;
    @BindView(R.id.edt_pass2)
    ShowHidePasswordEditText edtPass2;
    @BindView(R.id.btn_ubah)
    AppCompatButton btnUbah;
    @BindView(R.id.txt_notif)
    TextView txtNotif;
    SharedLogin sharedLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_pass);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Ubah Password");
        sharedLogin = new SharedLogin(this);

        edtPass2.addTextChangedListener(inputPasswordWatcher);
        txtNotif.setVisibility(View.GONE);
    }

    private final TextWatcher inputPasswordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //method ini  wajib ada meskipun disini kosong valuenya
            txtNotif.setVisibility(View.GONE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!edtPass1.getText().toString().equals(edtPass2.getText().toString())) {
                txtNotif.setVisibility(View.VISIBLE);
            } else {
                txtNotif.setVisibility(View.GONE);
            }
        }
    };

    @OnClick(R.id.btn_ubah)
    public void onViewClicked() {
        if (TextUtils.isEmpty(edtPass1.getText().toString()) && TextUtils.isEmpty(edtPass2.getText().toString())) {
            Toast.makeText(this, "Isian Kosong", Toast.LENGTH_SHORT).show();
        } else {
            update(sharedLogin.getNip(), edtPass1.getText().toString());
            sharedLogin.savePassAdmin(SP_PASSWORD_ADMIN, edtPass1.getText().toString());
        }
    }

    private void update(String nip, final String pass) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Tunggu Sebentar...");

        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.updateUser(nip, pass);
        dialog.show();
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if (response.body().getCode() == 200) {
                    dialog.dismiss();
                    Toast.makeText(UbahPass.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UbahPass.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(UbahPass.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}