package pactogramma.pactogramma;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pactogramma.pactogramma.DataBases.DataBaseShema;

public class FragmentGraph extends Fragment {

    private TextView rTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        rTextView = view.findViewById(R.id.title_for_grapg);
        Bundle bundle = this.getArguments();

        String name = bundle.getString(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME,"");
        String what_pregnancy_edit_text = bundle.getString(DataBaseShema.Patient.Columns.WHAT_PREGNANCY,"");
        String which_account_birth = bundle.getString(DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH,"");
        String number_medical_history = bundle.getString(DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_,"");
        String data_and_time_hospitalization = bundle.getString(DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION,"");
        String period_duration = bundle.getString(DataBaseShema.Patient.Columns.PERIOD_DURATION,"");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "\n" + what_pregnancy_edit_text +" \n" + which_account_birth + "\n" + number_medical_history + "\n" + data_and_time_hospitalization + "\n" + period_duration);

        rTextView.setText(stringBuilder);
        return view;
    }
}
