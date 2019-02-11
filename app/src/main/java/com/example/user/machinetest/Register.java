package com.example.user.machinetest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Register extends AppCompatActivity {
    EditText Naame, Emaail, Dateofbirthh, Agee, passwd;
    RelativeLayout r3;

    String color="";
    private Calendar myCalendar;
    private int Year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Naame = findViewById(R.id.regname);
        Emaail = findViewById(R.id.regeml);
        Dateofbirthh = findViewById(R.id.dob);
        Agee = findViewById(R.id.regage);
        passwd = findViewById(R.id.regpswd);
        SharedPreferences shred = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        String clr1 = shred.getString("key", null);
        r3 = findViewById(R.id.relay3);
        if (clr1.equals("red")) {
            r3.setBackgroundResource(R.color.Red);
        } else if (clr1.equals("blue")) {
            r3.setBackgroundResource(R.color.Blue);
        } else if (clr1.equals("green")) {
            r3.setBackgroundResource(R.color.Green);
        }

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };
        Dateofbirthh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                new DatePickerDialog(Register.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }


    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        Dateofbirthh.setText(sdf.format(myCalendar.getTime()));
        Agee.setText(Integer.toString(calculateAge(myCalendar.getTimeInMillis())));

    }

    int calculateAge(long date) {

        Date c = Calendar.getInstance().getTime();

        String myFormat = "dd/MM/yyyy";

        SimpleDateFormat df = new SimpleDateFormat(myFormat, Locale.US);
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        String formattedDate = df.format(c);
        Toast.makeText(this, "" + formattedDate, Toast.LENGTH_LONG).show();
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)) {
            age--;
        }
        return age;
    }

    public void regclick(View view) {

        String regName = Naame.getText().toString();
        String regEmail = Emaail.getText().toString();
String regdob=Dateofbirthh.getText().toString();
        String regAge = Agee.getText().toString();
        String regPassword = passwd.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (regName.isEmpty())

        {

            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Naame.setError("Enter Name");
        } else if (regEmail.isEmpty()) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Emaail.setError("Enter Email");

        } else if (regAge.isEmpty()) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Agee.setError("Enter age");
        } else if (regPassword.isEmpty()) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            passwd.setError("Enetr password");

        } else if (regEmail.matches(emailPattern)) {
            Toast.makeText(this, "succesfully registered ", Toast.LENGTH_SHORT).show();

            Intent in = new Intent(Register.this, Login.class);
            SharedPreferences shred1 = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
            SharedPreferences.Editor rt = shred1.edit();
            rt.putString("Emailkey", regEmail);
            rt.putString("Namekey", regName);
rt.putString("dobkey",regdob);
            rt.putString("Agekey", regAge);
            rt.putString("Passwordkey", regPassword);
            rt.commit();

            startActivity(in);
        } else {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }
      /*  SharedPreferences shred1 = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        String clr1 = shred1.getString("key", null);
        r3= findViewById(R.id.relay2);
        if (clr1.equals("red")) {
            r3.setBackgroundResource(R.color.Red);
        } else if (clr1.equals("blue")) {
            r3.setBackgroundResource(R.color.Blue);
        } else if (clr1.equals("green")) {
            r3.setBackgroundResource(R.color.Green);
        }*/

    }
}
