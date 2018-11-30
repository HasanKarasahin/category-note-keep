package Fragments;

import android.content.Context;
import android.net.Uri;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hasan.category_note_keep.R;

import java.util.ArrayList;

import Model.KategoriModel;
import data.DatabaseResult;

public class FragmentNotEkle extends Fragment {

    DatabaseResult dbResult;
    Spinner spKategoriler;
    ArrayList<KategoriModel> kategoriModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_not_ekle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbResult = new DatabaseResult(getContext());

        spKategoriler = view.findViewById(R.id.spKategoriler);
        spinnerDoldur();
        Button btn = view.findViewById(R.id.btnNotEkle);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kategoriId = ((KategoriModel) spKategoriler.getSelectedItem()).get_id();
                String notIcerik = ((EditText) (view.findViewById(R.id.etNotIcerik))).getText().toString();
                notEkle(notIcerik, kategoriId);
            }
        });
    }


    private void notEkle(String notIcerik, String kategoriId) {
        Uri uri = dbResult.setNotEkle(notIcerik, kategoriId);
        if (uri != null) {

            Toast.makeText(getContext(), "Not Basarıyla Eklendi", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Not Eklenemedi", Toast.LENGTH_SHORT).show();
        }
    }

    private void spinnerDoldur() {
        kategoriModels = dbResult.getTumKategoriler();
        ArrayAdapter<KategoriModel> adapter = new ArrayAdapter<KategoriModel>(getContext(), R.layout.support_simple_spinner_dropdown_item, kategoriModels);
        spKategoriler.setAdapter(adapter);
        spKategoriler.setSelection(0);
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
