package Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        dbResult = new DatabaseResult(getContext());

        lvKategoriler = view.findViewById(R.id.lvKategoriler);
        kategorileriListele();
        lvKategoriler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openAlertDialog();
            }
        });
    }

    private void kategorileriListele() {

        ArrayList<KategoriModel> kategoriModelList = dbResult.getTumKategoriler();

        if (kategoriModelList != null) {
            listviewload(kategoriModelList);
        } else {
            Toast.makeText(getActivity(), "Data Okunamadı", Toast.LENGTH_SHORT).show();
        }
    }

    private void listviewload(ArrayList<KategoriModel> KategoriModels) {
        ListeAdapter adapter = new ListeAdapter(KategoriModels, getContext());
        lvKategoriler.setAdapter(adapter);
    }

    private void openAlertDialog() {
        final CharSequence[] items = {"Sil", "İptal"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    kategoriSil();
                }
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void kategoriSil() {
        
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
