package Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hasan.category_note_keep.R;

import java.util.ArrayList;

import Model.KategoriModel;
import data.DatabaseContract;
import data.DatabaseProvider;
import data.DatabaseResult;

public class FragmentKategoriEkle extends Fragment {

    DatabaseResult dbResult;

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

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.KategorilerEntry.COLUMN_KATEGORI, kategoriAdi);
        Uri uri = getContext().getContentResolver().insert(DatabaseContract.KategorilerEntry.CONTENT_URI, values);

        Toast.makeText(getContext(), "-->" + uri, Toast.LENGTH_SHORT).show();
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
