package pactogramma.pactogramma.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DataBasePatients extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "patients.db";
    private static final int VERSION = 28;
    private static final String TAG = "pactogramma";

    private static final String sqlData = "INSERT INTO "
            + DataBaseShema.Patient.TABLE_PATIENT + "("
            + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + ", "
            + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + ", "
            + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + ", "
            + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + ", "
            + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + ", "
            + DataBaseShema.Patient.Columns.PERIOD_DURATION
            + ") VALUES ('Valentina Ivanova', 'Ранняя беременность', '1', '19.02.2019', '10 часов', '418956') ";

    public DataBasePatients(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Таблица ⦁	 Пациенты
        String sql = "CREATE TABLE "
                + DataBaseShema.Patient.TABLE_PATIENT + "("
                + DataBaseShema.Patient.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + " TEXT,"
                + DataBaseShema.Patient.Columns.WHAT_PREGNANCY + " TEXT,"
                + DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH + " TEXT,"
                + DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_ + " TEXT,"
                + DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION + " TEXT,"
                + DataBaseShema.Patient.Columns.PERIOD_DURATION + " TEXT);";
        db.execSQL(sql);
        // Таблица ⦁	 Сердцебиение плода
        String sqlPulse = "CREATE TABLE " + DataBaseShema.BabyHeartbeat.TABLE_PULSES + "("
                + DataBaseShema.BabyHeartbeat.Columns.ID + " INTEGER,"
                + DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT + " REAL,"
                + DataBaseShema.BabyHeartbeat.Columns.TIME + " REAL);";
        db.execSQL(sqlPulse);
        // Таблица • Температура
        String sqlTemperature = "CREATE TABLE " + DataBaseShema.Temperature.TABLE_TEMPERATURE + "("
                + DataBaseShema.Temperature.Columns.ID + " INTEGER,"
                + DataBaseShema.Temperature.Columns.TEMP + " REAL,"
                + DataBaseShema.Temperature.Columns.TIME + " REAL);";
        db.execSQL(sqlTemperature);
        // Таблица • Окситоцин
        String sqlOxytocin = "CREATE TABLE "
                + DataBaseShema.Oxytocin.TABLE_OXYTOCIN + "("
                + DataBaseShema.Oxytocin.Columns.ID + " INTEGER,"
                + DataBaseShema.Oxytocin.Columns.OXYTOCIN + " REAL,"
                + DataBaseShema.Oxytocin.Columns.TIME + " REAL);";
        db.execSQL(sqlOxytocin);
        // Таблица • Urine
        String sqlUrine = "CREATE TABLE "
                + DataBaseShema.Urine.TABLE_URINE + "("
                + DataBaseShema.Urine.Columns.ID + " INTEGER,"
                + DataBaseShema.Urine.Columns.PROTEIN + " REAL,"
                + DataBaseShema.Urine.Columns.ACETONE + " REAL,"
                + DataBaseShema.Urine.Columns.VOLUME + " REAL,"
                + DataBaseShema.Urine.Columns.TIME + " REAL);";
        db.execSQL(sqlUrine);
        // Таблица • Пульс и давление
        String sqlPulseAndPressure = "CREATE TABLE "
                + DataBaseShema.PulseAndPressure.TABLE_PULSE_AND_PRESSURE + "("
                + DataBaseShema.PulseAndPressure.Columns.ID + " INTEGER,"
                + DataBaseShema.PulseAndPressure.Columns.PRESSURE + " REAL,"
                + DataBaseShema.PulseAndPressure.Columns.PULSE + " REAL,"
                + DataBaseShema.PulseAndPressure.Columns.TIME + " REAL);";
        db.execSQL(sqlPulseAndPressure);
        // Таблица • Пульс и давление
        String sqlMedicationsReceived = "CREATE TABLE "
                + DataBaseShema.MedicationsReceived.TABLE_MEDICATIONS_RECEIVED + "("
                + DataBaseShema.MedicationsReceived.Columns.ID + " INTEGER,"
                + DataBaseShema.MedicationsReceived.Columns.MEDICATIONS + " TEXT,"
                + DataBaseShema.MedicationsReceived.Columns.TIME + " REAL);";
        db.execSQL(sqlMedicationsReceived);
        // Таблица • Маточные сокращения
        String sqlUterineContractions = "CREATE TABLE "
                + DataBaseShema.UterineContractions.TABLE_UTERINE_CONTRACTIONS + "("
                + DataBaseShema.UterineContractions.Columns.ID + " INTEGER,"
                + DataBaseShema.UterineContractions.Columns.UTERINE_CONTRACTIONS + " REAL,"
                + DataBaseShema.UterineContractions.Columns.TIME+ " REAL);";
        db.execSQL(sqlUterineContractions);
        //добавляем 1 пациента
        db.execSQL(sqlData);
        Log.d(TAG, "onCreate: start tables" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // в счулае если меняется база данных удаляем все таблицы из БД
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.Patient.TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.BabyHeartbeat.TABLE_PULSES);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.Temperature.TABLE_TEMPERATURE);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.Oxytocin.TABLE_OXYTOCIN);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.Urine.TABLE_URINE);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.UterineContractions.TABLE_UTERINE_CONTRACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.PulseAndPressure.TABLE_PULSE_AND_PRESSURE);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseShema.MedicationsReceived.TABLE_MEDICATIONS_RECEIVED);
        onCreate(db);
    }
}
