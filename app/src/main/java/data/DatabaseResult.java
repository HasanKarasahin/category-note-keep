package data;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import Model.KategoriModel;

public class DatabaseResult {

    private Context context;

    public DatabaseResult(Context context) {
        this.context = context;
    }

    public ArrayList<KategoriModel> getTumKategoriler() {
        //String[] projection_kategoriler = {"_id", "kategori"};
        //String selection = "_id = ?";
        //String[] selectionArgs = {"1", "2", "3"};
        try {
            Cursor cursor = context.getContentResolver().query(DatabaseContract.KategorilerEntry.CONTENT_URI, null, null, null, null);
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
