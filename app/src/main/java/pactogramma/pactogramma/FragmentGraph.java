package pactogramma.pactogramma;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import pactogramma.pactogramma.DataBases.DataBasePatients;
import pactogramma.pactogramma.DataBases.DataBaseShema;
import pactogramma.pactogramma.FragmentsData.FragmentMedicationsReceived;
import pactogramma.pactogramma.FragmentsData.FragmentOxytocin;
import pactogramma.pactogramma.FragmentsData.FragmentPulseAndPressure;
import pactogramma.pactogramma.FragmentsData.FragmentPulses;
import pactogramma.pactogramma.FragmentsData.FragmentTemperature;
import pactogramma.pactogramma.FragmentsData.FragmentUrine;
import pactogramma.pactogramma.FragmentsData.FragmentUterineContractions;

public class FragmentGraph extends Fragment {

    private TextView rTextView;
    private GraphView rGraphView,rGraphViewTemperature;
    private Button
            rButtonMedicationsRecaived,
            rButtonOxytocin,
            rButtonPulseAndPressure,
            rButtonPulses,
            rButtonTemperature,
            rButtonUrine,
            rButtonUterineContractions;
    //private ScrollView rScrollView;
    private String name
            ,what_pregnancy_edit_text
            ,which_account_birth
            ,number_medical_history
            ,data_and_time_hospitalization
            ,period_duration;

    private int id
            ,pulse;
    private  Bundle bundle;

    private static final String TAG = "pactogramma";

    private DataPoint rDataPoint;
    private ArrayList<DataPoint> rDataPointsArrayList;
    private ArrayList<DataPoint> rDataPointsListTemperature;

    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private Cursor cursor;
    private Cursor cursorTemperature;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getArguments();
        id = bundle.getInt(DataBaseShema.Patient.Columns.ID, 0);
        name = bundle.getString(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME, "");
        what_pregnancy_edit_text = bundle.getString(DataBaseShema.Patient.Columns.WHAT_PREGNANCY, "");
        which_account_birth = bundle.getString(DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH, "");
        number_medical_history = bundle.getString(DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_, "");
        data_and_time_hospitalization = bundle.getString(DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION, "");
        period_duration = bundle.getString(DataBaseShema.Patient.Columns.PERIOD_DURATION, "");
        Log.d(TAG, "onCreate: null  id " + id + " " + name);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        rTextView = view.findViewById(R.id.title_for_grapg);
        rButtonPulses = view.findViewById(R.id.button_pulse_baby);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(pulse)
                .append(" ID = ")
                .append(id)
                .append("\n")
                .append(name)
                .append("\n")
                .append(what_pregnancy_edit_text)
                .append(" \n")
                .append(which_account_birth)
                .append("\n")
                .append(number_medical_history)
                .append("\n")
                .append(data_and_time_hospitalization)
                .append("\n")
                .append(period_duration);

