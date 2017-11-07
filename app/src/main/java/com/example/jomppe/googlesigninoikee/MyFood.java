package com.example.jomppe.googlesigninoikee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MyFood extends AppCompatActivity {

    //textViews

    //ImageButtons
    private ImageButton imgbtnSettings;

    //Buttons



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_food);

        //BottomBarNAvigation onClickListener
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_add:
                        Toast.makeText(MyFood.this, "Action add clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_edit:
                        Toast.makeText(MyFood.this, "Action edit clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_remove:
                        //will open login activity here
                        Intent intentin = new Intent(MyFood.this, ProfileActivity.class);
                        MyFood.this.startActivity(intentin);
                        break;
                }
                return true;
            }
        });


        imgbtnSettings = (ImageButton)findViewById(R.id.imgbtnSettings);
        imgbtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });






    }
}
