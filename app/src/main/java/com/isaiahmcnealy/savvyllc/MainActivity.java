package com.isaiahmcnealy.savvyllc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;

    private Button btnEditProfile;
    private Button btnSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fStore = FirebaseFirestore.getInstance();   // Initialize Firestore
        mAuth = FirebaseAuth.getInstance();         // Initialize Firebase Auth

        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnSignout = findViewById(R.id.btnSignout);

        // Action: Navigate to EditProfileActivity
//        btnEditProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "Navigating to edit profile");
//                Toast.makeText(MainActivity.this, "Navigating to edit profile", Toast.LENGTH_SHORT).show();
////                goToEditProfile();
//
//            }
//        });
//
//        // Action: Sign out current user
//        btnSignout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "User signing out");
//                Toast.makeText(MainActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
//                FirebaseAuth.getInstance().signOut();
////                goToLogin();
//            }
//        });
    }

//    private void goToEditProfile() {
//        Intent i = new Intent(this, LoginActivity.class);
//        startActivity(i);
//    }
//
//    private void goToLogin() {
//        Intent i = new Intent(this, LoginActivity.class);
//        startActivity(i);
//    }
}
