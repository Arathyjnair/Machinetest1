package com.example.user.machinetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText Email,Password;
RelativeLayout r2;

String color="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=findViewById(R.id.eml);
        Password=findViewById(R.id.pswd);
        SharedPreferences shred = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        String clr1 = shred.getString("key", null);
        r2 = findViewById(R.id.relay2);
        if (clr1.equals("red")) {
            r2.setBackgroundResource(R.color.Red);
        } else if (clr1.equals("blue")) {
            r2.setBackgroundResource(R.color.Blue);
        } else if (clr1.equals("green")) {
            r2.setBackgroundResource(R.color.Green);
        }


    }


    public void logclick(View view) {

        String Eml = Email.getText().toString();
        String Pswd = Password.getText().toString();
        SharedPreferences shred1=getApplicationContext().getSharedPreferences("pref",MODE_PRIVATE);
        String ab=shred1.getString("Emailkey",null);
        String bh=shred1.getString("Passwordkey",null);

        if((Eml.equals(ab))&&(Pswd.equals(bh)))
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            Intent jk=new Intent(Login.this,Profile.class);
            startActivity(jk);
        }
        else
        {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }

    }



    public void usrclick(View view) {
        Intent in=new Intent(Login.this,Register.class);

        startActivity(in);
    }

}
