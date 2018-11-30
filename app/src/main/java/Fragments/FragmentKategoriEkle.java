package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hasan.category_note_keep.R;

import java.util.ArrayList;

import Adapter.ListeAdapter;
import Model.KategoriModel;
import data.DatabaseResult;

public class FragmentKategoriEkle extends Fragment {

    DatabaseResult dbResult;
    ListView lvKategoriListesi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_kategori_ekle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnKategoriEkle = view.findViewById(R.id.btnKategoriEkle);

        dbResult = new DatabaseResult(getContext());
        lvKategoriListesi = view.findViewById(R.id.lvKategoriListesi);

        btnKategoriEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kategoriAdi = ((EditText) (view.findViewById(R.id.etKategoriAdi))).getText().toString();
                kategoriEkle(kategoriAdi);
            }
        });
        kategorileriGoster();
    }

    void kategoriEkle(String kategoriAdi) {
        dbResult.setKategoriEkle(kategoriAdi);
        kategorileriGoster();
    }

    void kategorileriGoster() {

        ArrayList<KategoriModel> kategoriModelList = dbResult.getTumKategoriler();

        if (kategoriModelList != null) {
            String tumListe = "";
            for (KategoriModel kategoriModel :
                    kategoriModelList) {

                tumListe += kategoriModel.get_id() + "-" + kategoriModel.getKategoriAdi() + "\n";
            }
            Toast.makeText(getActivity(), "-" + tumListe, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Data Okunamadı", Toast.LENGTH_SHORT).show();
        }
        listviewload(kategoriModelList);
    }


    void listviewload(ArrayList<KategoriModel> KategoriModels) {
        //ArrayAdapter<String> adapter=new ArrayAdapter<>()
        ListeAdapter adapter = new ListeAdapter(KategoriModels, getContext());
        lvKategoriListesi.setAdapter(adapter);
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
