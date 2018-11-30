package data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import data.DatabaseContract.NotlarEntry;
import data.DatabaseContract.KategorilerEntry;

public class DatabaseProvider extends ContentProvider {

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int URICODE_NOTLAR = 1;
    private static final int URICODE_KATEGORILER = 2;

    static {
        matcher.addURI(DatabaseContract.CONTENT_AUTHORITY, NotlarEntry.TABLE_NAME, URICODE_NOTLAR);
        matcher.addURI(DatabaseContract.CONTENT_AUTHORITY, KategorilerEntry.TABLE_NAME, URICODE_KATEGORILER);
    }


    private SQLiteDatabase db;
    private DatabaseHelper helper;

    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());
        db = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = null;
        SQLiteQueryBuilder builder;
        String tabloBirlestir = NotlarEntry.TABLE_NAME + " inner join "
                + KategorilerEntry.TABLE_NAME
                + " on " + NotlarEntry.TABLE_NAME
                + "." + NotlarEntry.COLUMN_KATEGORI_ID
                + " = " + KategorilerEntry.TABLE_NAME + "." + KategorilerEntry.ID;

        switch (matcher.match(uri)) {
            case URICODE_NOTLAR:
                builder = new SQLiteQueryBuilder();
                builder.setTables(tabloBirlestir);
                cursor = builder.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case URICODE_KATEGORILER:
                //String[] projection_kategoriler = {"_id", "kategori"};
                //String selection = "_id = ?";
                //String[] selectionArgs = {"1", "2", "3"};

                cursor = db.query(KategorilerEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Bilinmeyen QUERY URI" + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (matcher.match(uri)) {
            case URICODE_NOTLAR:
                return kayitEkle(uri, values, NotlarEntry.TABLE_NAME);
            case URICODE_KATEGORILER:
                return kayitEkle(uri, values, KategorilerEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Bilinmeyen INSERT URI" + uri);
        }
    }

    private Uri kayitEkle(Uri uri, ContentValues values, String tableName) {
        long id = db.insert(tableName, null, values);
        if (id == -1) {
            Log.e("DatabaseExp", "Insert Hatasi");
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (matcher.match(uri)) {
            case URICODE_NOTLAR:
                return kayitSil(uri, selection, selectionArgs, NotlarEntry.TABLE_NAME);
            case URICODE_KATEGORILER:
                return kayitSil(uri, selection, selectionArgs, KategorilerEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Bilinmeyen DELETE URI" + uri);
        }
    }

    private int kayitSil(Uri uri, String selection, String[] selectionArgs, String tableName) {
        int etkilenen = db.delete(tableName, selection, selectionArgs);
        if (etkilenen == 0) {
            Log.e("DatabaseExp", "Delete Hatası");
            return -1;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return etkilenen;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (matcher.match(uri)) {
            case URICODE_NOTLAR:
                return kayitGuncelle(uri, values, selection, selectionArgs, NotlarEntry.TABLE_NAME);
            case URICODE_KATEGORILER:
                return kayitGuncelle(uri, values, selection, selectionArgs, NotlarEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Bilinmeyen UPDATE URI" + uri);
        }
    }

    private int kayitGuncelle(Uri uri, ContentValues values, String selection, String[] selectionArgs, String tableName) {
        int etkilenen = db.update(tableName, values, selection, selectionArgs);
        if (etkilenen == 0) {
            Log.e("DatabaseExp", "UPDATE Hatası");
            return -1;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return etkilenen;
    }
}
