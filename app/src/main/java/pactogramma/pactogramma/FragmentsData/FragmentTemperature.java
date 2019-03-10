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

import pactogramma.pactogramma.DataBases.DataBasePatients;
import pactogramma.pactogramma.DataBases.DataBaseShema;
import pactogramma.pactogramma.R;

public class FragmentTemperature extends Fragment {
    private static final String TAG = "pactogramma";
    private EditText rEditTextTemperature, rEditTextTime;
    private Button rButtonTemperature;
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private ContentValues contentValues;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle rBundle = getArguments();
        id = Objects.requireNonNull(rBundle).getInt("id");
        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
        contentValues = new ContentValues();
        Log.d(TAG, "onCreate: FragmentTemperature");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        rEditTextTemperature = view.findViewById(R.id.temperature_edit_text);
        rEditTextTime = view.findViewById(R.id.time_temperature_edit_text);
        rButtonTemperature = view.findViewById(R.id.button_temperature_data);
        rButtonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temperature = Integer.valueOf(rEditTextTemperature.getText().toString());
                int time = Integer.valueOf(rEditTextTime.getText().toString());


                contentValues.put(DataBaseShema.Temperature.Columns.ID, id);
                contentValues.put(DataBaseShema.Temperature.Columns.TEMP,temperature);
                contentValues.put(DataBaseShema.Temperature.Columns.TIME,time);
                rSqLiteDatabase.insert(DataBaseShema.Temperature.TABLE_TEMPERATURE, null, contentValues);
                Log.d(TAG, "onClick: добавлены данные FragmentTemperature"  + "id = " + id  + " pulse "  + temperature + " time " + time);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: FragmentTemperature");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: FragmentTemperature");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: FragmentTemperature");
    }

    @Override
    public void onStop() {
        super.onStop();
        rSqLiteDatabase.close();
        Log.d(TAG, "onStop: FragmentTemperature");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:FragmentTemperature");
    }
}
