package com.elvina.pinknote.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pinknote.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.myapplication.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.myapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.myapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.myapplication.EXTRA_PRIORITY";
    public static final String EXTRA_DATE_ADDED = "com.example.myapplication.EXTRA_DATE_ADDED";


    private EditText editTextTitle;
    private EditText editTextDescription;
    private TextView textViewDateAdded;
    private Switch switchPin;

    Calendar c = Calendar.getInstance();

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // SETTINGS
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean settingsDarkMode = preferences.getBoolean("check_box_dark_mode", true);
        if (settingsDarkMode) {
            setTheme(R.style.darktheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        textViewDateAdded = findViewById(R.id.text_view_date_added);
        switchPin = findViewById(R.id.switch1);

        mainLayout = findViewById(R.id.main_layout);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            String priority = Integer.toString(intent.getIntExtra(EXTRA_PRIORITY, 0));
            if (priority.equals("1")) {
                switchPin.setChecked(true);
            }

            String currDate = intent.getStringExtra(EXTRA_DATE_ADDED);
            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            Date date = null;
            try {
                date = parser.parse(currDate);
            } catch (Exception e) {

            }
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
            String formattedDate = formatter.format(date);

            textViewDateAdded.setText(formattedDate);
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priorityNew;
        if (switchPin.isChecked()) {
            priorityNew = 1;
        } else {
            priorityNew = 0;
        }

        String currentDate = c.getTime().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Snackbar.make(mainLayout, "Fields are empty.", Snackbar.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priorityNew);
        data.putExtra(EXTRA_DATE_ADDED, currentDate);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}