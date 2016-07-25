package com.example.alesdias.projectapp.DataContract;

import android.provider.BaseColumns;

public class WorkInfoContract {

    public WorkInfoContract() {}

    public static abstract class WorkInfo implements BaseColumns {

        public static final String TABLE_NAME = "work_info";
        public static final String COLUMN_NAME_COMPANY_NAME = "company_name";
        public static final String COLUMN_NAME_SHIFT_TIME = "shift_time";
        public static final String COLUMN_NAME_COMPANY_LOC_LAT = "company_loc_lat";
        public static final String COLUMN_NAME_COMPANY_LOC_LON = "company_loc_lon";
    }

}
