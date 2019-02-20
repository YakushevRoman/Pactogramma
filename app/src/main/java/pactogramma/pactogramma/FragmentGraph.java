package pactogramma.pactogramma;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import pactogramma.pactogramma.DataBases.DataBaseShema;

public class FragmentGraph extends Fragment {

    private TextView rTextView;
    private GraphView rGraphView;
    private Button rButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        rTextView = view.findViewById(R.id.title_for_grapg);
        Bundle bundle = this.getArguments();

        int id = bundle.getInt(DataBaseShema.Patient.Columns.ID,0);
        String name = bundle.getString(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME,"");
        String what_pregnancy_edit_text = bundle.getString(DataBaseShema.Patient.Columns.WHAT_PREGNANCY,"");
        String which_account_birth = bundle.getString(DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH,"");
        String number_medical_history = bundle.getString(DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_,"");
        String data_and_time_hospitalization = bundle.getString(DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION,"");
        String period_duration = bundle.getString(DataBaseShema.Patient.Columns.PERIOD_DURATION,"");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID = " + id + "\n" + name + "\n" + what_pregnancy_edit_text +" \n" + which_account_birth + "\n" + number_medical_history + "\n" + data_and_time_hospitalization + "\n" + period_duration);

        rTextView.setText(stringBuilder);

        rButton = view.findViewById(R.id.add_data_css_ploda);
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "реализовать добавление данных" , Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });

        rGraphView = view.findViewById(R.id.graph_css_ploda);
        // set manual X bounds
        rGraphView.getViewport().setXAxisBoundsManual(true);
        rGraphView.getViewport().setMinX(0.0);
        rGraphView.getViewport().setMaxX(25.00);

        // set manual Y bounds
        rGraphView.getViewport().setYAxisBoundsManual(true);
        rGraphView.getViewport().setMinY(100);
        rGraphView.getViewport().setMaxY(180);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 100),
                new DataPoint(4, 145),
                new DataPoint(8, 123),
                new DataPoint(15, 112),
                new DataPoint(24, 176)
        });
        rGraphView.addSeries(series);

        return view;
    }
}
