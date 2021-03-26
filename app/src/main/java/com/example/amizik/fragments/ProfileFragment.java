package com.example.amizik.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.amizik.LoginActivity;
import com.example.amizik.R;
import com.example.amizik.SignupActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    GoogleSignInClient mGoogleSignInClient;

    private boolean isSignedInwithGmail;
    private boolean isSignedUpwithGmail;

    private Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                mAuth = FirebaseAuth.getInstance();

        btnLogout = view.findViewById(R.id.btnLogout);
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
        Toast.makeText(getActivity(), "Signed out successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}