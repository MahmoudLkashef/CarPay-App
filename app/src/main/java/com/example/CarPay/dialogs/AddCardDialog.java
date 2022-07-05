package com.example.CarPay.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.CarPay.model.Car;
import com.example.CarPay.model.Card;
import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.R;
import com.example.CarPay.utility.CheckEditTextInput;
import com.example.CarPay.utility.EditTextProperties;

public class AddCardDialog {
    Context context;

    public AddCardDialog(Context context) {
        this.context = context;
    }

    public void AddCardDialog(String customerEmail, Car c)
    {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.payment);
        dialog.setCancelable(false);

        Button done;
        ImageView close;
        EditText cardNum,cvc,date;

        cardNum=dialog.findViewById(R.id.cardNum);
        cvc=dialog.findViewById(R.id.cvc);
        date=dialog.findViewById(R.id.date);
        close=dialog.findViewById(R.id.close_cardDialog);
        done=dialog.findViewById(R.id.done_btn);

        close.setOnClickListener(view -> {
            dialog.dismiss();
        });

        done.setOnClickListener(view -> {
            CheckEditTextInput check=new CheckEditTextInput(context);
            if(check.validInput(cardNum)&&check.validInput(date)&&check.validInput(cvc))
            {
                DataBase mydb=new DataBase(context);
                //write the logic to store card data
                String cardNo=cardNum.getText().toString().trim();
                String cardCVC=cvc.getText().toString().trim();
                String cardDate=date.getText().toString().trim();
                int cash= (int) (Integer.parseInt(c.getPrice())*0.25);
                String cashPay= String.valueOf(cash);
                mydb.insertCustomerCard(customerEmail,new Card(cardNo,cardCVC,cardDate,cashPay));
                Toast.makeText(context, "Done Successfully", Toast.LENGTH_SHORT).show();
                EditTextProperties.deleteText(cardNum,cvc,date);
                dialog.dismiss();
            }

        });
        //Set the background of the dialog's root view to transparent, because Android puts your dialog layout within a root view that hides the corners in your custom layout.
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
