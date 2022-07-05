package com.example.CarPay.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.adapter.CurrentCarsAdapter;
import com.example.CarPay.dialogs.DeleteCarDialog;
import com.example.CarPay.dialogs.EditCarDialog;
import com.example.CarPay.model.Car;
import com.example.CarPay.R;

public class CurrentCars extends AppCompatActivity implements CurrentCarsAdapter.onUserClicked {
    CurrentCarsAdapter adapter;
    DataBase mydb=new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cars);
        RecyclerView recyclerView=findViewById(R.id.current_cars_rv);

        adapter=new CurrentCarsAdapter(mydb.getAllCars(),this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onUserSingleClicked(Car c, int position) {
        EditCarDialog dialog=new EditCarDialog(this);
        dialog.EditCarDialog(c,adapter,position);
    }

    @Override
    public void onDeleteClicked(Car c, int position) {
        DeleteCarDialog dialog=new DeleteCarDialog(this);
        dialog.deleteCar(c.getName());
    }
}