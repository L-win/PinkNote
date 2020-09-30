package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddEditNoteActivity extends AppCompatActivity  {

    public static final String EXTRA_ID = "com.example.myapplication.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.myapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.myapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.myapplication.EXTRA_PRIORITY";
    public static final String EXTRA_DATE_ADDED = "com.example.myapplication.EXTRA_DATE_ADDED";


    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextPriority;
    private TextView textViewDateAdded;
    private Switch switchPin;

    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
//        editTextPriority = findViewById(R.id.edit_text_priority);
        textViewDateAdded = findViewById(R.id.text_view_date_added);
        switchPin = findViewById(R.id.switch1);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            String priority = Integer.toString(intent.getIntExtra(EXTRA_PRIORITY,0));
//            editTextPriority.setText(priority);
            textViewDateAdded.setText(intent.getStringExtra(EXTRA_DATE_ADDED));
            if(priority.equals("1")){
                switchPin.setChecked(true);
            }
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
//        String priorityT = editTextPriority.getText().toString();
        int priorityNew;
        if (switchPin.isChecked()){
            priorityNew = 1;
        }else {
            priorityNew=0;
        }

        String dateAdded;
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dateAdded = Integer.toString(year) +
                "." + Integer.toString(month) +
                "." + Integer.toString(day);

        if (title.trim().isEmpty()||description.trim().isEmpty()){
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priorityNew);
        data.putExtra(EXTRA_DATE_ADDED,dateAdded);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if (id != -1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}