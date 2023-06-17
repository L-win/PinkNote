package com.elvina.pinknote.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.pinknote.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (preferences.getBoolean("check_box_dark_mode", true)) {
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");
    }
}