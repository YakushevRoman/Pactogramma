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

public class FragmentMedicationsReceived extends Fragment {
    private Button rButtonMedicationsReceived;
    private EditText rEditTextMedicationsReceived, rEditTextTime;
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues rContentValues;
    private Bundle rBundle;
    private int id;
    private static final String TAG = "pactogramma";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rBundle = getArguments();
        id = rBundle.getInt(ConstantsApp.ID);

        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
        rContentValues = new ContentValues();
        Log.d(TAG, "onCreate: FragmentMedicationsReceived");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medications_received, container,false);
        rEditTextMedicationsReceived = view.findViewById(R.id.medications_edit_text);
        rEditTextTime = view.findViewById(R.id.time_medications_edit_text);
        rButtonMedicationsReceived = view.findViewById(R.id.medications_button);
        rButtonMedicationsReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicationsReceived = rEditTextMedicationsReceived.getText().toString();
                int time = Integer.valueOf(rEditTextTime.getText().toString());

                rContentValues.put(DataBaseShema.MedicationsReceived.Columns.ID, id);
                rContentValues.put(DataBaseShema.MedicationsReceived.Columns.MEDICATIONS, medicationsReceived);
                rContentValues.put(DataBaseShema.MedicationsReceived.Columns.TIME, time);
                rSqLiteDatabase.insert(DataBaseShema.MedicationsReceived.TABLE_MEDICATIONS_RECEIVED, null, rContentValues);
                Log.d(TAG, "oTABLE_MEDICATIONS_RECEIVED "  + "id = " + id  + " pulse "  + medicationsReceived + " time " + time);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: FragmentMedicationsReceived");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: FragmentMedicationsReceived");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: FragmentMedicationsReceived");
    }

    @Override
    public void onStop() {
        super.onStop();
        rSqLiteDatabase.close();
        Log.d(TAG, "onStop: FragmentMedicationsReceived");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:FragmentMedicationsReceived");
    }
}
