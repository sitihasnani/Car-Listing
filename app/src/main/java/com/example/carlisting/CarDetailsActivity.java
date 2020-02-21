package com.example.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carlisting.util.DatabaseHelper;

public class CarDetailsActivity extends AppCompatActivity {

    EditText yearTxt, nameTxt, priceTxt, descriptionTxt;
    Intent intent;
    Button cancelBtn, saveBtn;
    String year, name, price, description;
    int  id;
    DatabaseHelper myDB;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        myDB = new DatabaseHelper(this);

        yearTxt = (EditText) findViewById(R.id.yearTxt);
        nameTxt = (EditText) findViewById(R.id.nameTxt);
        priceTxt = (EditText) findViewById(R.id.priceTxt);
        descriptionTxt = (EditText) findViewById(R.id.descriptionTxt);

        intent = getIntent();

        yearTxt.setText(intent.getStringExtra("year"));
        nameTxt.setText(intent.getStringExtra("name"));
        priceTxt.setText(intent.getStringExtra("price"));
        descriptionTxt.setText(intent.getStringExtra("description"));

        id = Integer.parseInt(intent.getStringExtra("idCar"));

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = yearTxt.getText().toString();
                name = nameTxt.getText().toString();
                price = priceTxt.getText().toString();
                description = descriptionTxt.getText().toString();
                saveData(year, name, price, description);
            }
        });


    }

    private void saveData(String year, String name, String price, String description) {myDB.open();
        cursor = myDB.updateData(year, name, price, description, id);

    }


}
