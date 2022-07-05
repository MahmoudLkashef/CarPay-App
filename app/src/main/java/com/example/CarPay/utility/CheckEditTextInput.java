package com.example.CarPay.utility;

import android.content.Context;
import android.widget.EditText;

import com.example.CarPay.DataBase.DataBase;

public class CheckEditTextInput {
Context context;
DataBase mydb=new DataBase(context);


    public CheckEditTextInput(Context context) {
        this.context = context;
    }
    public boolean isEmpty(EditText editText)
    {
        if((editText.getText().toString().trim()).equals(""))return true;
        return false;
    }
    public boolean validInput(EditText editText)
    {
        if(isEmpty(editText))
        {
            editText.setError("please fill the field");
            return false;
        }
        return true;
    }

}
