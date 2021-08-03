package com.example.l3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    int counter=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_login);

        final Button loginbutton = findViewById(R.id.button2);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                final String signedUsername = bundle.getString("Username");
                final String signedPassword = bundle.getString("Password");

                EditText loginUsr = findViewById(R.id.editTextTextPersonName3);
                EditText loginPsd = findViewById(R.id.editTextTextPersonName4);

                String userName = loginUsr.getText().toString().trim();
                String passWord = loginPsd.getText().toString().trim();

                if(userName.equals(signedUsername) && passWord.equals(signedPassword)){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                }
                else
                {
                    counter++;
                    if(counter==1){
                        Toast.makeText(LoginActivity.this,"Invalid credentials,2 attempts left",Toast.LENGTH_LONG).show();
                    }
                    else if(counter==2){
                        Toast.makeText(LoginActivity.this,"Invalid credentials,1 attempt left",Toast.LENGTH_LONG).show();
                    }
                    else if(counter==3){
                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                        loginbutton.setEnabled(false);
                    }
                }
            }
        });
    }
}
