package com.example.lab04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listNoteItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView tvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvNotes = findViewById(R.id.tvNotes);
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listNoteItems);
        this.tvNotes.setAdapter(adapter);
    }

    @Override
    public boolean onCreationOptionMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_options_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPref = this.getSharedPreferences(Constants.NOTES_FILE, this.MODE_PRIVATE);
        String lastSavedNote = sharedPref.getString(Constants.NOTE_KEY, "NA");
        String lastSavedNoteDate = sharedPref.getString(Constants.NOTE_KEY_DATE, "1900-01-01");
        Set<String> savedset = sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);

        if (savedset != null) {
            this.listNoteItems.clear();
            ;
            this.listNoteItems.addAll(savedset);
            this.adapter.notifyDataSetChanged();
        }
        Snackbar.make(tvNotes, String.format("%s: %s", " Last saved note", lastSavedNote), Snackbar.LEGTH_LONG).Show();
        Toast.makeText(this, lastSavedNoteDate, Toast.LENGTH_LONG).Show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note:
                Intent i = new Intent(this, AddNoteActivity.class);
                StartActivity(i);
                return true;
            case R.id.remove_note:
                return true;
            case R.id.update_note:
                Toast.makeText(getApplicationContext(), "Update Clicked", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
