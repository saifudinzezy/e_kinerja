package com.example.cyber_net.e_kinerja.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.cyber_net.e_kinerja.R;

public class DialogSKP extends AppCompatActivity {
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    Context context;
    EditText edtId, edtKegiatan, edtTarget, edtSatuan, edtselesai;
    OnSelect listener;

    public DialogSKP() {
    }

    public DialogSKP(Context context) {
        this.context = context;
    }

    public DialogSKP(Context context, OnSelect listener) {
        this.context = context;
        this.listener = listener;
    }

    public void DialogForm(final String id, final String nip, final String tahun, final String kegiatan, final String target,
                            final String satuan, final String selesai, String button) {
        //buat dialog
        dialog = new AlertDialog.Builder(context);
        //buat layout
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.insert_skp, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.archive);
        dialog.setTitle("Form Biodata");

        //inisialisasi
        edtId = dialogView.findViewById(R.id.edt_id);
        edtKegiatan = dialogView.findViewById(R.id.edt_kegiatan);
        edtTarget = dialogView.findViewById(R.id.edt_selesai);
        edtSatuan = dialogView.findViewById(R.id.edt_satuan);
        edtselesai = dialogView.findViewById(R.id.edt_selesai);

        //cek jika kosong
        if (!id.isEmpty()) {
            //jika id kosong set nilai
            edtId.setText(id);
            edtKegiatan.setText(kegiatan);
            edtTarget.setText(target);
            edtSatuan.setText(satuan);
            edtselesai.setText(selesai);
        } else {
            kosong();
        } //end if

        // setting button
        //save
        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*//ambil nilai
                id = txt_id.getText().toString();
                nama = txt_nama.getText().toString();
                alamat = txt_alamat.getText().toString();*/

                //simpan_update();
                if (!id.isEmpty()) {
                    listener.simpan(nip, tahun, kegiatan, target, satuan, selesai);
                } else {
                    listener.update(id, nip, tahun, kegiatan, target, satuan, selesai);
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

    private void kosong() {
        edtId.setText(null);
        edtKegiatan.setText(null);
        edtTarget.setText(null);
        edtSatuan.setText(null);
        edtselesai.setText(null);
    }

    public interface OnSelect {
        void simpan(String nip, String tahun, String kegiatan, String target,
                    String satuan, String selesai);
        void update(String id, String nip, String tahun, String kegiatan, String target,
                    String satuan, String selesai);
    }
}
