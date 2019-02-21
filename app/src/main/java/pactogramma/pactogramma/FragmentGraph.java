package pactogramma.pactogramma;

import android.content.Context;
import android.content.SharedPreferences;
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
import pactogramma.pactogramma.DataBases.FragmentPulses;

public class FragmentGraph extends Fragment {

    private TextView rTextView;
    private GraphView rGraphView;
    private Button rButton;
    private String name
            ,what_pregnancy_edit_text
            ,which_account_birth
            ,number_medical_history
            ,data_and_time_hospitalization
            ,period_duration;

    private int id
            ,pulse;
    private  Bundle bundle;

    private SharedPreferences sharedPreferences;

    public static final String TAG = "fragmentGraph";

    private DataPoint rDataPoint;
    private ArrayList<DataPoint> rDataPointsArrayList;


    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                rDataPointsArrayList = new ArrayList<>();
                bundle = getArguments();
                id = bundle.getInt(DataBaseShema.Patient.Columns.ID, 0);

                name = bundle.getString(DataBaseShema
                        .Patient
                        .Columns
                        .FIRSTNAME_LASTNAME, "");
                what_pregnancy_edit_text = bundle.getString(DataBaseShema
                        .Patient
                        .Columns
                        .WHAT_PREGNANCY, "");
                which_account_birth = bundle.getString(DataBaseShema
                        .Patient
                        .Columns
                        .WHICH_ACCOUNT_BIRTH, "");
                number_medical_history = bundle.getString(DataBaseShema
                        .Patient
                        .Columns
                        .NUMBER_MEDICAL_HISTORY_, "");
                data_and_time_hospitalization = bundle.getString(DataBaseShema
                        .Patient
                        .Columns
                        .DATA_AND_TIME_HOSPITALIZATION, "");
                period_duration = bundle
                        .getString(DataBaseShema
                                .Patient
                                .Columns
                                .PERIOD_DURATION, "");


                Log.d(TAG, "onCreate: null");

                rDataBasePatients = new DataBasePatients(getContext());
                rSqLiteDatabase = rDataBasePatients.getReadableDatabase();
                Cursor cursor = rSqLiteDatabase.query(DataBaseShema.BabyHeartbeat.PULSES, null, null, null,null, null, null);
                if (cursor.moveToFirst()){
                    do{
                        int x = cursor.getInt(cursor.getColumnIndex(DataBaseShema.BabyHeartbeat.Columns.HEARTBEAT));
                        int y = cursor.getInt(cursor.getColumnIndex(DataBaseShema.BabyHeartbeat.Columns.TIME));
                        rDataPoint = new DataPoint(x, y);
                        rDataPointsArrayList.add(rDataPoint);
                        Log.d(TAG, "onCreate: " + x + " " + y);
                    }while (cursor.moveToNext());
                }
        //Log.d(TAG, "onCreate: " + rDataPointsArrayList.toString());
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
        View view = inflater
                .inflate(R.layout.fragment_graph, container, false);
        rTextView = view
                .findViewById(R.id.title_for_grapg);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pulse+ " ID = " + id
                + "\n" + name
                + "\n" + what_pregnancy_edit_text
                +" \n" + which_account_birth
                + "\n" + number_medical_history
                + "\n" + data_and_time_hospitalization
                + "\n" + period_duration);

        rTextView.setText(stringBuilder);

        rButton = view.findViewById(R.id.add_data_css_ploda);
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager rFragmentManager = getFragmentManager();
                Fragment rFragment = rFragmentManager.findFragmentById(R.id.fragment_container);
                if(rFragment != null){
                    rFragment = new FragmentPulses();
                    rFragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container,rFragment)
                            .commit();
                }

                Snackbar.make(v, "реализовать добавление данных" , Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });

        rGraphView = view.findViewById(R.id.graph_css_ploda);
        // set manual X bounds
        rGraphView.getViewport()
                .setXAxisBoundsManual(true);
        rGraphView.getViewport()
                .setMinX(0.0);
        rGraphView.getViewport()
                .setMaxX(25.00);

        // set manual Y bounds
        rGraphView.getViewport()
                .setYAxisBoundsManual(true);
        rGraphView.getViewport()
                .setMinY(100);
        rGraphView.getViewport()
                .setMaxY(180);


        DataPoint[] arr = rDataPointsArrayList.toArray(new DataPoint[rDataPointsArrayList.size()]);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(arr);
        rGraphView.addSeries(series);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        /*sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString("name", name);
        ed.commit();*/

        Log.d(TAG, "onStop: " + name);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /*sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        outState.putString("name",sharedPreferences.getString("name", ""));*/
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