        rTextView.setText(stringBuilder);
        rButtonPulses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);

            FragmentManager rFragmentManager = getFragmentManager();
                assert rFragmentManager != null;
                Fragment rFragment = rFragmentManager.findFragmentById(R.id.fragment_container);
            if(rFragment != null){
                rFragment = new FragmentPulses();
                rFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container,rFragment)
                    .commit();
                    rFragment.setArguments(bundle);

                }
            Snackbar.make(v, "реализовать добавление данных" , Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        rDataPointsArrayList = new ArrayList<>();

        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getReadableDatabase();
        cursor = rSqLiteDatabase.query(DataBaseShema.BabyHeartbeat.TABLE_PULSES, null, "id = ?", new String[]{String.valueOf(id)},null, null, null);
        if (cursor.moveToFirst()){
            do{
                int pulse = cursor.getInt(cursor.getColumnIndex(DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT));
                int time = cursor.getInt(cursor.getColumnIndex(DataBaseShema.BabyHeartbeat.Columns.TIME));
                rDataPoint = new DataPoint(time, pulse);
                rDataPointsArrayList.add(rDataPoint);
                //Log.d(TAG, "onCreate: " + time + " " + pulse);
            }while (cursor.moveToNext());
        }

        rGraphView = view.findViewById(R.id.graph_css_ploda);
        // set manual X bounds
        rGraphView.getViewport().setXAxisBoundsManual(true);
        rGraphView.getViewport().setMinX(0.0);
        rGraphView.getViewport().setMaxX(25.00);

        // set manual Y bounds
        rGraphView.getViewport().setYAxisBoundsManual(true);
        rGraphView.getViewport().setMinY(0);
        rGraphView.getViewport().setMaxY(180);

        DataPoint[] arr = rDataPointsArrayList.toArray(new DataPoint[rDataPointsArrayList.size()]);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(arr);
        rGraphView.addSeries(series);
        /**
         * реализуем   ⦁	Температуру
         */

        rGraphViewTemperature = view.findViewById(R.id.temperature_graph_view);
        // set manual X bounds
        rGraphViewTemperature.getViewport().setXAxisBoundsManual(true);
        rGraphViewTemperature.getViewport().setMinX(0.0);
        rGraphViewTemperature.getViewport().setMaxX(25.00);

        // set manual Y bounds
        rGraphViewTemperature.getViewport().setYAxisBoundsManual(true);
        rGraphViewTemperature.getViewport().setMinY(30.0);
        rGraphViewTemperature.getViewport().setMaxY(50.0);

        rButtonTemperature = view.findViewById(R.id.button_temperature);
        rButtonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null){
                    fragment = new FragmentTemperature();
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    fragment.setArguments(bundle);
                }
            }
        });

        rDataPointsListTemperature = new ArrayList<>();
        rSqLiteDatabase = rDataBasePatients.getReadableDatabase();
        cursorTemperature = rSqLiteDatabase.query(DataBaseShema.Temperature.TABLE_TEMPERATURE, null, "id = ?", new String[]{String.valueOf(id)},null, null, null);
        if (cursorTemperature.moveToFirst()){
            do{
                int temperature = cursorTemperature.getInt(cursorTemperature.getColumnIndex(DataBaseShema.Temperature.Columns.TEMP));
                int time = cursorTemperature.getInt(cursorTemperature.getColumnIndex(DataBaseShema.Temperature.Columns.TIME));
                rDataPoint = new DataPoint(time, temperature);
                rDataPointsListTemperature.add(rDataPoint);
                //Log.d(TAG, "onCreate: " + time + " " + temperature);
            }while (cursorTemperature.moveToNext());
        }

        DataPoint[] arr1 = rDataPointsListTemperature.toArray(new DataPoint[rDataPointsListTemperature.size()]);
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(arr1);
        rGraphViewTemperature.addSeries(series1);
        /**
         *
         */
        // реализуем добавление данных по всем таблицам
        rButtonMedicationsRecaived = view.findViewById(R.id.button_medications_received);
        rButtonMedicationsRecaived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null){
                    fragment = new FragmentMedicationsReceived();
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    fragment.setArguments(bundle);
                }
            }
        });
        /**
         *
         */
        rButtonOxytocin = view.findViewById(R.id.button_oxytocin);
        rButtonOxytocin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null){
                    fragment = new FragmentOxytocin();
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    fragment.setArguments(bundle);
                }
            }
        });
        /**
         *
         */
        rButtonPulseAndPressure = view.findViewById(R.id.button_pulse_and_pressure);
        rButtonPulseAndPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null){
                    fragment = new FragmentPulseAndPressure();
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    fragment.setArguments(bundle);
                }
            }
        });
        /**
         *
         */
        rButtonUrine = view.findViewById(R.id.button_urine);
        rButtonUrine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null){
                    fragment = new FragmentUrine();
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    fragment.setArguments(bundle);
                }
            }
        });
        /**
         *
         */
        rButtonUterineContractions = view.findViewById(R.id.button_uterine_contractions);
        rButtonUterineContractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null){
                    fragment = new FragmentUterineContractions();
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    fragment.setArguments(bundle);
                }
            }
        });
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        rDataBasePatients.close();
        cursor.close();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
