package com.example.emily.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editPhoneNumber;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editPhoneNumber = (EditText) findViewById(R.id.editText_phonenumber);
    }


    public void addBoolean (View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editPhoneNumber.getText().toString());
        if(isInserted == true){
            Log.d("MyContact", "Data insertion successful");
            //create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "Data insertion successful", Toast.LENGTH_SHORT).show();


        }
        else{
            Log.d("MyContact", "Data insertion unsuccessful");
            //create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "Data insertion unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }


    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //put a Log.d message and toast

            return;
        }

        StringBuffer buffer = new StringBuffer();
        //setup loop with moveToNext method
            //append each COL to buffer
            // use getString method

        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {

    }



}