package com.example.CarPay.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.R;
import com.example.CarPay.adapter.CustomerPaymetnAdapter;
import com.example.CarPay.model.Customer;

public class CustomersPayment extends AppCompatActivity implements CustomerPaymetnAdapter.OnUserClicked {
    CustomerPaymetnAdapter adapter;
DataBase mydb=new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_payment);
        RecyclerView recyclerView=findViewById(R.id.customer_payment_rv);

        adapter=new CustomerPaymetnAdapter(mydb.getAllCustomersPayment(),this,this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onDelete(Customer c) {
        mydb.deleteCustomer(c.getCustomerName(),c.getCustomerEmail());
        adapter.notifyDataSetChanged();
        Intent intent=new Intent(CustomersPayment.this,CustomersPayment.class);
        startActivity(intent);
        finish();
    }
}