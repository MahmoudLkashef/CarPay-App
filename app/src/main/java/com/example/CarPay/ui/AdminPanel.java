package com.example.CarPay.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.CarPay.R;

public class AdminPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        CardView addCar,currentCars,statistics,customersPayment;
        addCar=findViewById(R.id.addCar_cv);
        currentCars=findViewById(R.id.currentCars_cv);
        statistics=findViewById(R.id.statistics_cv);
        customersPayment=findViewById(R.id.customersPayment_cv);

        addCar.setOnClickListener(view -> {
            Intent intent =new Intent(AdminPanel.this,AddCar.class);
            startActivity(intent);
        });
        currentCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AdminPanel.this,CurrentCars.class);
                startActivity(intent);
            }
        });
        statistics.setOnClickListener(view -> Toast.makeText(AdminPanel.this, "statistics", Toast.LENGTH_SHORT).show());
        customersPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminPanel.this,CustomersPayment.class);
                startActivity(intent);
            }
        });
    }
}