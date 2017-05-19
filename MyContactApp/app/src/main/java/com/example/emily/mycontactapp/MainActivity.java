package com.example.emily.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    EditText editSearch;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editPhoneNumber = (EditText) findViewById(R.id.editText_phonenumber);
        editSearch = (EditText) findViewById((R.id.editText_search));
    }


    public void addData (View v) {
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
            Log.d("AllContacts", "Data not found");
            Toast.makeText(getApplicationContext(), "Data not found", Toast.LENGTH_SHORT).show();

            return;
        }

        StringBuffer buffer = new StringBuffer();
        //setup loop with moveToNext method
            //append each COL to buffer
            // use getString method

        while (res.moveToNext()) {
            buffer.append("ID " + res.getString(0) + "\n");
            buffer.append("Name " + res.getString(1) + "\n");
            buffer.append("Age " + res.getString(2) + "\n");
            buffer.append("Phone Number " + res.getString(3) + "\n");
        }
        showMessage("Data", buffer.toString());
    }


    public void searchData(View v) { //contact app stops working
        Cursor res = myDb.findData(editSearch);
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("ID " + res.getString(0) + "\n");
            buffer.append("Name " + res.getString(1) + "\n");
            buffer.append("Age " + res.getString(2) + "\n");
            buffer.append("Phone Number " + res.getString(3) + "\n");
        }
        showMessage("Search contact", buffer.toString());
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}