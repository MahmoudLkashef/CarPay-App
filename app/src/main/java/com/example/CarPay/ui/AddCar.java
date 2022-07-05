package com.example.CarPay.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.model.Car;
import com.example.CarPay.utility.CheckEditTextInput;
import com.example.CarPay.utility.EditTextProperties;
import com.example.CarPay.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCar extends AppCompatActivity {
ImageView image;
EditText carName,carModel,carPrice,speed,horsePower,motorCapacity,gas,seats,type,country;
Button add;
DataBase mydb=new DataBase(this);
Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        image=findViewById(R.id.addCarImage);
        carName=findViewById(R.id.carName_add);
        carModel=findViewById(R.id.carModel_add);
        carPrice=findViewById(R.id.carPrice_add);
        speed=findViewById(R.id.speed_add);
        horsePower=findViewById(R.id.horsePower_add);
        motorCapacity=findViewById(R.id.motorCapacity_add);
        gas=findViewById(R.id.gas_add);
        seats=findViewById(R.id.seats_add);
        type=findViewById(R.id.type_add);
        add=findViewById(R.id.add_btn);
        country=findViewById(R.id.country_add);

        image.setOnClickListener(view -> openGallery());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carN=carName.getText().toString().trim();
                String carM=carModel.getText().toString().trim();
                String carP=carPrice.getText().toString().trim();
                String carSpeed=speed.getText().toString().trim();
                String carHorsePower=horsePower.getText().toString().trim();
                String carMotorCapacity=motorCapacity.getText().toString().trim();
                String carGas=gas.getText().toString().trim();
                String carSeats=seats.getText().toString().trim();
                String carType=type.getText().toString().trim();
                String carCountry=country.getText().toString().trim();

                CheckEditTextInput check =new CheckEditTextInput(AddCar.this);

                if(bitmap!=null&&check.validInput(carName)&&check.validInput(carModel)&&check.validInput(carPrice)&&check.validInput(speed)&&check.validInput(horsePower)&&check.validInput(motorCapacity)&&check.validInput(gas)&&check.validInput(seats)&&check.validInput(type)&&check.validInput(country)) {
                    mydb.insertCarData(new Car(carN,carM,carP,bitmap,carSpeed,carGas,carMotorCapacity,carHorsePower,carSeats,carType,carCountry));
                    Toast.makeText(AddCar.this, "Number of cars in store : "+mydb.getAllCars().size(), Toast.LENGTH_SHORT).show();
                    bitmap=null;
                    image.setImageDrawable(AddCar.this.getDrawable(R.drawable.default_car));
                    EditTextProperties.deleteText(carName,carModel,carPrice,speed,horsePower,motorCapacity,gas,seats,type,country);
                }
                else if(bitmap==null)Toast.makeText(AddCar.this, "Please pick a car image", Toast.LENGTH_SHORT).show();
                else Toast.makeText(AddCar.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openGallery ()
    {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==RESULT_OK)
        {
            Uri uri=data.getData();

            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("ex",e.getMessage());
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}