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

import java.util.Objects;

import pactogramma.pactogramma.DataBases.ConstantsApp;
import pactogramma.pactogramma.DataBases.DataBasePatients;
import pactogramma.pactogramma.DataBases.DataBaseShema;
import pactogramma.pactogramma.R;

public class FragmentUterineContractions extends Fragment {
    private static final String TAG = "pactogramma";
    private EditText rEditTextUterineContractions,rEditTextTime;
    private int id;
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle rBundle = getArguments();
        assert rBundle != null;
        id = rBundle.getInt(ConstantsApp.ID);
        
        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
        rContentValues = new ContentValues();
        Log.d(TAG, "onCreate: FragmentUterineContractions");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uterine_contractions, container, false);
        rEditTextUterineContractions = view.findViewById(R.id.uterine_contractions_edit_text);
        rEditTextTime = view.findViewById(R.id.uterine_contractions_time_edit_text);
        Button rButtonUterineContractions = view.findViewById(R.id.uterine_contractions_button);
        rButtonUterineContractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uterine_contractions = Integer.valueOf(rEditTextUterineContractions.getText().toString());
                int time = Integer.valueOf(rEditTextTime.getText().toString());

                rContentValues.put(DataBaseShema.UterineContractions.Columns.ID, id);
                rContentValues.put(DataBaseShema.UterineContractions.Columns.UTERINE_CONTRACTIONS, uterine_contractions);
                rContentValues.put(DataBaseShema.UterineContractions.Columns.TIME, time);

                rSqLiteDatabase.insert(DataBaseShema.BabyHeartbeat.TABLE_PULSES, null,rContentValues);
                Log.d(TAG, "onClick: добавлены данные FragmentUterineContractions "  + "id = " + id  + " uterine_contractions "  + uterine_contractions + " time " + time);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: FragmentUterineContractions");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: FragmentUterineContractions");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: FragmentUterineContractions");
    }
    
    @Override
    public void onStop() {
        super.onStop();
        rDataBasePatients.close();
        Log.d(TAG, "onStop: FragmentUterineContractions");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:FragmentUterineContractions");
    }
}
