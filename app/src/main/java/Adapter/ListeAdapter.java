package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hasan.category_note_keep.R;

import java.util.ArrayList;

import Model.KategoriModel;
import Model.NotModel;

public class ListeAdapter extends BaseAdapter {

    ArrayList<?> arrayList;
    Context context;
    boolean state;
    boolean dataVar;

    public ListeAdapter(ArrayList<?> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

        if (arrayList.size() > 0) {
            if (arrayList.get(0).getClass() == KategoriModel.class) {
                state = true;
            } else if (arrayList.get(0).getClass() == NotModel.class) {
                state = false;
            }
            dataVar = true;
        } else {
            dataVar = false;
        }
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.liste_adapter_satir, parent, false);

        TextView tvElemanAdi = v.findViewById(R.id.tvElemanAdi);

        if (dataVar) {
            if (state) {
                KategoriModel kategoriModel = ((KategoriModel) arrayList.get(position));
                tvElemanAdi.setText(kategoriModel.get_id() + " - " + kategoriModel.getKategoriAdi());
            } else {
                NotModel notModel = ((NotModel) arrayList.get(position));
                tvElemanAdi.setText("id : " + notModel.getId() + " - icerik : " + notModel.getNotIcerik());
            }
        } else {
            tvElemanAdi.setText("Data Yok");
        }

        return v;
    }
}
