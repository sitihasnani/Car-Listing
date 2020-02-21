package com.example.carlisting;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlisting.util.DatabaseHelper;

public class AddNewCarActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText yearEt, carNameEt, priceEt, descriptionEt;
    Button saveBtn, cancelBtn;
    String year, name, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_car); ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Car");

        myDB = new DatabaseHelper(this);

        yearEt = (EditText) findViewById(R.id.yearEt);
        carNameEt = (EditText) findViewById(R.id.nameEt);
        priceEt = (EditText) findViewById(R.id.priceEt);
        descriptionEt = (EditText) findViewById(R.id.descriptionEt);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = yearEt.getText().toString();
                name = carNameEt.getText().toString();
                price = priceEt.getText().toString();
                description = descriptionEt.getText().toString();

                boolean isInserted = myDB.insertData( year, name, price, description);

                if (isInserted = true){
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();


                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();


                }

            }
        });

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
