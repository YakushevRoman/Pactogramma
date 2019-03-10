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

public class FragmentUrine extends Fragment {
    private static final String TAG = "pactogramma";
    private Button rButtonUrine;
    private EditText rEditTextUrineProtein;
    private EditText rEditTextUrineAcetone;
    private EditText rEditTextUrineVolume;
    private EditText rEditTextUrineTime;
    //
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;
    private Bundle rBundle;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: FragmentUrine");
        Bundle rBundle = getArguments();
        id = rBundle.getInt(ConstantsApp.ID);

        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
        rContentValues = new ContentValues();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_urine, container, false);
        Log.d(TAG, "onCreateView: FragmentUrine");
        rButtonUrine = view.findViewById(R.id.urine_button);
        rEditTextUrineProtein = view.findViewById(R.id.urine_protein_edit_text);
        rEditTextUrineAcetone = view.findViewById(R.id.urine_acetone_edit_text);
        rEditTextUrineVolume = view.findViewById(R.id.urine_volume_edit_text);
        rEditTextUrineTime= view.findViewById(R.id.urine_time_edit_text);

        rButtonUrine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rProtein = Double.valueOf(rEditTextUrineProtein.getText().toString());
                double rAcetone = Double.valueOf(rEditTextUrineAcetone.getText().toString());
                double rVolume = Double.valueOf(rEditTextUrineVolume.getText().toString());
                double rTime = Double.valueOf(rEditTextUrineTime.getText().toString());

                rContentValues.put(DataBaseShema.Urine.Columns.ID, id);
                rContentValues.put(DataBaseShema.Urine.Columns.PROTEIN, rProtein);
                rContentValues.put(DataBaseShema.Urine.Columns.ACETONE, rAcetone);
                rContentValues.put(DataBaseShema.Urine.Columns.VOLUME, rVolume);
                rContentValues.put(DataBaseShema.Urine.Columns.TIME, rTime);

                rSqLiteDatabase.insert(DataBaseShema.Urine.TABLE_URINE, null, rContentValues);
                Log.d(TAG, "onCreateView: FragmentUrine rProtein "+ id + " " + rProtein + " rAcetone "+ rAcetone + " rVolume "+ rVolume + " rTime "+ rTime );
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
