package com.example.CarPay.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.CarPay.adapter.CurrentCarsAdapter;
import com.example.CarPay.model.Car;
import com.example.CarPay.utility.CheckEditTextInput;
import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.R;

public class EditCarDialog {
    Context context;
    public EditCarDialog(Context context) {
        this.context = context;
    }

    public void EditCarDialog(Car c, CurrentCarsAdapter adapter, int position)
    {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.edit_car_dialog);
        dialog.setCancelable(false);

        Button done;
        EditText carName,carModel,carPrice;
        ImageView close;
        carName=dialog.findViewById(R.id.carName_ET_editCar);
        carModel=dialog.findViewById(R.id.carModel_ET_editCar);
        carPrice=dialog.findViewById(R.id.carPrice_ET_editCar);
        done=dialog.findViewById(R.id.done_btn_editCar);
        close=dialog.findViewById(R.id.close_editCar);


        close.setOnClickListener(view -> {
            dialog.dismiss();
        });

        done.setOnClickListener(view -> {
            CheckEditTextInput check=new CheckEditTextInput(context);
            if(check.validInput(carName)&&check.validInput(carModel)&&check.validInput(carPrice))
            {
                DataBase mydb=new DataBase(context);
                String carN=carName.getText().toString();
                String carM=carModel.getText().toString();
                String carP=carPrice.getText().toString();
                mydb.updateCarInfo(c.getName(),new Car(carN,carM,carP));
                adapter.notifyItemChanged(position);
                Toast.makeText(context, "Done Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent intent=new Intent(context,context.getClass());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
