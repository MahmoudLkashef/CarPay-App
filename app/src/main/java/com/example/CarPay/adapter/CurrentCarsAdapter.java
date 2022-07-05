package com.example.CarPay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CarPay.model.Car;
import com.example.CarPay.R;

import java.util.ArrayList;

public class CurrentCarsAdapter extends RecyclerView.Adapter<CurrentCarsAdapter.CurrentCarsViewHolder> {
    ArrayList<Car> arrayList=new ArrayList<Car>();
    Context context;
    onUserClicked onUserClicked;
    public CurrentCarsAdapter(ArrayList<Car> arrayList, Context context,onUserClicked onUserClicked) {
        this.arrayList = arrayList;
        this.context = context;
        this.onUserClicked=onUserClicked;
    }
    public interface onUserClicked
    {
        public void onUserSingleClicked(Car c,int position);
        public void onDeleteClicked(Car c,int position);
    }

    @NonNull
    @Override
    public CurrentCarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_current_car_item,parent,false);
        return new CurrentCarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentCarsViewHolder holder, int position) {
        Car car=arrayList.get(position);

        holder.carName.setText(car.getName());
        holder.price.setText(car.getPrice());
        holder.carModel.setText(car.getModelYear());
        holder.edit.setOnClickListener(view -> onUserClicked.onUserSingleClicked(car,position));
        //holder.image.(car.getImage());
        holder.image.setImageBitmap(car.getImg());
        holder.delete.setOnClickListener(view -> onUserClicked.onDeleteClicked(car,position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CurrentCarsViewHolder extends RecyclerView.ViewHolder
    {
        TextView carName,carModel,price;
        ImageView image,delete;
        Button edit;
        public CurrentCarsViewHolder(@NonNull View itemView) {
            super(itemView);
            carName=itemView.findViewById(R.id.carName_TV_current);
            carModel=itemView.findViewById(R.id.modelYear_TV_current);
            price=itemView.findViewById(R.id.price_current);
            image=itemView.findViewById(R.id.car_image_current);
            edit=itemView.findViewById(R.id.edit_btn_current);
            delete=itemView.findViewById(R.id.delete_current);
        }
    }
}
