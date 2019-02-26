package pactogramma.pactogramma.DataBaseContact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import pactogramma.pactogramma.DataBases.DataBaseShema;

public class DataBaseContacts extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "contact.db";
    private static final int VERSION = 1;
    public static final String TAG = "contacts";

    private String sqlPulse = "CREATE TABLE " + DataBaseShemaContacts.CONTACTSFROMFILE + "("
            + DataBaseShemaContacts.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataBaseShemaContacts.Columns.NUMBER + " TEXT);";


    private static final String sqlPulsedata1 = "INSERT INTO " + DataBaseShemaContacts.CONTACTSFROMFILE + "("
            + DataBaseShemaContacts.Columns.NUMBER
            + ") VALUES ('+380993374555') ";
    private static final String sqlPulsedata2 = "INSERT INTO " + DataBaseShemaContacts.CONTACTSFROMFILE + "("
            + DataBaseShemaContacts.Columns.NUMBER
            + ") VALUES ('+380993374554') ";
    private static final String sqlPulsedata3 = "INSERT INTO " + DataBaseShemaContacts.CONTACTSFROMFILE + "("
            + DataBaseShemaContacts.Columns.NUMBER
            + ") VALUES ('+380993374553') ";
    private static final String sqlPulsedata4 = "INSERT INTO " + DataBaseShemaContacts.CONTACTSFROMFILE + "("
            + DataBaseShemaContacts.Columns.NUMBER
            + ") VALUES ('+380993374552') ";

    public DataBaseContacts(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* таблица с пульсом*/
        db.execSQL(sqlPulse);
        db.execSQL(sqlPulsedata1);
        db.execSQL(sqlPulsedata2);
        db.execSQL(sqlPulsedata3);
        db.execSQL(sqlPulsedata4);

        Log.d(TAG, "onCreate: contacts db");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShemaContacts.CONTACTSFROMFILE);
        onCreate(db);
    }
}
