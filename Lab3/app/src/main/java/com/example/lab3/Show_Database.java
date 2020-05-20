package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Show_Database extends AppCompatActivity {

    ListView studentsList;
    Database db;
    SQLiteDatabase database;
    Cursor cursor;
    cursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__database);

        studentsList = (ListView)findViewById(R.id.list);
        db = new Database(this);
        database = db.getReadableDatabase();

        cursor =  database.rawQuery("select * from students", null);

        adapter = new cursorAdapter(this, cursor);
        studentsList.setAdapter(adapter);
    }

}
