package data;

import android.database.Cursor;

import java.util.ArrayList;

import Model.KategoriModel;

public class DatabaseResult {


    public ArrayList<KategoriModel> tumKategoriler(Cursor cursor) {

        try {

            ArrayList<KategoriModel> kategoriModels = new ArrayList<>();

            int _idC = cursor.getColumnIndex(DatabaseContract.KategorilerEntry.ID);
            int kategoriAdiC = cursor.getColumnIndex(DatabaseContract.KategorilerEntry.COLUMN_KATEGORI);

            while (cursor.moveToNext()) {

                String _id = cursor.getString(_idC);
                String kategoriAdi = cursor.getString(kategoriAdiC);

                kategoriModels.add(new KategoriModel(_id, kategoriAdi));
            }


            return kategoriModels;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
