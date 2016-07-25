package com.example.alesdias.projectapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.alesdias.projectapp.DataContract.WorkLogEntryContract;
import com.example.alesdias.projectapp.DatabaseManager.WorkLogDbHelper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Locale;

public class WorkLogEntryActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_log_entry);

        Intent intent = getIntent();
        String v_messageDate = intent.getStringExtra(MainActivity.MSG_LOG_DATE);
        String v_messageStarTime = intent.getStringExtra(MainActivity.MSG_LOG_START_DATE);
        String v_messageStopTime = intent.getStringExtra(MainActivity.MSG_LOG_STOP_DATE);

        TextView v_labelLogDate = (TextView) findViewById(R.id.content_work_log);
        v_labelLogDate.setText(v_messageDate);

        TextView v_labelLogStartTime = (TextView) findViewById(R.id.content_start_log);
        v_labelLogStartTime.setText(v_messageStarTime);

        TextView v_labelLogStopTime = (TextView) findViewById(R.id.content_stop_log);
        v_labelLogStopTime.setText(v_messageStopTime);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DisplayMessage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alesdias.projectapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DisplayMessage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alesdias.projectapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public void onSaveWorkLog(final View p_view)
    {
        final String v_dateValue = ((TextView) findViewById(R.id.content_work_log)).getText().toString();
        final String v_startTimeValue = ((TextView) findViewById(R.id.content_start_log)).getText().toString();
        final String v_stopTimeValue = ((TextView) findViewById(R.id.content_stop_log)).getText().toString();

        if (v_dateValue.isEmpty() || v_startTimeValue.isEmpty() || v_stopTimeValue.isEmpty())
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.alert_required_field_title)
                    .setMessage(R.string.alert_required_field_message)
                    .setPositiveButton(R.string.option_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface p_dialogInterface, int p_which) {
                            Intent v_intent = new Intent(WorkLogEntryActivity.this, MainActivity.class);
                            WorkLogEntryActivity.this.setResult(Activity.RESULT_CANCELED, v_intent);
                            finish();
                        }
                    })
                    .show();
        }
        else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.alert_create_log_title)
                    .setMessage(R.string.alert_create_log_message)
                    .setPositiveButton(R.string.option_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface p_dialogInterface, int p_which) {
                            SQLiteDatabase v_database = WorkLogDbHelper.getInstance(WorkLogEntryActivity.this).getWritableDatabase();

                            String[] v_dateElements = v_dateValue.split(getString(R.string.date_separator_screen));
                            String v_valueDateDatabase = String.format(Locale.US, String.format(Locale.US, "%1$s%2$s%3$s%4$s%5$s",
                                    v_dateElements[2], getString(R.string.date_separator_database),
                                    v_dateElements[1], getString(R.string.date_separator_database),
                                    v_dateElements[0]));

                            ContentValues v_values = new ContentValues();
                            v_values.put(WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_DATE, v_valueDateDatabase);
                            v_values.put(WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_START_TIME, v_startTimeValue);
                            v_values.put(WorkLogEntryContract.WorkLogEntry.COLUMN_NAME_STOP_TIME, v_stopTimeValue);

                            v_database.insert(WorkLogEntryContract.WorkLogEntry.TABLE_NAME,
                                              null,
                                              v_values);

                            Intent v_intent = new Intent(WorkLogEntryActivity.this, MainActivity.class);
                            WorkLogEntryActivity.this.setResult(Activity.RESULT_OK, v_intent);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.option_no, null)
                    .show();
        }
    }

    public void onCancelSaveWorkLog(View p_view)
    {
        Intent v_intent = new Intent(WorkLogEntryActivity.this, MainActivity.class);
        WorkLogEntryActivity.this.setResult(Activity.RESULT_CANCELED, v_intent);
        finish();
    }
}
