package com.example.CarPay.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.CarPay.DataBase.DataBase;

public class DeleteCarDialog {
    Context context;
    DataBase mydb;

    public DeleteCarDialog(Context context) {
        this.context = context;
        mydb=new DataBase(context);
    }
    public void deleteCar(String carName)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Delete Car");
        alertDialog.setMessage("\nAre you sure to delete this car?");
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "yes", (dialog, which) -> {
            mydb.deleteCar(carName);
            alertDialog.dismiss();
            Intent intent = new Intent(context, context.getClass());
            context.startActivity(intent);
            ((Activity)context).finish();
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "no", (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();
    }
}
