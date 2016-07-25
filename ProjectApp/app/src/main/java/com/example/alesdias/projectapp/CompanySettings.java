package com.example.alesdias.projectapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CompanySettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_settings);

        Intent intent = getIntent();
        double v_messageCurrentLatitude = intent.getDoubleExtra(MainActivity.MSG_CURRENT_LATITUDE, 37.422);
        double v_messageCurrentLongitude = intent.getDoubleExtra(MainActivity.MSG_CURRENT_LONGITUDE, -122.084);

        TextView v_etLatitude = (TextView) findViewById(R.id.comp_set_latitude);
        v_etLatitude.setText(String.valueOf(v_messageCurrentLatitude));

        TextView v_etLongitude = (TextView) findViewById(R.id.comp_set_longitude);
        v_etLongitude.setText(String.valueOf(v_messageCurrentLongitude));
    }

    public void onSaveCompanySettings(View p_view)
    {
        Intent v_intent = new Intent(this, MainActivity.class);
        this.setResult(Activity.RESULT_OK, v_intent);
        finish();
    }

    public void onBackCLickCompanySettings(View p_view)
    {
        Intent v_intent = new Intent(this, MainActivity.class);
        this.setResult(Activity.RESULT_CANCELED, v_intent);
        finish();
    }
}
