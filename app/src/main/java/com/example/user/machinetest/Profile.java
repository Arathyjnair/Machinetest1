package com.example.user.machinetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    TextView ProName, ProEmail, Prodob, ProAge, ProPassword;
    ImageView image;
RelativeLayout r4;
    static int CAM_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences shred = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        String clr1 = shred.getString("key", null);
        r4 = findViewById(R.id.relay4);
        if (clr1.equals("red")) {
            r4.setBackgroundResource(R.color.Red);
        } else if (clr1.equals("blue")) {
            r4.setBackgroundResource(R.color.Blue);
        } else if (clr1.equals("green")) {
            r4.setBackgroundResource(R.color.Green);
        }
        image = findViewById(R.id.imageView);
        Button bt = (Button) findViewById(R.id.btnShow);
        registerForContextMenu(bt);
        ProName = (TextView) findViewById(R.id.pronme);
        ProEmail = (TextView) findViewById(R.id.proemail);
        Prodob = findViewById(R.id.prodateofbirth);
        ProAge = (TextView) findViewById(R.id.proag);
        ProPassword = (TextView) findViewById(R.id.propassword);
        SharedPreferences shred1= getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        String hn = shred1.getString("Namekey", null);
        String ab = shred1.getString("Emailkey", null);
        String yu = shred1.getString("dobkey", null);
        String ji = shred1.getString("Agekey", null);
        String bh = shred1.getString("Passwordkey", null);
        ProName.setText(hn);
        ProEmail.setText(ab);
        Prodob.setText(yu);
        ProAge.setText(ji);
        ProPassword.setText(bh);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(" Menu");
        menu.add(0, v.getId(), 0, "Logout");

    }

    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Logout") {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {


            return false;
        }
        return true;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cam) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAM_REQUEST);
        }


        return super.onOptionsItemSelected(item);

    }

    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == CAM_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(mphoto);

        }


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }

    public void oncalculator(View view) {
        Intent in = new Intent(Profile.this, Calculator.class);
        startActivity(in);
    }



}
