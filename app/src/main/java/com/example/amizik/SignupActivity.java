package com.example.amizik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText fullName, email, password, confirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password =findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPasword);
        btnRegister = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
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

                            Intent i = new Intent(SignupActivity.this, NavigationActivity.class);
                            finishAffinity();
                            startActivity(i);
                        } else{
                            Toast.makeText(SignupActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}