package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

import Model.KategoriModel;
import Model.NotModel;

import data.DatabaseContract.NotlarEntry;
import data.DatabaseContract.KategorilerEntry;

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
            Cursor cursor = context.getContentResolver().query(KategorilerEntry.CONTENT_URI, null, null, null, null);
            ArrayList<KategoriModel> kategoriModels = new ArrayList<>();

            int _idC = cursor.getColumnIndex(KategorilerEntry.ID);
            int kategoriAdiC = cursor.getColumnIndex(KategorilerEntry.COLUMN_KATEGORI);

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

    public ArrayList<NotModel> getTumNotlar() {

        ArrayList<NotModel> notModels = null;

        try {
            Cursor cursor = context.getContentResolver().query(NotlarEntry.CONTENT_URI, null, null, null, null);
            notModels = new ArrayList<NotModel>();

            int _idC = cursor.getColumnIndex(NotlarEntry.ID);
            int notIcerikC = cursor.getColumnIndex(NotlarEntry.COLUMN_NOT_ICERIK);
            int olusuturlmaTarihiC = cursor.getColumnIndex(NotlarEntry.COLUMN_OLUSTURULMA_TARIHI);
            int yapildiC = cursor.getColumnIndex(NotlarEntry.COLUMN_YAPILDI);
            int kategoriIdC = cursor.getColumnIndex(KategorilerEntry.COLUMN_KATEGORI);
            //NotlarEntry.COLUMN_KATEGORI_ID
            int bitisTarihiC = cursor.getColumnIndex(NotlarEntry.COLUMN_BITIS_TARIHI);

            while (cursor.moveToNext()) {
                String _id = cursor.getString(_idC);
                String notIcerik = cursor.getString(notIcerikC);
                String kategoriId = cursor.getString(kategoriIdC);

                notModels.add(new NotModel(_id, notIcerik, kategoriId, null, null, null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notModels;
    }
}
