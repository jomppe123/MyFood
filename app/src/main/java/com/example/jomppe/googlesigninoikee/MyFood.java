package com.example.jomppe.googlesigninoikee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyFood extends AppCompatActivity {

    private TextView liha;
    private TextView kala;
    private TextView peruna;

    private Button lihabtn;
    private Button kalabtn;
    private Button perunabtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_food);

        liha = (TextView)findViewById(R.id.lihatv);
        kala = (TextView)findViewById(R.id.kalatv);
        peruna = (TextView)findViewById(R.id.perunatv);

        lihabtn = (Button) findViewById(R.id.LihaButton);
        kalabtn = (Button) findViewById(R.id.KalaButton);
        perunabtn = (Button)findViewById(R.id.PerunaButton);


        lihabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liha.setText("Lihahaha");
            }
        });

        kalabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kala.setText("kallahaha");

            }
        });


        perunabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peruna.setText("perunanhah");

            }
        });



    }
}
