package com.example.CarPay.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CarPay.DataBase.DataBase;
import com.example.CarPay.model.Customer;
import com.example.CarPay.utility.CheckEditTextInput;
import com.example.CarPay.utility.EditTextProperties;
import com.example.CarPay.R;

public class SignUpActivity extends AppCompatActivity {
    EditText username,email,pass,confirmPass;
    Button signup;
    DataBase mydb=new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username=findViewById(R.id.username_ET_signUP);
        email=findViewById(R.id.email_ET_signUp);
        pass=findViewById(R.id.pass_ET_signUp);
        confirmPass=findViewById(R.id.confirm_pass_ET);
        signup=findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextInput check = new CheckEditTextInput(SignUpActivity.this);
                if (check.validInput(username) && check.validInput(email) && check.validInput(pass) && check.validInput(confirmPass)) {
                    String userName = username.getText().toString().trim();
                    String userEmail = email.getText().toString().trim();
                    String userPass = pass.getText().toString().trim();
                    String confirmP = confirmPass.getText().toString().trim();

                    if (!userPass.equals(confirmP)) {
                        confirmPass.setError("Those passwords didn't match");
                        return;
                    }
                    mydb.insertCustomerData(new Customer(userName, userEmail, userPass));
                    Toast.makeText(SignUpActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    EditTextProperties.deleteText(username, email, pass, confirmPass);
                    finish();
                }
            }
        });

    }
}