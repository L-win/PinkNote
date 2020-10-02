package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // SETTINGS
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean settingsDarkMode = preferences.getBoolean("check_box_dark_mode", true);
        if(settingsDarkMode){
            setTheme(R.style.PreferenceScreenDark);
        }else {
            setTheme(R.style.PreferenceScreen);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setTitle("Settings");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
    }
}