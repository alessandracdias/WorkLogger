package com.example.alesdias.projectapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import com.example.alesdias.projectapp.DatabaseManager.WorkLogDbHelper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {

    public final static String MSG_LOG_DATE = "com.example.alesdias.projectapp.MSG_LOG_DATE";
    public final static String MSG_LOG_START_DATE = "com.example.alesdias.projectapp.MSG_LOG_START_DATE";
    public final static String MSG_LOG_STOP_DATE = "com.example.alesdias.projectapp.MSG_LOG_STOP_DATE";
    public final static String MSG_CURRENT_LATITUDE = "com.example.alesdias.projectapp.MSG_CURRENT_LATITUDE";
    public final static String MSG_CURRENT_LONGITUDE = "com.example.alesdias.projectapp.MSG_CURRENT_LONGITUDE";

    public final static int ENTRY_REQUEST_CODE = 100;
    public final static int LIST_REQUEST_CODE = 101;
    public final static int COMPANY_REQUEST_CODE = 102;
    public final static int APP_PERMISSION_ACCESS_COURSE_LOCATION = 99;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient m_googleAPIClient;

    private Location m_lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.snack_message, Snackbar.LENGTH_LONG)
                        .setAction(R.string.snack_action_title, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent v_intent = new Intent(MainActivity.this, ListWorkLog.class);
                                startActivityForResult(v_intent, LIST_REQUEST_CODE);
                            }
                        }).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        m_googleAPIClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(AppIndex.API).build();

        WorkLogDbHelper.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent v_intent = new Intent(this, CompanySettings.class);

            if (m_lastLocation != null)
            {
                v_intent.putExtra(MSG_CURRENT_LATITUDE, m_lastLocation.getLatitude());
                v_intent.putExtra(MSG_CURRENT_LONGITUDE, m_lastLocation.getLongitude());
            }

            startActivityForResult(v_intent, COMPANY_REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        m_googleAPIClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alesdias.projectapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(m_googleAPIClient, viewAction);

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alesdias.projectapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(m_googleAPIClient, viewAction);
        m_googleAPIClient.disconnect();
    }

    @Override
    protected void onActivityResult(int p_requestCode, int p_resultCode, Intent p_data) {
        super.onActivityResult(p_requestCode, p_resultCode, p_data);

        if (p_requestCode == ENTRY_REQUEST_CODE)
        {
            if (p_resultCode == Activity.RESULT_OK)
            {
                ((EditText) findViewById(R.id.date_work_log)).setText("");
                ((EditText) findViewById(R.id.time_start_log)).setText("");
                ((EditText) findViewById(R.id.time_stop_log)).setText("");
            }
        }
    }

    public void onSendMessageWorkLog(View p_view) {
        Intent v_intent = new Intent(this, WorkLogEntryActivity.class);
        EditText v_etLogDate = (EditText) findViewById(R.id.date_work_log);
        EditText v_etLogStartTime = (EditText) findViewById(R.id.time_start_log);
        EditText v_etLogStopTime = (EditText) findViewById(R.id.time_stop_log);

        v_intent.putExtra(MSG_LOG_DATE, v_etLogDate.getText().toString());
        v_intent.putExtra(MSG_LOG_START_DATE, v_etLogStartTime.getText().toString());
        v_intent.putExtra(MSG_LOG_STOP_DATE,v_etLogStopTime.getText().toString());

        startActivityForResult(v_intent, ENTRY_REQUEST_CODE);
    }

    public void onStartLog(View p_view)
    {
        final long v_miliseconds;
        SharedPreferences v_sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        if (v_sharedPreferences.contains(getString(R.string.saved_current_millis)))
        {
            v_miliseconds = v_sharedPreferences.getLong(getString(R.string.saved_current_millis), 0);
        }
        else
        {
            v_miliseconds = System.currentTimeMillis();
        }

        SharedPreferences.Editor v_editor = v_sharedPreferences.edit();
        v_editor.putLong(getString(R.string.saved_current_millis), v_miliseconds);
        v_editor.apply();

        Timer v_timer = new Timer();
        v_timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        long v_currentMillis = System.currentTimeMillis();
                        long v_diff = v_miliseconds - v_currentMillis;
                    }
                });
            }
        }, 1000, 1000);
    }

    public void onTimeClick(final View p_view) {
        Calendar v_currentTime = Calendar.getInstance();
        int v_hour = v_currentTime.get(Calendar.HOUR_OF_DAY);
        int v_minute = v_currentTime.get(Calendar.MINUTE);

        if (!((EditText) p_view).getText().toString().isEmpty())
        {
            String[] v_values = ((EditText) p_view).getText().toString().split(":");
            v_hour = Integer.parseInt(v_values[0]);
            v_minute = Integer.parseInt(v_values[1]);
        }

        TimePickerDialog v_timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker p_timePicker, int p_selectedHour, int p_selectedMinute) {
                ((EditText) p_view).setText(String.format(Locale.US, "%1$02d%2$s%3$02d", p_selectedHour,
                                            getString(R.string.hour_separator), p_selectedMinute));
            }
        }, v_hour, v_minute, true);

        v_timePickerDialog.setTitle(getString(R.string.select_time));
        v_timePickerDialog.show();
    }

    public void onDateClick(final View p_view) {
        Calendar v_currentTime = Calendar.getInstance();
        int v_dayOfMonth = v_currentTime.get(Calendar.DAY_OF_MONTH);
        int v_month = v_currentTime.get(Calendar.MONTH) + 1;
        int v_year = v_currentTime.get(Calendar.YEAR);

        if (!((EditText) p_view).getText().toString().isEmpty())
        {
            String[] v_values = ((EditText) p_view).getText().toString().split(getString(R.string.date_separator_screen));
            v_dayOfMonth = Integer.parseInt(v_values[0]);
            v_month = Integer.parseInt(v_values[1]);
            v_year = Integer.parseInt(v_values[2]);
        }

        DatePickerDialog v_datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker p_datePicker, int p_year, int p_month, int p_dayOfMonth) {
                ((EditText) p_view).setText(String.format(Locale.US, "%1$02d%2$s%3$02d%4$s%5$04d",
                        p_dayOfMonth, getString(R.string.date_separator_screen),
                        p_month, getString(R.string.date_separator_screen),
                        p_year));
            }
        }, v_year, v_month, v_dayOfMonth);

        v_datePickerDialog.setTitle(getString(R.string.select_date));
        v_datePickerDialog.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    APP_PERMISSION_ACCESS_COURSE_LOCATION);
        }
        m_lastLocation = LocationServices.FusedLocationApi.getLastLocation(m_googleAPIClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
