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
        public static final String ID = "id";
        public static final String HEARTBEAT = "heartbeat";

    }
    //•	Околоплодные воды и конфигурация головки
    public static final class WhatPregnancy{

    }
    // •	Шейка матки и прохождение головки
    // •	Маточные сокращения
    // •	Окситоцин
    public static final class Oxytocin{

    }
    //•	Полученные лекарства
    public static final class MedicationsReceived{

    }
    // •	Пульс и давление
    public static final class PulseAndPressure
    {

    }
    // •	Температура
    public static final class DataAndTimeHospitalization{

    }
    // •	Моча
    public static final class Urine{

    }
}
