package com.example.jomppe.googlesigninoikee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class FireBsSignUp extends AppCompatActivity{


    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private Button buttonRegister;
    private SignInButton googleSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebssignup);


//        if (firebaseAuth.getCurrentUser() != null){
//            //profile activity here
//            finish();
//            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
//        }

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == buttonRegister){
                    registerUser();
                }
            }
        });

        textViewSignin = (TextView)findViewById(R.id.textViewSingin);
        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //will open login activity here
                Intent intentin = new Intent(FireBsSignUp.this, FireBsSignIn.class);
                FireBsSignUp.this.startActivity(intentin);

            }
        });

    }

    public void registerUser(){
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

        progressDialog.setMessage("Rekisteröidään käyttäjää...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //user is succesfully registered and logged in
                            //we start the profile activity
                            //right now lets display a toast only
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }else{
                            Toast.makeText(FireBsSignUp.this, "Rekisteröinti epäonnistui... Yritä uudelleen!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}

