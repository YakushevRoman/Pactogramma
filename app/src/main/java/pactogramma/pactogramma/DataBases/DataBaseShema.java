package pactogramma.pactogramma.DataBases;
// описание базы данных
public class DataBaseShema {
    //•	Параметры для добавления нового пациента
    public static final class Patient{
        public static final String TABLE_PATIENT = "patient";
        public static final class Columns{
            public static final String ID = "id";
            public static final String FIRSTNAME_LASTNAME = "name";
            public static final String WHAT_PREGNANCY = "what_pregnancy";
            public static final String WHICH_ACCOUNT_BIRTH = "which_account_birth";
            public static final String NUMBER_MEDICAL_HISTORY_ = "number_medical_history";
            public static final String DATA_AND_TIME_HOSPITALIZATION = "data_and_time_hospitalization";
            public static final String PERIOD_DURATION = "period_duration";
        }
    }
    //•	Сердцебиение плода
    public static final class BabyHeartbeat{
        public static final String TABLE_PULSES = "babypulses";
        public static final class Columns {
            public static final String ID = "id";
            public static final String HEARTBEAT = "heartbeat";
            public static final String TIME = "time";
        }
    }

    // •Температура
    public static final class Temperature{
        public static final String TABLE_TEMPERATURE = "temperature";
        public static final class Columns {
            public static final String ID = "id";
            public static final String TEMP = "tempe";
            public static final String TIME = "time";
        }
    }
    // •	Окситоцин
    public static final class Oxytocin{
        public static final String TABLE_OXYTOCIN = "pulse";
        public static final class Columns{
            public static final String ID = "id";
            public static final String OXYTOCIN = "oxytocin";
            public static final String TIME = "time";
        }
    }
    //•	Полученные лекарства
    public static final class MedicationsReceived{
        public static final String TABLE_MEDICATIONS_RECEIVED = "medications_received";
        public static final class Columns{
            public static final String ID = "id";
            public static final String MEDICATIONS = "medications";
            public static final String TIME = "time";
        }
    }
    // •	Пульс и давление
    public static final class PulseAndPressure {
        public static final String TABLE_PULSE_AND_PRESSURE = "pulse_and_pressure";
        public static final class Columns{
            public static final String ID = "id";
            public static final String PULSE = "pulse";
            public static final String PRESSURE = "pressure";
            public static final String TIME = "time";
        }
    }
    // •	Моча
    public static final class Urine{
        public static final String TABLE_URINE = "urine";
        public static final class Columns{
            public static final String ID = "id";
            public static final String PROTEIN = "protein";
            public static final String ACETONE  = "acetone";
            public static final String VOLUME = "volume";
            public static final String TIME = "time";

        }
    }
    // ⦁	Маточные сокращения
    public static final class UterineContractions{
        public static final String TABLE_UTERINE_CONTRACTIONS = "uterine_contractions";
        public static final class Columns{
            public static final String ID = "id";
            public static final String TIME = "time";
            public static final String UTERINE_CONTRACTIONS = "uterine_contraction";
        }
    }
}
