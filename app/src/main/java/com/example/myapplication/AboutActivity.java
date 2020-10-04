package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        boolean settingsDarkMode = preferences.getBoolean("check_box_dark_mode", true);
        if (preferences.getBoolean("check_box_dark_mode", true)) {
            setTheme(R.style.darktheme);
//            darkModeState = true;
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");
    }
}