package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_KATEGORILER_CREATE = "";
    private static final String TABLE_NOTLAR_CREATE = "";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_KATEGORILER_CREATE);
        db.execSQL(TABLE_NOTLAR_CREATE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("");
        db.execSQL("");
        onCreate(db);
    }
}
