package com.example.cyber_net.e_kinerja.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cyber_net.e_kinerja.R;
import com.example.cyber_net.e_kinerja.model.item.KuantitasItem;
import com.example.cyber_net.e_kinerja.model.item.SkpTahunanItem;

import java.util.List;

public class SKPTahunanAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;

    List<SkpTahunanItem> data;

    public SKPTahunanAdapter(Context context, List<SkpTahunanItem> data) {
        this.context = context;
        this.data = data;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.kuantitas_spinner_items, null);
        TextView names = view.findViewById(R.id.textView);

        names.setText(data.get(i).getKegiatan());
        return view;
    }
}
