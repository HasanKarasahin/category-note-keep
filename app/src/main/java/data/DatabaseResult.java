package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

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
            String[] projection = {NotlarEntry.TABLE_NAME + "." + NotlarEntry.ID, NotlarEntry.TABLE_NAME + "." + NotlarEntry.COLUMN_NOT_ICERIK, KategorilerEntry.TABLE_NAME + "." + KategorilerEntry.COLUMN_KATEGORI};
            Cursor cursor = context.getContentResolver().query(NotlarEntry.CONTENT_URI, projection, null, null, null);
            notModels = new ArrayList<>();

            //int _idC = cursor.getColumnIndex(NotlarEntry.TABLE_NAME + "." + NotlarEntry.ID);
            //int notIcerikC = cursor.getColumnIndex(NotlarEntry.TABLE_NAME + "." + NotlarEntry.COLUMN_NOT_ICERIK);
            //int olusuturlmaTarihiC = cursor.getColumnIndex(NotlarEntry.COLUMN_OLUSTURULMA_TARIHI);
            //int yapildiC = cursor.getColumnIndex(NotlarEntry.COLUMN_YAPILDI);
            //int kategoriIdC = cursor.getColumnIndex(KategorilerEntry.TABLE_NAME + "." + KategorilerEntry.COLUMN_KATEGORI);
            //NotlarEntry.COLUMN_KATEGORI_ID
            //int bitisTarihiC = cursor.getColumnIndex(NotlarEntry.COLUMN_BITIS_TARIHI);

            while (cursor.moveToNext()) {
                String _id = cursor.getString(cursor.getColumnIndex(NotlarEntry.TABLE_NAME + "." + NotlarEntry.ID));
                Log.e("DerinNokta", _id);
                String notIcerik = cursor.getString(cursor.getColumnIndex(NotlarEntry.TABLE_NAME + "." + NotlarEntry.COLUMN_NOT_ICERIK));
                String kategoriId = cursor.getString(cursor.getColumnIndex(KategorilerEntry.TABLE_NAME + "." + KategorilerEntry.COLUMN_KATEGORI));

                notModels.add(new NotModel(_id, notIcerik, kategoriId, null, null, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notModels;
    }

    public int setKategoriSil(String id) {
        int etkilenen;
        String where = KategorilerEntry.ID + " = ?";
        String[] selectionArgs = {id};
        etkilenen = context.getContentResolver().delete(KategorilerEntry.CONTENT_URI, where, selectionArgs);

        return etkilenen;
    }

    public int setNotSil(String id) {
        int etkilenen;
        String where = NotlarEntry.ID + " = ?";
        String[] selectionArgs = {id};
        etkilenen = context.getContentResolver().delete(NotlarEntry.CONTENT_URI, where, selectionArgs);
        return etkilenen;
    }
}
