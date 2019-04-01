package com.example.cyber_net.e_kinerja.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.item.AtasanItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AtasanAdapter extends RecyclerView.Adapter<AtasanAdapter.ViewHolder> {
    private List<AtasanItem> list;
    private Context context;
    private OnFunction listener;

    public AtasanAdapter(List<AtasanItem> list, Context context, OnFunction listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_atasan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtJabatan.setText(list.get(position).getNamaJab());
        holder.txtLoker.setText(list.get(position).getLoker());
        holder.txtNama.setText(list.get(position).getNama());
        holder.txtNip.setText(list.get(position).getNip());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nip)
        TextView txtNip;
        @BindView(R.id.txt_nama)
        TextView txtNama;
        @BindView(R.id.txt_jabatan)
        TextView txtJabatan;
        @BindView(R.id.txt_loker)
        TextView txtLoker;
        @BindView(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnFunction{
        void onSelected(AtasanItem data);
    }
}
