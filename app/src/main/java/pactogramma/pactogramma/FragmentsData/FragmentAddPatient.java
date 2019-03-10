package pactogramma.pactogramma.FragmentsData;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pactogramma.pactogramma.DataBases.DataBasePatients;
import pactogramma.pactogramma.DataBases.DataBaseShema;
import pactogramma.pactogramma.R;

public class FragmentAddPatient extends Fragment {
    private static final String TAG = "pactogramma";


    private EditText rEditTextName
            ,rEditTextWhatPregnancy
            ,rEditTextWhichAccountBirth
            ,rEditTextNumberMedicalHistory
            ,rEditTextDataAndTimeHospitalization
            ,rEditTextPeriodDuration ;
    private Button rButtonAddNewPatient;

    private ContentValues rContentValues;
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_patient, container, false);
        rEditTextName = view.findViewById(R.id.name);
        rEditTextWhatPregnancy = view.findViewById(R.id.what_pregnancy_edit_text);
        rEditTextWhichAccountBirth = view.findViewById(R.id.which_account_birth);
        rEditTextNumberMedicalHistory = view.findViewById(R.id.number_medical_history);
        rEditTextDataAndTimeHospitalization = view.findViewById(R.id.data_and_time_hospitalization);
        rEditTextPeriodDuration = view.findViewById(R.id.period_duration);

        rButtonAddNewPatient = view.findViewById(R.id.add_new_patients_button);
        rButtonAddNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rEditTextName.getText().toString();
                String what_pregnancy = rEditTextWhatPregnancy.getText().toString();
                String which_account_birth = rEditTextWhichAccountBirth.getText().toString();
                String number_medical_history = rEditTextNumberMedicalHistory.getText().toString();
                String data_and_time_hospitalization = rEditTextDataAndTimeHospitalization.getText().toString();
                String period_duration = rEditTextPeriodDuration.getText().toString();

                rContentValues = new ContentValues();
                rContentValues.put(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME, name);
                rContentValues.put(DataBaseShema.Patient.Columns.WHAT_PREGNANCY, what_pregnancy);
                rContentValues.put(DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH, which_account_birth);
                rContentValues.put(DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_, number_medical_history);
                rContentValues.put(DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION, data_and_time_hospitalization);
                rContentValues.put(DataBaseShema.Patient.Columns.PERIOD_DURATION, period_duration);

                rSqLiteDatabase.insert(DataBaseShema.Patient.TABLE_PATIENT, null,rContentValues);

                Snackbar.make(rButtonAddNewPatient, "Данные добавлены : ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: FragmentUrine");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: FragmentUrine");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: FragmentUrine");
    }

    @Override
    public void onStop() {
        super.onStop();
        rSqLiteDatabase.close();
        Log.d(TAG, "onStop: FragmentUrine");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:FragmentUrine");
    }
}
