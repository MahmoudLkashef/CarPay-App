package com.example.CarPay.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.dialogs.AddCardDialog;
import com.example.CarPay.model.Car;
import com.example.CarPay.utility.Converter;
import com.example.CarPay.R;

public class PaymentActivity extends AppCompatActivity {
    DataBase mydb=new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        TextView carName,carModel,carPrice,prepaid,payMonth;
        Button payNow;

        ImageView creditCard=findViewById(R.id.imageView4);
        creditCard.setVisibility(View.VISIBLE);
        creditCard.setX(-3000);
        //creditCard.setY(-3000);
        creditCard.animate().translationXBy(3000).rotation(1800).setDuration(1500);
        //creditCard.animate().translationYBy(3000).setDuration(3500);

        ImageButton addCard=findViewById(R.id.addCard);
        carName=findViewById(R.id.carName_payment);
        carModel=findViewById(R.id.carModel_payment);
        carPrice=findViewById(R.id.carPrice_payment);
        prepaid=findViewById(R.id.prepaid);
        payMonth=findViewById(R.id.pay_month);

        payNow=findViewById(R.id.payNow_btn);

        Intent intent=getIntent();
        Car carDetails=intent.getParcelableExtra("car");
        byte[]image=intent.getByteArrayExtra("image");
        carDetails.setImg(Converter.getBitmapImage(image));
        String customerEmail=intent.getStringExtra("customerEmail");


        int totalPrice=(int) (Integer.parseInt(carDetails.getPrice()));
        int cash= (int) (totalPrice*0.25);
        int cashPerMonth=(totalPrice-cash)/30;

        String prePaid=String.valueOf(cash);
        String cashMonth=String.valueOf(cashPerMonth);
        carName.setText(carDetails.getName());
        carModel.setText(carDetails.getModelYear());
        carPrice.setText(carDetails.getPrice());
        prepaid.setText(prePaid);
        payMonth.setText(cashMonth);

        addCard.setOnClickListener(view -> {
            AddCardDialog dialog=new AddCardDialog(PaymentActivity.this);
            dialog.AddCardDialog(customerEmail,carDetails);

        });

        payNow.setOnClickListener(view -> {
            if(mydb.isCustomerCardEmpty(customerEmail))
            {
                Toast.makeText(PaymentActivity.this, "Please add your card", Toast.LENGTH_SHORT).show();
                return;
            }
            mydb.insertCustomerCar(customerEmail,carDetails,String.valueOf(cash));
            Toast.makeText(PaymentActivity.this, "Congratulations", Toast.LENGTH_SHORT).show();
        });
    }
}