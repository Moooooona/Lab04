package com.example.lab04;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public class AddNoteActivity extends AppCompatActivity {
    EditText edNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edNote=findViewById(R.id.edNote);
    }

    public void onBtnSaveAndCloseClick(View view){
        String noteToAdd= this. edNote.getText().toString();

        Date c= Calendar.getInstance().getTime();
        System.out.println("Current time =>"+c);
        SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate= df.format(c);

        SharedPreferences sharedPref= this.getSharedPreferences(Constants.NOTES_FILE, this.MODE_PRIVATE);
        SharedPreferences.Editor editore=sharedPref.edit();

        Set<String> savedSet= sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);
        Set<String> newSet= new HashSet<>();
        if (savedSet != null){
            newSet.addAll(savedSet);
        }
        newSet.add(noteToAdd);

        editore.putString(Constants.NOTE_KEY, noteToAdd);
        editore.putString(Constants.NOTE_KEY_DATE,formattedDate );
        editore.putStringSet(Constants.NOTES_ARRAY_KEY, newSet);
        editore.apply();

        finish();
    }
}

