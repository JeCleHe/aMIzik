package com.example.amizik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView username;
    TextView email;

    private boolean isSignedInwithGmail;
    private boolean isSignedUpwithGmail;

    private Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //to change the toolbar's color
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        username =findViewById(R.id.tvUsername);
        email = findViewById(R.id.tvEmailAddress);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser().getEmail().equals("charly@gmail.com")) {

            username.setText("Admin:");

        } else if(mAuth.getCurrentUser().getEmail().equals("hergy@gmail.com")){

            username.setText("Admin:");

        } else if(mAuth.getCurrentUser().getEmail().equals("cleevenscharlemagne@gmail.com")){

            username.setText("Admin:");

        } else {

            username.setText("User:");

        }

        email.setText(mAuth.getCurrentUser().getEmail());

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser()!= null){
                    mAuth.signOut();
                    goBackToLoginActivity();
                }
            }
        });
    }

    public void goBackToLoginActivity(){
        Toast.makeText(ProfileActivity.this, "Signed out successfully", Toast.LENGTH_LONG).show();
        Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(i);
        this.finish();
    }

}