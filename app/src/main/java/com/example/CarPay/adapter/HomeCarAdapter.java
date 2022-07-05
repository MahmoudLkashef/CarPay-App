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
import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.R;

import java.util.ArrayList;

public class HomeCarAdapter extends RecyclerView.Adapter<HomeCarAdapter.CarViewHolder> {
    ArrayList<Car>arrayList=new ArrayList<Car>();
    Context context;
    private OnUserClicked onUserClicked;
    public HomeCarAdapter(ArrayList<Car> arrayList, Context context, OnUserClicked onUserClicked) {
        this.arrayList = arrayList;
        this.context = context;
        this.onUserClicked=onUserClicked;
    }
    public interface OnUserClicked
    {
        public void onUserSingleClicked(Car c,int position);
    }
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_car_item,parent,false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car=arrayList.get(position);
        holder.name.setText(car.getName());
        holder.modelYear.setText(car.getModelYear());
        holder.price.setText(car.getPrice());
        holder.details.setOnClickListener(view -> {
            onUserClicked.onUserSingleClicked(car,position);
        });
        //holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.mustang_png51));
       // holder.image.setImageDrawable(context.getDrawable(R.drawable.mustang_png51));

       holder.image.setImageBitmap(car.getImg());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,modelYear,price;
        ImageView image;
        Button details;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.carName_TV);
            modelYear=itemView.findViewById(R.id.modelYear_TV);
            price=itemView.findViewById(R.id.price);
            details=itemView.findViewById(R.id.details_btn);
            image=itemView.findViewById(R.id.car_image);
        }
    }
    public void filter(String text,Context context) {
        DataBase mydb=new DataBase(context);
        arrayList.clear();
        if(text.isEmpty()){
            arrayList.addAll(mydb.getAllCars());
        } else{
            text = text.toLowerCase();
            for(Car item: mydb.getAllCars()){
                if(item.getName().toLowerCase().contains(text) || item.getModelYear().toLowerCase().contains(text)||item.getCountry().toLowerCase().contains(text)||item.getType().toLowerCase().contains(text)){
                    arrayList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
