package Data;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String CONTENT_AUTHORITY = "";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //TABLOLARA AIT SABITLER BASLANGIC

    public static final class NotlarEntry implements BaseColumns {

        public static final String TABLE_NAME = "notlar";

        //TABLOYA AIT URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);
        //content://com.example.hasan.notdefteriapp.notdefterimprovider/notlar

        //COLUMNS
        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_NOT_ICERIK = "notIcerik";
        public static final String COLUMN_OLUSTURULMA_TARIHI = "olusuturulmaTarihi";
        public static final String COLUMN_BITIS_TARIHI = "bitisTarihi";
        public static final String COLUMN_YAPILDI = "yapildi";
        public static final String COLUMN_KATEGORI_ID = "kategoriID";
    }

    public static final class KategorilerEntry implements BaseColumns {
        public static final String TABLE_NAME = "kategori";

        //TABLOYA AIT URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);
        //content://com.example.hasan.notdefteriapp.notdefterimprovider/kategoriler

        //COLUMNS
        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_KATEGORI = "kategori";

    }

    //TABLOLARA AIT SABITLER BITIS

}
