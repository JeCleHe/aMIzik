package com.example.amizik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.amizik.R.*;

public class LoginActivity extends AppCompatActivity {
    private static int RC_SIGN_IN = 100;
    GoogleSignInClient mGoogleSignInClient;

    private ImageView imv;
    private EditText email , pass;
    private Button btnc;
    private TextView signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(layout.activity_login);
        email = (EditText) findViewById(id.email);
        pass  = (EditText) findViewById(id.pass);
        btnc  = (Button) findViewById(id.btnc) ;
        signup = (TextView) findViewById(id.signup);

        if(mAuth.getCurrentUser() != null){
            Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
            finishAffinity();
            startActivity(i);
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();*/

        // Build a GoogleSignInClient with the options specified by gso.
       /* mGoogleSignInClient = GoogleSignIn.getClient(this, gso);*/

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
       /* GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);*/
        // Set the dimensions of the sign-in button.
     /*   SignInButton signInButton = findViewById(id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);*/

        /*signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        })*/;
    }

    /*private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }*/

    /*private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                Toast.makeText(this, "user Email"+personEmail, Toast.LENGTH_SHORT).show();
            }
            // create a new Activity message
            //  startActivity(new Intent(MainActivity.this , Second.class));
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.;
            Log.d("message ", e.toString());
        }
    }*/



    private void userLogin() {
        String strEmail = email.getText().toString().trim();
        String strPassword = pass.getText().toString().trim();

        if(TextUtils.isEmpty(strEmail)){
            email.setError("Email requis");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            email.setError("Email incorrect");
            email.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(strPassword)){
            pass.setError("Mot de passe requis");
            return;
        }

        if(strPassword.length() < 8){
            pass.setError("Le mot de passe doit a voir au moins 8 charcteres");
            return;
        }

        // Authenticate the user
        mAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, NavigationActivity.class);
                    finishAffinity();
                    startActivity(i);
                } else{
                    Toast.makeText(LoginActivity.this, "Error ! The password or the username is incorrect.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
