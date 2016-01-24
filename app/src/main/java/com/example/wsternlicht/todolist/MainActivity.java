package com.example.wsternlicht.todolist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.Serializable;
import java.util.*;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemLongClickListener {

    private ArrayList<String> ToDoArray;
    private ArrayAdapter<String> Adapter;
    private static final String ARRAY = "ARRAY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // connect the ListView to an ArrayList of tasks
        ListView list = (ListView) findViewById(R.id.list);
        list.setOnItemLongClickListener(this);
        ToDoArray = new ArrayList<>();
        Adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        ToDoArray);
        list.setAdapter(Adapter);

    }

    public void addItemToList(View view) {
        EditText newEntry = (EditText) findViewById(R.id.newEntry);
        String newEntryString = newEntry.getText().toString();
        if (newEntryString.matches("")) {
            Toast.makeText(this,"Please Enter a Task!", Toast.LENGTH_SHORT).show();
            return;
        }
        addEntryToList(newEntryString);
        newEntry.setText("");

    }

    public void addEntryToList(String entryString){
        ToDoArray.add(0, entryString);
        ListView list = (ListView) findViewById(R.id.list);
        Adapter.notifyDataSetChanged();
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long rowID) {
        ToDoArray.remove(index);
        Adapter.notifyDataSetChanged();
        return false;
    }

 /************************************Save and Restore State ***********************************/


    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("array", ToDoArray);

    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        ToDoArray = (ArrayList) bundle.getSerializable("array");
        Adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ToDoArray);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();

    }

}
