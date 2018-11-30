package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import data.DatabaseContract.NotlarEntry;
import data.DatabaseContract.KategorilerEntry;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    //CREATE TABLE KATEGORILER(_ID INTEGER PRIMARY KEY, KATEGORILER TEXT);

    private static final String TABLE_KATEGORILER_CREATE = "CREATE TABLE " + KategorilerEntry.TABLE_NAME +
            " (" + KategorilerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KategorilerEntry.COLUMN_KATEGORI + " TEXT )";

    //CREATE TABLE NOTLAR (_ID INTEGER PRIMARY KEY,
    //                    NOT_ICERIK TEXT,
    //                    OLUSTURMA_TARIHI TEXT default CURRENT_TIMESTAMP,
    //                    BITIS_TARIHI TEXT
    //                    KATEGORI_ID INTEGER
    //                    FOREIGN KEY(KATEGORI_ID) REFERENCES KATEGORILER(_ID);

    private static final String TABLE_NOTLAR_CREATE = "CREATE TABLE " + NotlarEntry.TABLE_NAME + " (" + NotlarEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NotlarEntry.COLUMN_NOT_ICERIK + " TEXT, " +
            NotlarEntry.COLUMN_OLUSTURULMA_TARIHI + " TEXT default CURRENT_TIMESTAMP, " +
            NotlarEntry.COLUMN_BITIS_TARIHI + " TEXT, " +
            NotlarEntry.COLUMN_YAPILDI + " INTEGER, " +
            NotlarEntry.COLUMN_KATEGORI_ID + " INTEGER, " +
            "FOREIGN KEY(" + NotlarEntry.COLUMN_KATEGORI_ID + ") REFERENCES " + KategorilerEntry.TABLE_NAME + "(" + KategorilerEntry._ID + ") )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABLOLARIMIZ BURADA OLUSTURULUYOR.
        db.execSQL(TABLE_KATEGORILER_CREATE);
        db.execSQL(TABLE_NOTLAR_CREATE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        //FOREIGN KEYLERIN DÜZGÜN CALISMASI ICIN GEREKLI CONFIG AYARLANMASI
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //BURAYA YEDEK ALMA ISLEMLERI EKLENECEKTIR.
        db.execSQL("DROP TABLE IF EXISTS " + KategorilerEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NotlarEntry.TABLE_NAME);
        onCreate(db);
    }
}
