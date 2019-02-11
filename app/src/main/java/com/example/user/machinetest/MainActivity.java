package com.example.user.machinetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button ok, redbtn, bluebtn, grnbtn;
    String color = "";
    RelativeLayout r1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redbtn = findViewById(R.id.red);
        bluebtn = findViewById(R.id.blue);
        grnbtn = findViewById(R.id.green);
        ok = findViewById(R.id.okk);
        r1 = findViewById(R.id.ly1);

    }
        public void redclick (View view){

            r1.setBackgroundResource(R.color.Red);
            color = "red";
        }

        public void blueclick (View view){

            r1.setBackgroundResource(R.color.Blue);
            color = "blue";
        }

        public void grnclick (View view){

            r1.setBackgroundResource(R.color.Green);
            color = "green";
        }

    public void okclick(View view) {


        Intent in = new Intent(MainActivity.this, Login.class);
        if (color.equals("")) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, Login.class);

            SharedPreferences shred = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
            SharedPreferences.Editor edit = shred.edit();
            edit.putString("key", color);
            edit.apply();
            startActivity(intent);

        }
    }
}
