package com.example.cyber_net.e_kinerja.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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
import com.example.cyber_net.e_kinerja.model.item.LapKinerjaSkpItem;
import com.example.cyber_net.e_kinerja.model.ResponseDelete;
import com.example.cyber_net.e_kinerja.network.ApiService;
import com.example.cyber_net.e_kinerja.network.RetroClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cyber_net.e_kinerja.helper.Contans.TABEL_ID_LAP_SKP_TAHUNAN;
import static com.example.cyber_net.e_kinerja.helper.Contans.TABEL_LAP_SKP_TAHUNAN;
import static com.example.cyber_net.e_kinerja.helper.ConvertDate.ubahTanggal2;

public class LapKinerjaSKPAdapter extends RecyclerSwipeAdapter<LapKinerjaSKPAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<LapKinerjaSkpItem> list;
    SKPAdapterListener listener;


    public LapKinerjaSKPAdapter(Context context, ArrayList<LapKinerjaSkpItem> objects, SKPAdapterListener listener) {
        this.mContext = context;
        this.list = objects;
        this.listener = listener;
    }

    public LapKinerjaSKPAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public LapKinerjaSKPAdapter(Context context, ArrayList<LapKinerjaSkpItem> objects) {
        this.mContext = context;
        this.list = objects;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lap_kinerja_skp, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        viewHolder.kegiatan.setText(list.get(position).getKegiatanSkp());
        viewHolder.jumlah.setText(list.get(position).getKuantitasSkp());
        viewHolder.kuantitas.setText(list.get(position).getKuantitasKuantitas());
        viewHolder.skpTahunan.setText(list.get(position).getSkpTahunanKegiatan());
        viewHolder.kesesuaian.setText(list.get(position).getStatusSkp());
        viewHolder.txtTanggal.setText(ubahTanggal2(list.get(position).getTanggalSkp()));
        if (viewHolder.kesesuaian.getText().toString().equalsIgnoreCase("Y")) {
            //green
            viewHolder.background.setBackgroundColor(Color.parseColor("#00796B"));
            viewHolder.kesesuaian.setText("Di setujui");
        }else  if (viewHolder.kesesuaian.getText().toString().equalsIgnoreCase("M")) {
            //green
            viewHolder.background.setBackgroundColor(Color.parseColor("#ffb300"));
            viewHolder.kesesuaian.setText("Menunggu");
        }else {
            //red
            viewHolder.background.setBackgroundColor(Color.parseColor("#E43F3F"));
            viewHolder.kesesuaian.setText("Tidak di Setujui");
        }

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
                                hapusData(list.get(position).getIdKuantitas(), TABEL_LAP_SKP_TAHUNAN, TABEL_ID_LAP_SKP_TAHUNAN);
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
        @BindView(R.id.txt_tanggal)
        TextView txtTanggal;
        @BindView(R.id.kegiatan)
        TextView kegiatan;
        @BindView(R.id.jumlah)
        TextView jumlah;
        @BindView(R.id.kuantitas)
        TextView kuantitas;
        @BindView(R.id.skp_tahunan)
        TextView skpTahunan;
        @BindView(R.id.kesesuaian)
        TextView kesesuaian;
        @BindView(R.id.background)
        LinearLayout background;
        @BindView(R.id.swipe)
        SwipeLayout swipeLayout;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

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
        void onSelected(LapKinerjaSkpItem data);
    }
}