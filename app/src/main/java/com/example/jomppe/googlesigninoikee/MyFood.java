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
    private TextView liha;
    private TextView kala;
    private TextView peruna;

    //ImageButtons
    private ImageButton imgbtnSettings;

    //Buttons
    private Button lihabtn;
    private Button kalabtn;
    private Button perunabtn;


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
                        Toast.makeText(MyFood.this, "Action remoce clicked!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        liha = (TextView) findViewById(R.id.lihatv);
        kala = (TextView) findViewById(R.id.kalatv);
        peruna = (TextView) findViewById(R.id.perunatv);

        imgbtnSettings = (ImageButton)findViewById(R.id.imgbtnSettings);
        imgbtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

        lihabtn = (Button) findViewById(R.id.LihaButton);
        kalabtn = (Button) findViewById(R.id.KalaButton);
        perunabtn = (Button) findViewById(R.id.PerunaButton);


        lihabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liha.setText("Lihahaha");
                kala.setVisibility(View.INVISIBLE);
                peruna.setVisibility(View.INVISIBLE);
                liha.setVisibility(View.VISIBLE);
            }
        });

        kalabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kala.setText("kallahaha");
                liha.setVisibility(View.INVISIBLE);
                peruna.setVisibility(View.INVISIBLE);
                kala.setVisibility(View.VISIBLE);

            }
        });


        perunabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peruna.setText("perunanhah");
                kala.setVisibility(View.INVISIBLE);
                liha.setVisibility(View.INVISIBLE);
                peruna.setVisibility(View.VISIBLE);

            }
        });


    }
}
