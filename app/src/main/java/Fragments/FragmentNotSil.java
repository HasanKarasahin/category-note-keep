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
import Model.NotModel;
import data.DatabaseResult;

public class FragmentNotSil extends Fragment {

    ListView lvNotlar;
    DatabaseResult dbResult;
    ArrayList<NotModel> notModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_not_sil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbResult = new DatabaseResult(getContext());

        lvNotlar = view.findViewById(R.id.lvNotlar);
        notlariListele();
        lvNotlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openAlertDialog(position);
            }
        });
    }

    private void openAlertDialog(final int selectedPosition) {
        final CharSequence[] items = {"Sil", "İptal"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {

                    Toast.makeText(getContext(), "" + notModels.get(selectedPosition).getId(), Toast.LENGTH_SHORT).show();

                    //notSil(notModels.get(selectedPosition).getId());
                }
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void notSil(String id) {
        Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
        int result = dbResult.setNotSil(id);
        if (result != -1) {
            Toast.makeText(getContext(), "Kayıt Silindi ", Toast.LENGTH_SHORT).show();
            notlariListele();
        } else {
            Toast.makeText(getContext(), "Kayit Silinemedi", Toast.LENGTH_SHORT).show();
        }
    }

    private void notlariListele() {

        notModels = dbResult.getTumNotlar();

        if (notModels != null) {
            listviewload(notModels);
        } else {
            Toast.makeText(getActivity(), "Data Okunamadı", Toast.LENGTH_SHORT).show();
        }
    }

    private void listviewload(ArrayList<NotModel> notModels) {
        ListeAdapter adapter = new ListeAdapter(notModels, getContext());
        lvNotlar.setAdapter(adapter);
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
