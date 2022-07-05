package com.example.CarPay.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.adapter.HomeCarAdapter;
import com.example.CarPay.dialogs.CarDetailsDialog;
import com.example.CarPay.model.Car;
import com.example.CarPay.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeCarAdapter.OnUserClicked {
DataBase mydb=new DataBase(this);
Button all,german,japan,italian,american;
SearchView searchView;
String customerEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView recyclerView=findViewById(R.id.rv);
        all=findViewById(R.id.all);
        german=findViewById(R.id.german);
        japan=findViewById(R.id.japan);
        italian=findViewById(R.id.italian);
        american=findViewById(R.id.american);
        searchView = findViewById(R.id.search);

        Intent intent=getIntent();
        customerEmail=intent.getStringExtra("customerEmail");

        ArrayList <Car> carArrayList=new ArrayList<>();
        carArrayList.addAll(mydb.getAllCars());

        HomeCarAdapter adapter=new HomeCarAdapter(carArrayList,this,this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        all.setOnClickListener(view -> {
            carArrayList.clear();
            carArrayList.addAll(mydb.getAllCars());
            adapter.notifyDataSetChanged();
        });
        german.setOnClickListener(view -> {
            carArrayList.clear();
            carArrayList.addAll(mydb.getCarsBelongToCountry("german"));
            adapter.notifyDataSetChanged();
        });
        japan.setOnClickListener(view -> {
            carArrayList.clear();
            carArrayList.addAll(mydb.getCarsBelongToCountry("japan"));
            adapter.notifyDataSetChanged();
        });
        italian.setOnClickListener(view -> {
            carArrayList.clear();
            carArrayList.addAll(mydb.getCarsBelongToCountry("italian"));
            adapter.notifyDataSetChanged();
        });
        american.setOnClickListener(view -> {
            carArrayList.clear();
            carArrayList.addAll(mydb.getCarsBelongToCountry("american"));
            adapter.notifyDataSetChanged();
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s.trim(), getApplicationContext());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s.trim(), getApplicationContext());
                return true;
            }
        });

    }
    @Override
    public void onUserSingleClicked(Car c, int position) {
        CarDetailsDialog dialog=new CarDetailsDialog(this);
        dialog.DetailsDialog(c,customerEmail);
    }
}