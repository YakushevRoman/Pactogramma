package pactogramma.pactogramma;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public static final String TAG = "phone";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*ArrayList<String> contacts = new ArrayList<String>();
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if(cursor!=null){
            while (cursor.moveToNext()) {

                // получаем каждый контакт
                String contact = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                // добавляем контакт в список
                contacts.add(contact);
            }
            cursor.close();
        }
        Log.d(TAG, "hasWhatsapp: " + contacts);*/

        rPatientsLists = new ArrayList<>();
        rDataBasePatients = new DataBasePatients(getContext());
        rSqLiteDatabase = rDataBasePatients.getReadableDatabase();
        rCursor = rSqLiteDatabase.
                query(DataBaseShema.Patient.PATIENT,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (rCursor.moveToFirst()){
            do{
                int id = rCursor.
                        getInt(rCursor.
                                getColumnIndex(DataBaseShema.Patient.Columns.ID));
                String name = rCursor.
                        getString(rCursor.
                                getColumnIndex(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME));
                String what_pregnancy_edit_text = rCursor.
                        getString(rCursor.
                                getColumnIndex(DataBaseShema.Patient.Columns.WHAT_PREGNANCY));
                String which_account_birth = rCursor.
                        getString(rCursor.
                                getColumnIndex(DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH));
                String number_medical_history = rCursor.
                        getString(rCursor.
                                getColumnIndex(DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_));
                String data_and_time_hospitalization = rCursor.
                        getString(rCursor.
                                getColumnIndex(DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION));
                String period_duration = rCursor.
                        getString(rCursor.
                                getColumnIndex(DataBaseShema.Patient.Columns.PERIOD_DURATION));

                PatientsList patientsList = new PatientsList(id,
                        name,
                        what_pregnancy_edit_text,
                        which_account_birth,
                        number_medical_history,
                        data_and_time_hospitalization,
                        period_duration);
                rPatientsLists.add(patientsList);
            }while (rCursor.moveToNext());
        }
        rPatientAdapter = new PatientAdapter(rPatientsLists);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.
                inflate(R.layout.fragment_patients,
                        container,
                        false);
        rRecyclerViewPatients = view.
                findViewById(R.id.list_patients_recycler_view);
        rRecyclerViewPatients.
                setLayoutManager(new LinearLayoutManager(getActivity()));

        rRecyclerViewPatients.
                setAdapter(rPatientAdapter);

        /*GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(4, 0)
        });
        graph.addSeries(series);*/

        return view;
    }

    private class PatientAdapter extends RecyclerView.Adapter<PatientHolder>{

        private List <PatientsList> rPatientsListsRecyclerView;
        private Bundle bundle;
        PatientAdapter(List<PatientsList> rPatientsListsRecyclerView) {
            this.rPatientsListsRecyclerView = rPatientsListsRecyclerView;

        }

        @NonNull
        @Override
        public PatientHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.patients_item_recycler_view, viewGroup,false);
            bundle = new Bundle();
            return new PatientHolder(view, bundle);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientHolder patientHolder, int i) {
            PatientsList patientsList = rPatientsListsRecyclerView.get(i);

            bundle.putInt(DataBaseShema.Patient.Columns.ID,patientsList.getID());
            bundle.putString(DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME,patientsList.getName());
            bundle.putString(DataBaseShema.Patient.Columns.WHAT_PREGNANCY,patientsList.getWhat_pregnancy());
            bundle.putString(DataBaseShema.Patient.Columns.WHICH_ACCOUNT_BIRTH,patientsList.getWhich_account_birth());
            bundle.putString(DataBaseShema.Patient.Columns.NUMBER_MEDICAL_HISTORY_,patientsList.getNumber_medical_history());
            bundle.putString(DataBaseShema.Patient.Columns.DATA_AND_TIME_HOSPITALIZATION,patientsList.getData_and_time_hospitalization());
            bundle.putString(DataBaseShema.Patient.Columns.PERIOD_DURATION,patientsList.getPeriod_duration());

            patientHolder.rPatientsTextView.setText(patientsList.getName());
        }

        @Override
        public int getItemCount() {
            return rPatientsListsRecyclerView.size();
        }
    }

    private class PatientHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView rPatientsTextView;
        private Bundle bundle;

        PatientHolder(@NonNull View itemView, Bundle bundle) {
            super(itemView);
            rPatientsTextView = itemView.findViewById(R.id.patient_item_text_view);
            rPatientsTextView.setOnClickListener(this);
            rPatientsTextView.setOnLongClickListener(this);
            this.bundle = bundle;
        }

        @Override
        public void onClick(View v) {
            FragmentManager rFragmentManager = getFragmentManager();
            Fragment rFragment = rFragmentManager.findFragmentById(R.id.fragment_container);

            if(rFragment != null) {
                rFragment = new FragmentGraph();
                rFragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, rFragment)
                        .commit();
            }
            rFragment.setArguments(bundle);

            Snackbar.make(v, rPatientsTextView.getText().toString() , Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show();

        }

        @Override
        public boolean onLongClick(View v) {

            /*rSqLiteDatabase = rDataBasePatients.getWritableDatabase();
            int delItems = rSqLiteDatabase.delete(DataBaseShema.Patient.PATIENT, DataBaseShema.Patient.Columns.FIRSTNAME_LASTNAME + " = Valentina Durova" , null);

            Snackbar.make(v, "Удалено :" + delItems , Snackbar.LENGTH_LONG)
                    //.setAction("Action", null)
                    .show();*/
            return true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        rDataBasePatients.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
