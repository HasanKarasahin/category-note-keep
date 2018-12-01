package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hasan.category_note_keep.R;

import java.util.ArrayList;

import Adapter.ListeAdapter;
import Model.KategoriModel;
import data.DatabaseResult;

public class FragmentKategoriSil extends Fragment {

    ListView lvKategoriler;
    DatabaseResult dbResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_kategori_sil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbResult=new DatabaseResult(getContext());

        lvKategoriler = view.findViewById(R.id.lvKategoriler);

        kategorileriListele();
    }

    void kategorileriListele() {

        ArrayList<KategoriModel> kategoriModelList = dbResult.getTumKategoriler();

        if (kategoriModelList != null) {
            listviewload(kategoriModelList);
        } else {
            Toast.makeText(getActivity(), "Data Okunamadı", Toast.LENGTH_SHORT).show();
        }
    }

    void listviewload(ArrayList<KategoriModel> KategoriModels) {
        ListeAdapter adapter = new ListeAdapter(KategoriModels, getContext());
        lvKategoriler.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
