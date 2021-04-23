package com.example.amizik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amizik.utils.AdminUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.amizik.R.*;

public class LoginActivity extends AppCompatActivity {
    private static int RC_SIGN_IN = 1;

    private ImageView imv;
    private EditText email , pass;
    private Button btnc;
    private Button signupBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(id.email);
        pass  = (EditText) findViewById(id.pass);
        btnc  = (Button) findViewById(id.btnc) ;
        signupBtn = (Button) findViewById(id.signupBtn);

        if(mAuth.getCurrentUser() != null){
            Intent i = new Intent(getApplicationContext(), OptionActivity.class);
            finishAffinity();
            startActivity(i);
        }

        signupBtn.setOnClickListener(new View.OnClickListener() {
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
    }


    private void userLogin(){
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
                    Intent i = new Intent(LoginActivity.this, OptionActivity.class);
                    finishAffinity();
                    startActivity(i);
                } else{
                    Toast.makeText(LoginActivity.this, "Error ! The password or the username is incorrect.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}