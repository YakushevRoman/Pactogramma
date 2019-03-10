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

import pactogramma.pactogramma.DataBases.DataBasePatients;
import pactogramma.pactogramma.DataBases.DataBaseShema;
import pactogramma.pactogramma.FragmentGraph;
import pactogramma.pactogramma.R;

public class FragmentPulses extends Fragment {

    private EditText rEditTextPulses, rEditTextTimePulse;
    private Button rButtonPulses;
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private Bundle bundle;
    int id;

    private static final String TAG = "pactogramma";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getArguments();
        id = bundle.getInt(DataBaseShema.Patient.Columns.ID, 0);

        Log.d(TAG, "onCreate: 2 " + String.valueOf(id));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: 2");
        View view = inflater.inflate(R.layout.fragment_pulses, container, false);
        rEditTextPulses = view.findViewById(R.id.pulses_edit_text);
        rEditTextTimePulse = view.findViewById(R.id.time_pulses_edit_text);
        rButtonPulses = view.findViewById(R.id.add_pusles_button);
        rButtonPulses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rDataBasePatients = new DataBasePatients(getContext());
                rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
                ContentValues rContentValues = new ContentValues();
                int pulse = Integer.valueOf(rEditTextPulses.getText().toString());
                int time = Integer.valueOf(rEditTextTimePulse.getText().toString());


                rContentValues.put(DataBaseShema.BabyHeartbeat.Columns.ID, id);
                rContentValues.put(DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT, pulse);
                rContentValues.put(DataBaseShema.BabyHeartbeat.Columns.TIME, time);

                rSqLiteDatabase.insert(DataBaseShema.BabyHeartbeat.TABLE_PULSES, null,rContentValues);
                Log.d(TAG, "onClick: добавлены данные "  + "id = " + 1  + " pulse "  + pulse + " time " + time);
                getActivity().getSupportFragmentManager().popBackStack();

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
