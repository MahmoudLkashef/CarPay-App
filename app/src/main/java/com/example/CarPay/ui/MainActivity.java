package com.example.CarPay.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.dialogs.ForgetPasswordDialog;
import com.example.CarPay.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextView forgetPass;
    TextInputEditText email,pass;
    Button login,signUp;
    DataBase mydb=new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forgetPass=findViewById(R.id.forget_TV);
        email=findViewById(R.id.email_ET);
        pass=findViewById(R.id.pass_ET);
        login=findViewById(R.id.login_btn);
        signUp=findViewById(R.id.signUp_btn);

        forgetPass.setOnClickListener(view -> {
            ForgetPasswordDialog dialog=new ForgetPasswordDialog(MainActivity.this);
            dialog.ForgetPasswordDialog();
        });

        signUp.setOnClickListener(view -> {
            Intent intent =new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(view -> {
            if((email.getText().toString()).equalsIgnoreCase("admin"))
            {
                Intent intent =new Intent(MainActivity.this, AdminPanel.class);
                startActivity(intent);
            }
            else
            {
                String customerEmail=email.getText().toString().trim();
                String customerPass=pass.getText().toString().trim();
                if(mydb.validEmail(customerEmail,customerPass)) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("customerEmail",customerEmail);
                    startActivity(intent);
                }
                else email.setError("Invalid email or password");
            }
        });
    }
}