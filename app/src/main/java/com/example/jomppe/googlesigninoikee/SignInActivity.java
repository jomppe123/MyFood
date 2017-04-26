package com.example.jomppe.googlesigninoikee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{


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
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        googleSignIn = (SignInButton)findViewById(R.id.sign_in_button);
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        signIn();
            }
        });


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
                if (v == textViewSignin){
                    //will open login activity here
                }
            }
        });


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();

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
                            Toast.makeText(SignInActivity.this, "Rekisteröinti onnistui", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignInActivity.this, "Rekisteröinti epäonnistui... Yritä uudelleen!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent3 = new Intent(SignInActivity.this, MyFood.class);
        SignInActivity.this.startActivity(intent3);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            Intent intent2 = new Intent(SignInActivity.this, MyFood.class);
            SignInActivity.this.startActivity(intent2);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            updateUI(true);

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin)
    {
        if (isLogin)
        {
        }
        else
        {
        }

    }





}

