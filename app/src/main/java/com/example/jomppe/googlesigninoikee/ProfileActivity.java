package com.example.jomppe.googlesigninoikee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth != null && firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(this, FireBsSignIn.class));
//        }



        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView)findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Tervetuloa " +user.getEmail());


        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent intentin = new Intent(ProfileActivity.this, FireBsSignIn.class);
                ProfileActivity.this.startActivity(intentin);
            }
        });

    }
}
