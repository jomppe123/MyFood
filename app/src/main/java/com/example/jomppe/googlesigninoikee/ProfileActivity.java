package com.example.jomppe.googlesigninoikee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;

    private DatabaseReference databaseReference;

    private EditText editTextName, editTextAddress, editTextNumber;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth != null && firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(this, FireBsSignIn.class));
//        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextNumber = (EditText)findViewById(R.id.editTextNumber);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing Views
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        textViewUserEmail = (TextView)findViewById(R.id.textViewUserEmail);
        //Displaying logged in user name
        textViewUserEmail.setText("Tervetuloa " +user.getEmail());

        //Adding listener to buttons

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent intentin = new Intent(ProfileActivity.this, FireBsSignIn.class);
                ProfileActivity.this.startActivity(intentin);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

    }

     private void saveUserInformation(){

         String name = editTextName.getText().toString().trim();
         String address = editTextAddress.getText().toString().trim();
         String numero = editTextNumber.getText().toString().trim();

         UserInformation userInformation = new UserInformation(name, address, numero);

         FirebaseUser user = firebaseAuth.getCurrentUser();

         databaseReference.child(user.getUid()).setValue(userInformation);

         Toast.makeText(this,"Tiedot tallennettiin onnistuneesti!", Toast.LENGTH_LONG).show();

     }






}
