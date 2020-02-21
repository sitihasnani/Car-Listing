package com.example.carlisting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.carlisting.model.CarModel;
import com.example.carlisting.util.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CarAdapter.CarListener {

    DatabaseHelper myDB;
    public RecyclerView recyclerView;
    ArrayList<CarModel> arrayList = new ArrayList<CarModel>();
    CarAdapter carAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.listOfCar);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        carAdapter = new CarAdapter(this, arrayList, this);
        recyclerView.setAdapter(carAdapter);
        onLoadList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddNewCarActivity.class);
                startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void onLoadList() {

        try{

            myDB.open();
            cursor = myDB.loadData();
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        CarModel carModel = new CarModel();
                        carModel.setId(Integer.parseInt(cursor.getString(0)));
                        carModel.setYear(cursor.getString(1));
                        carModel.setName(cursor.getString(2));
                        carModel.setPrice(cursor.getString(4));
                        carModel.setDescription(cursor.getString(6));

                        Log.d("TAG", "LIst from database size::"+ cursor.getString(1));

                        arrayList.add(carModel);

                    }while (cursor.moveToNext());
                }
            }

        }catch (SQLiteException e){e.printStackTrace();}




        carAdapter.notifyDataSetChanged();

        Log.d("TAG", "LIst from database size"+ arrayList.size());
    }

    @Override
    public void onCarListClick(int position) {
        Log.d("TAG", "oncarclick "+arrayList.get(position).getId());
//        arrayList.get(position);
        Intent i = new Intent(MainActivity.this, CarDetailsActivity.class);
        i.putExtra("idCar", ""+arrayList.get(position).getId());
        i.putExtra("year", arrayList.get(position).getYear());
        i.putExtra("name", arrayList.get(position).getName());
        i.putExtra("price", arrayList.get(position).getPrice());
        i.putExtra("description", ""+arrayList.get(position).getDescription());
        startActivity(i);

    }

}
