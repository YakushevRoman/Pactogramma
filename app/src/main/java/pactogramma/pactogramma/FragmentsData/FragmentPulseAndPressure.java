package pactogramma.pactogramma.FragmentsData;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pactogramma.pactogramma.DataBases.ConstantsApp;
import pactogramma.pactogramma.DataBases.DataBasePatients;
import pactogramma.pactogramma.DataBases.DataBaseShema;
import pactogramma.pactogramma.R;

public class FragmentPulseAndPressure extends Fragment {
    private static final String TAG = "pactogramma";
    //
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;
    private Bundle rBundle;
    private int id;
    //
    private Button rButtonPulseAndPressure;
    private EditText rEditTextPulse;
    private EditText rEditTextPressure;
    private EditText rEditTextTimePulseAndPressure;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: FragmentPulseAndPressure");
        Bundle rBundle = getArguments();
        id = rBundle.getInt(ConstantsApp.ID);

        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
        rContentValues = new ContentValues();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pulse_and_pressure, container, false);
        Log.d(TAG, "onCreateView: FragmentPulseAndPressure");

        rEditTextPulse = view.findViewById(R.id.pulse_edit_text);
        rEditTextPressure = view.findViewById(R.id.pressure_edit_text);
        rEditTextTimePulseAndPressure = view.findViewById(R.id.pulse_and_pressure_time_edit_text);
        rButtonPulseAndPressure = view.findViewById(R.id.pulse_and_pressure_button);
        rButtonPulseAndPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rPulse  = Double.valueOf(rEditTextPulse.getText().toString());
                double rPressure = Double.valueOf(rEditTextPressure.getText().toString());
                double rTimePulseAndPressure= Double.valueOf(rEditTextTimePulseAndPressure.getText().toString());

                rContentValues.put(DataBaseShema.PulseAndPressure.Columns.ID, id);
                rContentValues.put(DataBaseShema.PulseAndPressure.Columns.PULSE, rPulse);
                rContentValues.put(DataBaseShema.PulseAndPressure.Columns.PRESSURE, rPressure);
                rContentValues.put(DataBaseShema.PulseAndPressure.Columns.TIME, rTimePulseAndPressure);

                rSqLiteDatabase.insert(DataBaseShema.PulseAndPressure.TABLE_PULSE_AND_PRESSURE, null, rContentValues);
                Log.d(TAG, "TABLE_PULSE_AND_PRESSURE  " + rPulse + " rPressure "+ rPressure + " rTimePulseAndPressure "+ rTimePulseAndPressure);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: FragmentPulseAndPressure");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: FragmentPulseAndPressure");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: FragmentPulseAndPressure");
    }

    @Override
    public void onStop() {
        super.onStop();
        rSqLiteDatabase.close();
        Log.d(TAG, "onStop: FragmentPulseAndPressure");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:FragmentPulseAndPressure");
    }
}
