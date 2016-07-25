package com.example.alesdias.projectapp.DataContract;

import android.provider.BaseColumns;

public class WorkLogEntryContract {

    public WorkLogEntryContract() {}

    public static abstract class WorkLogEntry implements BaseColumns {

        public static final String TABLE_NAME = "work_log";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_START_TIME = "start_time";
        public static final String COLUMN_NAME_STOP_TIME = "stop_time";

    }
}
