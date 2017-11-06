package com.example.jomppe.googlesigninoikee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class FireBsSignIn extends AppCompatActivity{


    private Button buttonSignin;
    private EditText editTextEmail;

    private TextView textViewSignup;
    private TextInputEditText editTextPassword;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebssignin);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth != null && firebaseAuth.getCurrentUser() != null){
//            //profile activity here
//            finish();
//            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
//        }



        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (TextInputEditText) findViewById(R.id.editTextPassword);

        buttonSignin = (Button) findViewById(R.id.buttonSignin);
        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    userLogin();

            }
        });

        textViewSignup = (TextView)findViewById(R.id.textViewSignup);
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //will open login activity here
                    Intent intentin = new Intent(FireBsSignIn.this, FireBsSignUp.class);
                    FireBsSignIn.this.startActivity(intentin);                }

        });

    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //Email is empty
            Toast.makeText(this,"Syötä sähköpostiosoite",Toast.LENGTH_SHORT).show();
            //Stopping the function execution further
            return;
        }
        if (TextUtils.isEmpty(password)){
            //Password is empty
            Toast.makeText(this,"Syötä salasana",Toast.LENGTH_SHORT).show();
            //Stopping the function execution further
            return;
        }
        //if validations is ok
        //We will first show a progressDialog

        progressDialog.setMessage("Kirjaudutaan...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            //start profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),MyFood.class));

                        }
                    }
                });




    }




}

