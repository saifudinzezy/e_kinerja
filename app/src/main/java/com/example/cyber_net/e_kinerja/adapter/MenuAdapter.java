package com.example.cyber_net.e_kinerja.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.activity.menu.KinerjaProd;
import com.example.cyber_net.e_kinerja.activity.menu.LapKinerjaSKP;
import com.example.cyber_net.e_kinerja.activity.menu.NamaBawahan;
import com.example.cyber_net.e_kinerja.activity.menu.PenilaianPerilaku;
import com.example.cyber_net.e_kinerja.activity.menu.SKP2;
import com.example.cyber_net.e_kinerja.model.MenuModel;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context context;
    private List<MenuModel> list;
    private OnSelect listener;


    public MenuAdapter(Context context, List<MenuModel> list) {
        this.context = context;
        this.list = list;
    }

    public MenuAdapter(Context context, List<MenuModel> list, OnSelect listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ivGambarMenu.setImageResource(list.get(position).getIconMenu());
        holder.tvNamaMenu.setText(list.get(position).getNamaMenu());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "clik " + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, SKP2.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, LapKinerjaSKP.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, KinerjaProd.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, NamaBawahan.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context, PenilaianPerilaku.class));
                        break;
                    case 5:
                        /*//context.startActivity(new Intent(context, Setting.class));
                        final CharSequence[] items = { "Edit", "Delete" };

                        new AlertDialog.Builder(context).setTitle("Student Record")
                                .setItems(items, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {
                                        if (item == 0) {
                                            Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                                        }

                                        dialog.dismiss();

                                    }
                                }).show();*/
                        listener.select();
                        break;
                    case 6:
//                        context.startActivity(new Intent(context, PemHB.class));
                        break;
                    case 7:
//                        context.startActivity(new Intent(context, CatatanLainnya.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarMenu;
        TextView tvNamaMenu;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGambarMenu = itemView.findViewById(R.id.img_menu);
            tvNamaMenu = itemView.findViewById(R.id.txt_menu);
            cv = itemView.findViewById(R.id.cv);
        }
    }

    public interface OnSelect{
        public void select();
    }
}
