package com.example.alesdias.projectapp.DatabaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkLogDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ProjectAppWorkLog.db";

    private static WorkLogDbHelper g_instance = null;

    public static WorkLogDbHelper getInstance(Context p_activityContext) {
        if (g_instance == null) {
            g_instance = new WorkLogDbHelper(p_activityContext.getApplicationContext());
        }
        return g_instance;
    }

    private WorkLogDbHelper(Context p_context) {
        super(p_context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase p_sqliteDatabase) {
        p_sqliteDatabase.execSQL(TableCommands.SQL_CREATE_WORK_LOG);
        p_sqliteDatabase.execSQL(TableCommands.SQL_CREATE_WORK_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase p_sqLiteDatabase, int p_previousVersion, int p_newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        p_sqLiteDatabase.execSQL(TableCommands.SQL_DELETE_WORK_LOG);
        p_sqLiteDatabase.execSQL(TableCommands.SQL_DELETE_WORK_INFO);
        onCreate(p_sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase p_sqLiteDatabase, int p_previousVersion, int p_newVersion) {
        onUpgrade(p_sqLiteDatabase, p_previousVersion, p_newVersion);
    }
}
