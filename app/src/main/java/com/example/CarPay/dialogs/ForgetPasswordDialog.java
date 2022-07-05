package com.example.CarPay.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.R;
import com.example.CarPay.utility.CheckEditTextInput;

public class ForgetPasswordDialog {
    Context context;
    public ForgetPasswordDialog(Context context) {
        this.context = context;
    }

    public void ForgetPasswordDialog()
    {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.forget_pass_dialog);
        dialog.setCancelable(false);

        Button send;
        EditText email;
        ImageView close;

        email=dialog.findViewById(R.id.forget_email);
        send=dialog.findViewById(R.id.send_forget);
        close=dialog.findViewById(R.id.close_forget);


        close.setOnClickListener(view -> {
            dialog.dismiss();
        });

        send.setOnClickListener(view -> {
            DataBase mydb=new DataBase(context);
            CheckEditTextInput check=new CheckEditTextInput(context);
            String customerEmail=email.getText().toString().trim();
            if(mydb.validEmail(customerEmail))Toast.makeText(context, "The password is : "+mydb.getCustomerPass(customerEmail), Toast.LENGTH_SHORT).show();
            else email.setError("Invalid email");
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
