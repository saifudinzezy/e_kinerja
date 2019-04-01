package com.example.cyber_net.e_kinerja.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.ResponseDelete;
import com.example.cyber_net.e_kinerja.model.item.SkpTahunanItem;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.e_kinerja.helper.Contans.TABEL_ID_SKP;
import static com.example.cyber_net.e_kinerja.helper.Contans.TABEL_SKP_TAHUNAN;
import static com.example.cyber_net.e_kinerja.helper.ConvertDate.ubahTanggal2;

public class SKPAdapter extends RecyclerSwipeAdapter<SKPAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<SkpTahunanItem> list;
    private boolean cek;
    SKPAdapterListener listener;

    public SKPAdapter(Context mContext, ArrayList<SkpTahunanItem> list, SKPAdapterListener listener, boolean cek) {
        this.mContext = mContext;
        this.list = list;
        this.cek = cek;
        this.listener = listener;
    }

    public SKPAdapter(Context context, ArrayList<SkpTahunanItem> objects, SKPAdapterListener listener) {
        this.mContext = context;
        this.list = objects;
        this.listener = listener;
    }

    public SKPAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public SKPAdapter(Context context, ArrayList<SkpTahunanItem> objects) {
        this.mContext = context;
        this.list = objects;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_skp, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        viewHolder.kegiatan.setText(list.get(position).getKegiatan());
        viewHolder.target.setText(list.get(position).getTargetKuantitas());
        viewHolder.kuantitas.setText(list.get(position).getKuantitas());
        viewHolder.selesai.setText(list.get(position).getTargetSelesai());
        viewHolder.txtTanggal.setText(ubahTanggal2(list.get(position).getTahun()));

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kanan ke kriri
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));

        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        //setenable spy tdk bisa di klik
        if (!cek){

            viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert();
                }
            });

            viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert();
                }
            });
        }else {
            viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSelected(list.get(position));
                }
            });

            viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), "Clicked on hapus  ", Toast.LENGTH_SHORT).show();

                    //buat object alarm
                    AlertDialog.Builder aleBuilder = new AlertDialog.Builder(mContext);
                    //settting judul dan pesan
                    aleBuilder.setTitle("Hapus Data");
                    aleBuilder
                            .setMessage("Apakah anda yakin ingin menghapus data ini?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //panggil metod yang di butuhkan
                                    //ini yang bakal kita deklarasikan sendiri jika ada yang panggil ini
                                    hapusData(list.get(position).getIdSkp(), TABEL_SKP_TAHUNAN, TABEL_ID_SKP);
                                    mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                                    list.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, list.size());
                                    mItemManger.closeAllItems();
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
            });
        }

        //untuk membuka swipe hanya satu
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Delete)
        ImageButton Delete;
        @BindView(R.id.Edit)
        ImageButton Edit;
        @BindView(R.id.bottom_wraper)
        LinearLayout bottomWraper;
        @BindView(R.id.kegiatan)
        TextView kegiatan;
        @BindView(R.id.target)
        TextView target;
        @BindView(R.id.kuantitas)
        TextView kuantitas;
        @BindView(R.id.selesai)
        TextView selesai;
        @BindView(R.id.swipe)
        SwipeLayout swipeLayout;
        @BindView(R.id.txt_tanggal)
        TextView txtTanggal;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelected(list.get(getAdapterPosition()));
                }
            });*/
        }
    }

    public void hapusData(final String id, final String tabel, final String cari) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseDelete> call = apiService.delete(tabel, cari, id);
        call.enqueue(new Callback<ResponseDelete>() {
            @Override
            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                try {
                    if (response.body().getKode() == 1) {
                        Toast.makeText(mContext, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDelete> call, Throwable t) {

            }
        });
    }

    public interface SKPAdapterListener {
        void onSelected(SkpTahunanItem data);
    }

    private void alert(){
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(mContext);
        //settting judul dan pesan
        aleBuilder.setTitle("Informasi");
        aleBuilder
                .setMessage("Data tidak bisa dihapus/diedit karena sudah melampaui batas waktu")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }
}