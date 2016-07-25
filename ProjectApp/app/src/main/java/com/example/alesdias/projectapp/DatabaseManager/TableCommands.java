package com.example.alesdias.projectapp.DatabaseManager;

import com.example.alesdias.projectapp.DataContract.WorkInfoContract;
import com.example.alesdias.projectapp.DataContract.WorkLogEntryContract;

public class TableCommands {

    public static final String REAL_TYPE = " REAL";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ", ";
    public static final String PRIMARY_KEY = " INTEGER PRIMARY KEY";

    public static final String SQL_CREATE_WORK_LOG =
            "CREATE TABLE " + WorkLogEntryContract.WorkLogEntry.TABLE_NAME + " ( "  +
                    WorkLogEntryContract.WorkLogEntry._ID + PRIMARY_KEY + COMMA_SEP +
                    WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_START_TIME + TEXT_TYPE + COMMA_SEP +
                    WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_STOP_TIME + TEXT_TYPE +
                    " )";

    public static final String SQL_CREATE_WORK_INFO =
            "CREATE TABLE " + WorkInfoContract.WorkInfo.TABLE_NAME + " ( " +
                    WorkInfoContract.WorkInfo._ID + PRIMARY_KEY + COMMA_SEP +
                    WorkInfoContract.WorkInfo.COLUMN_NAME_COMPANY_NAME + TEXT_TYPE + COMMA_SEP +
                    WorkInfoContract.WorkInfo.COLUMN_NAME_SHIFT_TIME + TEXT_TYPE + COMMA_SEP +
                    WorkInfoContract.WorkInfo.COLUMN_NAME_COMPANY_LOC_LAT + REAL_TYPE + COMMA_SEP +
                    WorkInfoContract.WorkInfo.COLUMN_NAME_COMPANY_LOC_LON + REAL_TYPE +
                    " )";

    public static final String SQL_DELETE_WORK_LOG =
            "DROP TABLE IF EXISTS " + WorkLogEntryContract.WorkLogEntry.TABLE_NAME;

    public static final String SQL_DELETE_WORK_INFO =
            "DROP TABLE IF EXISTS " + WorkInfoContract.WorkInfo.TABLE_NAME;

}
