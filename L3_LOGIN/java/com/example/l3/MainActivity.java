package com.example.l3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signupbutton = findViewById(R.id.button);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userText = findViewById(R.id.editTextTextPersonName);
                EditText password = findViewById(R.id.editTextTextPersonName2);

                String userName = userText.getText().toString().trim();
                String passWord = password.getText().toString().trim();

                if(validatePassword(passWord)){
                    Toast.makeText(MainActivity.this,"Successful Signup",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);

                    intent.putExtra("Username",userName);
                    intent.putExtra("Password",passWord);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean validatePassword(String passwd)
    {
        boolean isvalid=false;
        int passwordLength=8;

        for(int i=0;i<passwd.length();i++){
            char ch=passwd.charAt(i);
            if(Character.isUpperCase(ch)||Character.isLowerCase(ch)||Character.isDigit(ch)||Character.isAlphabetic(ch)||passwd.length()>=passwordLength|| ch=='@'||ch=='!'||ch == '#'||ch =='$'||ch=='_')
            {
                isvalid=true;
            }
            else
            {
                isvalid=false;
            }
        }
        return isvalid;
    }
}