package pactogramma.pactogramma.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataBasePatients extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "patients.db";
    private static final int VERSION = 1;

    public DataBasePatients(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DataBaseShema.Patient.PATIENT + "("
                + DataBaseShema.Patient.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + " TEXT,"
                + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + " TEXT,"
                + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + " TEXT,"
                + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + " TEXT,"
                + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + " TEXT,"
                + DataBaseShema.Patient.Columns.PERIOD_DURATION + " TEXT);";
        db.execSQL(sql);
        // добавляем начальные данные
        db.execSQL("INSERT INTO " + DataBaseShema.Patient.PATIENT + "("
                + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + ", "
                + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + ", "
                + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + ", "
                + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + ", "
                + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + ", "
                + DataBaseShema.Patient.Columns.PERIOD_DURATION + ") VALUES ('Valentina Durova', 'Ранняя беременность', '1', '19.02.2019', '10 часов', '418957') ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.Patient.PATIENT);
        onCreate(db);
    }
}
