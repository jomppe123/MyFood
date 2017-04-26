package com.example.jomppe.googlesigninoikee;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class GoogleFBLogIn extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{


    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private Button buttonEkaActivity;
    private SignInButton googleSignIn;
    private TextView textViewSignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlefblogin);


        googleSignIn = (SignInButton)findViewById(R.id.sign_in_button);
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        buttonEkaActivity = (Button) findViewById(R.id.buttonEkaActivity);
        buttonEkaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Logini = new Intent(GoogleFBLogIn.this, FireBsSignIn.class);
                GoogleFBLogIn.this.startActivity(Logini);
            }
        });

        textViewSignin = (TextView)findViewById(R.id.textViewSigninEka);
        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //will open login activity here
                Intent Logini = new Intent(GoogleFBLogIn.this, FireBsSignIn.class);
                GoogleFBLogIn.this.startActivity(Logini);

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

        Intent intent3 = new Intent(GoogleFBLogIn.this, MyFood.class);
        GoogleFBLogIn.this.startActivity(intent3);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            Intent intent2 = new Intent(GoogleFBLogIn.this, MyFood.class);
            GoogleFBLogIn.this.startActivity(intent2);
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

