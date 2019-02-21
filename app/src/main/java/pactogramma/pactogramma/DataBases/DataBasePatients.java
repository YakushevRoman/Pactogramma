package pactogramma.pactogramma.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DataBasePatients extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "patients.db";
    private static final int VERSION = 17;
    public static final String TAG = "fragmentGraph";

    //private String sqlTablePulses = "CREATE TABLE " + DataBaseShema.BabyHeartbeat.PULSES;

    private static final String sqlData = "INSERT INTO " + DataBaseShema.Patient.PATIENT + "("
            + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + ", "
            + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + ", "
            + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + ", "
            + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + ", "
            + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + ", "
            + DataBaseShema.Patient.Columns.PERIOD_DURATION
            + ") VALUES ('Valentina Ivanova', 'Ранняя беременность', '1', '19.02.2019', '10 часов', '418956') ";

    private static final String sqlData1 = "INSERT INTO " + DataBaseShema.Patient.PATIENT + "("
            + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + ", "
            + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + ", "
            + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + ", "
            + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + ", "
            + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + ", "
            + DataBaseShema.Patient.Columns.PERIOD_DURATION
            + ") VALUES ('Valentina Durova', 'Ранняя беременность', '1', '19.02.2019', '10 часов', '418955') ";

    private static final String sqlData2 = "INSERT INTO " + DataBaseShema.Patient.PATIENT + "("
            + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + ", "
            + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + ", "
            + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + ", "
            + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + ", "
            + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + ", "
            + DataBaseShema.Patient.Columns.PERIOD_DURATION
            + ") VALUES ('Valentina Yakusheva', 'Ранняя беременность', '1', '19.02.2019', '10 часов', '418954') ";

    /**
     *
     *
     */
    private String sql = "CREATE TABLE " + DataBaseShema.Patient.PATIENT + "("
            + DataBaseShema.Patient.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + " TEXT,"
            + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + " TEXT,"
            + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + " TEXT,"
            + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + " TEXT,"
            + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + " TEXT,"
            + DataBaseShema.Patient.Columns.PERIOD_DURATION + " TEXT);";

    /*private String sqlPulse = "CREATE TABLE " + DataBaseShema.BabyHeartbeat.PULSES + "("
            + DataBaseShema.BabyHeartbeat.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT + " REAL,"
            + DataBaseShema.BabyHeartbeat.Columns.TIME + " REAL);";*/


    private String sqlPulse = "CREATE TABLE " + DataBaseShema.BabyHeartbeat.PULSES + "("
            + DataBaseShema.BabyHeartbeat.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT + " REAL,"
            + DataBaseShema.BabyHeartbeat.Columns.TIME + " REAL);";
    private static final String sqlPulsedata = "INSERT INTO " + DataBaseShema.BabyHeartbeat.PULSES + "("
            + DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT + ", "
            + DataBaseShema.BabyHeartbeat.Columns.TIME
            + ") VALUES (5, 110) ";
    private static final String sqlPulsedata1 = "INSERT INTO " + DataBaseShema.BabyHeartbeat.PULSES + "("
            + DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT + ", "
            + DataBaseShema.BabyHeartbeat.Columns.TIME
            + ") VALUES (15, 170) ";

    public DataBasePatients(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* таблица с пульсом*/
        db.execSQL(sqlPulse);
        db.execSQL(sqlPulsedata);
        db.execSQL(sqlPulsedata1);
        //db.execSQL(sqlPulsedata);
        Log.d(TAG, "onCreate: pulses");

        db.execSQL(sql);
        // добавляем начальные данные
        /*
        таблица с пациентками
         */
        db.execSQL(sqlData);
        db.execSQL(sqlData1);
        db.execSQL(sqlData2);
        Log.d(TAG, "onCreate: start table" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.Patient.PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.BabyHeartbeat.PULSES);
        onCreate(db);
    }
}
