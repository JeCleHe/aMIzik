package com.example.amizik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

public class SignupActivity extends AppCompatActivity {
    private static int RC_SIGN_IN = 1;

    private FirebaseAuth mAuth;
    private EditText fullName, email, password, confirmPassword;
    private Button btnRegister;
    private GoogleSignInButton signUpButton;

    public  static GoogleSignInClient mGoogleSignInClient;
    public static boolean isconnectedwithGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        isconnectedwithGoogle = false;
        mAuth = FirebaseAuth.getInstance();

        signUpButton = findViewById(R.id.signup_with_google);
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password =findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPasword);
        btnRegister = findViewById(R.id.register);

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        if(mAuth.getCurrentUser() != null){
            Intent i = new Intent(getApplicationContext(), OptionActivity.class);
            startActivity(i);
            finish();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = email.getText().toString().trim();
                String strPassword = password.getText().toString().trim();
                String strConfirmpassword = confirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(strEmail)){
                    email.setError("Email requis");
                    return;
                }

                if(TextUtils.isEmpty(strPassword)){
                    password.setError("Mot de passe requis");
                    return;
                }

                if(strPassword.length() < 8){
                    password.setError("Le mot de passe doit a voir au moins 8 charcteres");
                    return;
                }

                if(TextUtils.isEmpty(strConfirmpassword)){
                    confirmPassword.setError("Mot de passe requis");
                    return;
                }

                if(strPassword.equals(strConfirmpassword) == false){
                    confirmPassword.setError("Mot de passe different");
                    return;
                }

                // register the user to firebase
                mAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                            if(mAuth.getCurrentUser() != null){
                                Toast.makeText(SignupActivity.this, "User is sign in", Toast.LENGTH_LONG).show();
                            }

                            Intent i = new Intent(SignupActivity.this, OptionActivity.class);
                            finishAffinity();
                            startActivity(i);
                        } else{
                            Toast.makeText(SignupActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(SignupActivity.this, "Signed in succesfully", Toast.LENGTH_SHORT).show();
            firebaseGoogleAuth(acc);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            Toast.makeText(SignupActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
            firebaseGoogleAuth(null);
            Log.d("message ", e.toString());
        }
    }

    private void firebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "Succesfull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    isconnectedwithGoogle = true;
                    Intent i = new Intent(SignupActivity.this, OptionActivity.class);
                    finishAffinity();
                    startActivity(i);
                } else{
                    Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fbUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null){
            String personFullname = account.getDisplayName();
            String personEmail = account.getEmail();
            Toast.makeText(SignupActivity.this, personFullname + " Email: " + personEmail, Toast.LENGTH_SHORT).show();
        }
    }
}