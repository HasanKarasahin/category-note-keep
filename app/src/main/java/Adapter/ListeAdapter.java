package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hasan.category_note_keep.R;

import java.util.ArrayList;

import Model.KategoriModel;

public class ListeAdapter extends BaseAdapter {

    ArrayList<KategoriModel> kategoriModels;
    Context context;

    public ListeAdapter(ArrayList<KategoriModel> kategoriModels, Context context) {
        this.kategoriModels = kategoriModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return kategoriModels.size();
    }

    @Override
    public Object getItem(int position) {
        return kategoriModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.liste_adapter_satir, parent, false);

        TextView tvElemanAdi = v.findViewById(R.id.tvElemanAdi);
        tvElemanAdi.setText(kategoriModels.get(position).getKategoriAdi());

        return v;
    }
}
