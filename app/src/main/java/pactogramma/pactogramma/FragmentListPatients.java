package pactogramma.pactogramma;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pactogramma.pactogramma.DataBases.DataBasePatients;
import pactogramma.pactogramma.DataBases.DataBaseShema;

public class FragmentListPatients extends Fragment {

    private RecyclerView rRecyclerViewPatients;
    private PatientAdapter rPatientAdapter;
    private List <PatientsList> rPatientsLists;
    private DataBasePatients rDataBasePatients;
    private SQLiteDatabase rSqLiteDatabase;
    private Cursor rCursor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rPatientsLists = new ArrayList<>();
        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getReadableDatabase();
        rCursor = rSqLiteDatabase.query(DataBaseShema.Patient.PATIENT, null,null,null,null,null, null);
        if (rCursor.moveToFirst()){
            do{
                String name = rCursor.getString(rCursor.getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                PatientsList patientsList = new PatientsList(name,null,null,null,null,null);
                //String name = rCursor.getString(rCursor.getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                //String name = rCursor.getString(rCursor.getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                //String name = rCursor.getString(rCursor.getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                //String name = rCursor.getString(rCursor.getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                //String name = rCursor.getString(rCursor.getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                //String name = rCursor.getString(rCursor.getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                rPatientsLists.add(patientsList);
            }while (rCursor.moveToNext());
        }
        rPatientAdapter = new PatientAdapter(rPatientsLists);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patients, container, false);
        rRecyclerViewPatients = view.findViewById(R.id.list_patients_recycler_view);
        rRecyclerViewPatients.setLayoutManager(new LinearLayoutManager(getActivity()));

        rRecyclerViewPatients.setAdapter(rPatientAdapter);
        return view;
    }

    private class PatientAdapter extends RecyclerView.Adapter<PatientHolder>{

        private List <PatientsList> rPatientsListsRecyclerView;

        public PatientAdapter(List<PatientsList> rPatientsListsRecyclerView) {
            this.rPatientsListsRecyclerView = rPatientsListsRecyclerView;
        }

        @NonNull
        @Override
        public PatientHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.patients_item_recycler_view, viewGroup,false);
            return new PatientHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientHolder patientHolder, int i) {
            PatientsList patientsList = rPatientsListsRecyclerView.get(i);
            patientHolder.rPatientsTextView.setText(patientsList.getName() + "    " + patientsList.getNumber_medical_history());
        }

        @Override
        public int getItemCount() {
            return rPatientsListsRecyclerView.size();
        }
    }

    private class PatientHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView rPatientsTextView;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            rPatientsTextView = itemView.findViewById(R.id.patient_item_text_view);
            rPatientsTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

                Toast.makeText(getContext(), rPatientsTextView.getText().toString(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
