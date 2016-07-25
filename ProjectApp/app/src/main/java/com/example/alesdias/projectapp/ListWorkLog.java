package com.example.alesdias.projectapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.alesdias.projectapp.DataContract.WorkLogEntryContract;
import com.example.alesdias.projectapp.DatabaseManager.WorkLogDbHelper;

public class ListWorkLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_work_log);

        TableLayout v_tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        SQLiteDatabase v_database = WorkLogDbHelper.getInstance(this).getReadableDatabase();

        String[] v_projection = {
                WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_DATE,
                WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_START_TIME,
                WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_STOP_TIME
        };

        String v_sortOrder = WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_DATE + " DESC";

        Cursor v_cursor = v_database.query(
                WorkLogEntryContract.WorkLogEntry.TABLE_NAME,
                v_projection,
                null,
                null,
                null,
                null,
                v_sortOrder);

        View v_titleRow = LayoutInflater.from(this).inflate(R.layout.table_item, null, false);

        TextView v_titleDisplayDate = (TextView) v_titleRow.findViewById(R.id.column_display_date);
        TextView v_titleDisplayStartTime  = (TextView) v_titleRow.findViewById(R.id.column_display_start_time);
        TextView v_titleDisplayStopTime  = (TextView) v_titleRow.findViewById(R.id.column_display_stop_time);

        v_titleDisplayDate.setText(getString(R.string.date_work_log_hint));
        v_titleDisplayStartTime.setText(getString(R.string.start_log_hint));
        v_titleDisplayStopTime.setText(getString(R.string.stop_log_hint));

        v_tableLayout.addView(v_titleRow);

        if (v_cursor != null ) {
            if (v_cursor.moveToFirst()) {
                do {
                    String v_dateString = v_cursor.getString(v_cursor.getColumnIndex(WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_DATE));
                    String v_startTimeString = v_cursor.getString(v_cursor.getColumnIndex(WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_START_TIME));
                    String v_stopTimeString = v_cursor.getString(v_cursor.getColumnIndex(WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_STOP_TIME));

                    View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item, null, false);

                    TextView v_columnDisplayDate = (TextView) tableRow.findViewById(R.id.column_display_date);
                    TextView v_columnDisplayStartTime  = (TextView) tableRow.findViewById(R.id.column_display_start_time);
                    TextView v_columnDisplayStopTime  = (TextView) tableRow.findViewById(R.id.column_display_stop_time);

                    v_columnDisplayDate.setText(v_dateString);
                    v_columnDisplayStartTime.setText(v_startTimeString);
                    v_columnDisplayStopTime.setText(v_stopTimeString);

                    v_tableLayout.addView(tableRow);

                } while (v_cursor.moveToNext());
            }
        }
        v_cursor.close();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void onBackClick(View p_view)
    {
        Intent v_intent = new Intent(ListWorkLog.this, MainActivity.class);
        ListWorkLog.this.setResult(Activity.RESULT_OK, v_intent);
        finish();
    }
}
