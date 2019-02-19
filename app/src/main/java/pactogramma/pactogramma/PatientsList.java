package pactogramma.pactogramma;

public class PatientsList {
    private String name;
    private String what_pregnancy;
    private String which_account_birth;
    private String number_medical_history;
    private String data_and_time_hospitalization;
    private String period_duration;

    public PatientsList(String name, String what_pregnancy, String which_account_birth, String number_medical_history, String data_and_time_hospitalization, String period_duration) {
        this.name = name;
        this.what_pregnancy = what_pregnancy;
        this.which_account_birth = which_account_birth;
        this.number_medical_history = number_medical_history;
        this.data_and_time_hospitalization = data_and_time_hospitalization;
        this.period_duration = period_duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhat_pregnancy() {
        return what_pregnancy;
    }

    public void setWhat_pregnancy(String what_pregnancy) {
        this.what_pregnancy = what_pregnancy;
    }

    public String getWhich_account_birth() {
        return which_account_birth;
    }

    public void setWhich_account_birth(String which_account_birth) {
        this.which_account_birth = which_account_birth;
    }

    public String getNumber_medical_history() {
        return number_medical_history;
    }

    public void setNumber_medical_history(String number_medical_history) {
        this.number_medical_history = number_medical_history;
    }

    public String getData_and_time_hospitalization() {
        return data_and_time_hospitalization;
    }

    public void setData_and_time_hospitalization(String data_and_time_hospitalization) {
        this.data_and_time_hospitalization = data_and_time_hospitalization;
    }

    public String getPeriod_duration() {
        return period_duration;
    }

    public void setPeriod_duration(String period_duration) {
        this.period_duration = period_duration;
    }
}
