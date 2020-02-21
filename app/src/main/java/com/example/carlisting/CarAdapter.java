package com.example.carlisting;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carlisting.model.CarModel;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    Activity activity;
    List<CarModel> carList ;
    CarListener carListener;

    public CarAdapter(Activity activity, List<CarModel> carModels, CarListener carListener){
        this.activity = activity;
        this.carList = carModels;
        this.carListener = carListener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view, carListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.yearTxt.setText(carList.get(position).getYear());
        holder.nameTxt.setText(carList.get(position).getName());
        holder.priceTxt.setText("RM "+carList.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        Log.i("Adapter", ""+carList.size() );
        return carList.size();
    }

    public interface CarListener{
        void onCarListClick(int position);

    }


}
