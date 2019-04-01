package com.example.cyber_net.e_kinerja.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.item.NamaBawahanItem;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BawahanAdapter extends RecyclerView.Adapter<BawahanAdapter.ViewHolder> {
    private List<NamaBawahanItem> list;
    private Context context;
    private OnFunction listener;

    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    public BawahanAdapter(List<NamaBawahanItem> list, Context context, OnFunction listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bawahan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNama.setText(list.get(position).getNamaJab());
        holder.txtNip.setText(list.get(position).getNama());

        String user = list.get(position).getNama();
        String firstUser = user.substring(0,1);
        //image drawable
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .fontSize(40) // size of text in pixels
                .bold()
                .endConfig()
                .buildRound(firstUser, getColor());
        /*TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaDosen, getColor());*/
        //https://github.com/amulyakhare/TextDrawable
        //https://github.com/amulyakhare/TextDrawable/issues/8
        holder.ivTextDrawable.setImageDrawable(drawable);

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
        @BindView(R.id.iv_text_drawable)
        ImageView ivTextDrawable;
        @BindView(R.id.txt_nip)
        TextView txtNip;
        @BindView(R.id.txt_nama)
        TextView txtNama;
        @BindView(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnFunction {
        void onSelected(NamaBawahanItem data);
    }

    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }
}
