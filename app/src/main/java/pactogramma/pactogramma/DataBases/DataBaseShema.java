package pactogramma.pactogramma.DataBases;

public class DataBaseShema {
    // таблица с пациентками
    public static final class Patient{
        public static final String PATIENT = "patient";
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
        public static final String PULSES = "babypulses";
        public static final class Columns {
            public static final String ID = "id";
            public static final String HEARTBEAT = "heartbeat";
            public static final String TIME = "time";
        }
    }

    // •	Окситоцин
    public static final class Oxytocin{
        public static final String OXYTOCIN = "pulse";
        public static final class Columns{
            public static final String ID = "id";
            public static final String oxytocin = "oxytocin";
        }
    }
    //•	Полученные лекарства
    public static final class MedicationsReceived{

    }
    // •	Пульс
    public static final class Pulse {
        public static final String PULSE = "pulse";
        public static final class Columns{
            public static final String ID = "id";
            public static final String PULSE = "pulse";
        }
    }
    // •Давление
    public static final class Pressure {
        public static final String PRESSURE = "pulse";
        public static final class Columns{
            public static final String ID = "id";
            public static final String PRESSURE = "pressure";
        }
    }
    // •	Температура
    public static final class Temperature{
        public static final String TEMPERATURE = "temperature";
        public static final class Columns {
            public static final String ID = "id";
            public static final String TEMP = "temp";
        }


    }
    // •	Моча
    public static final class Urine{
        public static final String URINE = "urine";
        public static final class Columns{
            public static final String ID = "id";
            public static final String PROTEIN = "protein";
            public static final String ACETONE  = "acetone";
            public static final String VOLUME = "volume";
        }

    }
}
