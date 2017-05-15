package com.example.emily.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
    }


    public void addBoolean (View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString());
        if(isInserted == true){
            Log.d("MyContact", "Data insertion successful");
            //create toast message to user indicating data inserted correctly

        }
        else{
            Log.d("MyContact", "Data insertion unsuccessful");
            //create toast message to user indicating data inserted incorrectly

        }
    }



}