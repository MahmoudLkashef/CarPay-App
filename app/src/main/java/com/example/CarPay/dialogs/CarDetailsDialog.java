package com.example.CarPay.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.CarPay.model.Car;
import com.example.CarPay.utility.Converter;
import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.ui.PaymentActivity;
import com.example.CarPay.R;

public class CarDetailsDialog {
    Context context;

    public CarDetailsDialog(Context context) {
        this.context = context;
    }

    public void DetailsDialog(Car c, String customerEmail)
{
    Dialog dialog=new Dialog(context);
    dialog.setContentView(R.layout.details_dialog2);
    dialog.setCancelable(false);

    TextView carName,carModel,price,speed,gas,seats,type,horsePower,motorCapacity;
    Button book;
    ImageView close , carImage;

    carName=dialog.findViewById(R.id.car_name);
    carModel=dialog.findViewById(R.id.car_model);
    price=dialog.findViewById(R.id.price_dialog);
    speed=dialog.findViewById(R.id.speed);
    gas=dialog.findViewById(R.id.gas);
    seats=dialog.findViewById(R.id.seats);
    type=dialog.findViewById(R.id.type);
    horsePower=dialog.findViewById(R.id.horsePower);
    motorCapacity=dialog.findViewById(R.id.motorCapacity);
    carImage=dialog.findViewById(R.id.car_image_dialog);
    book=dialog.findViewById(R.id.bookNow_btn);
    close=dialog.findViewById(R.id.close);

    carName.setText(c.getName());
    carModel.setText(c.getModelYear());
    price.setText(c.getPrice());
    carImage.setImageBitmap(c.getImg());
    speed.setText(c.getSpeed());
    gas.setText(c.getGas());
    seats.setText(c.getSeats());
    type.setText(c.getType());
    horsePower.setText(c.getHorsePower());
    motorCapacity.setText(c.getMotorCapacity());

    close.setOnClickListener(view -> {
        dialog.dismiss();
    });

    book.setOnClickListener(view -> {
        DataBase mydb=new DataBase(context);
        Intent intent=new Intent(context, PaymentActivity.class);
        byte[]image= Converter.getBytes(c.getImg());
        intent.putExtra("car",c);
        intent.putExtra("image",image);
        intent.putExtra("customerEmail",customerEmail);
        context.startActivity(intent);
        dialog.dismiss();
    });
    //Set the background of the dialog's root view to transparent, because Android puts your dialog layout within a root view that hides the corners in your custom layout.
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.show();

}
}