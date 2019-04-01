package com.example.cyber_net.e_kinerja.adapter.persetujuan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.item.LapKinerjaSkpItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.cyber_net.e_kinerja.helper.ConvertDate.ubahTanggal;

public class PersLapSKPAdapter extends RecyclerView.Adapter<PersLapSKPAdapter.ViewHolder> {
    private Context context;
    private List<LapKinerjaSkpItem> itemList;
    private OnFunction listener;
    int setuju = 0;

    public PersLapSKPAdapter(Context context, List<LapKinerjaSkpItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public PersLapSKPAdapter(Context context, List<LapKinerjaSkpItem> itemList, OnFunction listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pers_lap_skp2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtKegiatan.setText(itemList.get(position).getKegiatanSkp());
        holder.txtKuantitas.setText(itemList.get(position).getKuantitasSkp());
        holder.txtSatuan.setText(itemList.get(position).getKuantitasKuantitas());
        holder.txtSkp.setText(itemList.get(position).getSkpTahunanKegiatan());
        holder.txtTanggal.setText(ubahTanggal(itemList.get(position).getTanggalSkp()));
        //cek in
        holder.imgNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //true or checked
                    setuju += 1;
                    //Toast.makeText(context, "" + setuju, Toast.LENGTH_SHORT).show();
                } else {
                    //false or no checked
                    setuju -= 1;
                    //Toast.makeText(context, "" + setuju, Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(context, "" + setuju, Toast.LENGTH_SHORT).show();
                //kirim data
                listener.onSetuju(itemList.get(position), setuju, itemList.size() - setuju);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_notif)
        CheckBox imgNotif;
        @BindView(R.id.txt_tanggal)
        TextView txtTanggal;
        @BindView(R.id.txt_kegiatan)
        TextView txtKegiatan;
        @BindView(R.id.txt_kuantitas)
        TextView txtKuantitas;
        @BindView(R.id.txt_satuan)
        TextView txtSatuan;
        @BindView(R.id.txt_skp)
        TextView txtSkp;
        @BindView(R.id.txt_hasil)
        TextView txtHasil;
        @BindView(R.id.linear)
        LinearLayout linear;
        @BindView(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnFunction {
        void onSetuju(LapKinerjaSkpItem data, Integer yes, Integer no);
    }
}
