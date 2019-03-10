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

public class FragmentOxytocin extends Fragment {
    private static final String TAG = "pactogramma";
    //
    private Button rButtonOxytocin;
    private EditText rEditTextOxytocin;
    private EditText rEditTextTimeOxytocin;
    //
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;
    private Bundle rBundle;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: FragmentOxytocin");
        Bundle rBundle = getArguments();
        id = rBundle.getInt(ConstantsApp.ID);

        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
        rContentValues = new ContentValues();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oxytocin, container, false);
        Log.d(TAG, "onCreateView: FragmentOxytocin");
        rButtonOxytocin = view.findViewById(R.id.oxytocin_button);
        rEditTextOxytocin = view.findViewById(R.id.oxytocin_edit_text);
        rEditTextTimeOxytocin= view.findViewById(R.id.oxytocin_time_edit_text);

        rButtonOxytocin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rOxytocin  = Double.valueOf(rEditTextOxytocin.getText().toString());
                double rTime = Double.valueOf(rEditTextTimeOxytocin.getText().toString());

                rContentValues.put(DataBaseShema.Oxytocin.Columns.ID, id);
                rContentValues.put(DataBaseShema.Oxytocin.Columns.OXYTOCIN, rOxytocin);
                rContentValues.put(DataBaseShema.Oxytocin.Columns.TIME, rTime);

                rSqLiteDatabase.insert(DataBaseShema.Oxytocin.TABLE_OXYTOCIN, null, rContentValues);
                Log.d(TAG, "TABLE_OXYTOCIN " + rOxytocin + " rTime "+ rTime);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: FragmentOxytocin");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: FragmentOxytocin");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: FragmentOxytocin");
    }

    @Override
    public void onStop() {
        super.onStop();
        rSqLiteDatabase.close();
        Log.d(TAG, "onStop: FragmentOxytocin");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:FragmentOxytocin");
    }
}
