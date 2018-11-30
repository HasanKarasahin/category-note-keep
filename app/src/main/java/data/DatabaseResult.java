package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

import Model.KategoriModel;

import data.DatabaseContract.NotlarEntry;

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

    public Uri setKategoriEkle(String kategoriAdi) {

        Uri uri = null;
        try {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.KategorilerEntry.COLUMN_KATEGORI, kategoriAdi);
            uri = context.getContentResolver().insert(DatabaseContract.KategorilerEntry.CONTENT_URI, values);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return uri;
    }

    public Uri setNotEkle(String notIcerik, String kategoriId) {

        Uri uri = null;
        try {
            ContentValues values = new ContentValues();
            values.put(NotlarEntry.COLUMN_NOT_ICERIK, notIcerik);
            values.put(NotlarEntry.COLUMN_OLUSTURULMA_TARIHI, "28-11-18");
            values.put(NotlarEntry.COLUMN_YAPILDI, 0);
            values.put(NotlarEntry.COLUMN_KATEGORI_ID, kategoriId);

            uri = context.getContentResolver().insert(NotlarEntry.CONTENT_URI, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uri;
    }
}
