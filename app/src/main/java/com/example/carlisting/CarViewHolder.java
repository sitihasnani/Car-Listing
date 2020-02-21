package com.example.carlisting;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView yearTxt, nameTxt,priceTxt;
    CarAdapter.CarListener carListener;

    public CarViewHolder(View itemView, CarAdapter.CarListener carListener) {
        super(itemView);

        yearTxt = (TextView) itemView.findViewById(R.id.yearTxt);
        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        priceTxt = (TextView) itemView.findViewById(R.id.priceTxt);

        this.carListener = carListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        carListener.onCarListClick(getAdapterPosition());
    }

}
