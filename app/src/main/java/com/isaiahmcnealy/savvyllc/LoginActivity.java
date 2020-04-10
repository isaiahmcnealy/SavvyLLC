package com.isaiahmcnealy.savvyllc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    private String userId;

    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fStore = FirebaseFirestore.getInstance();   // Initialize Firestore
        mAuth = FirebaseAuth.getInstance();         // Initialize Firebase Auth

        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Action: Create new account with email and password.
        // Action: Navigate to RegistrationActivity.
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Register button clicked");
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                registerUser(email, password);
            }
        });

        // Action: Login user
        // Action: Navigate to MainActivity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnClick login button pressed");
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                loginUser(email, password);
            }
        });

    }

    // Action: On start of activity check if user is still logged in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        checkCurrentUser(currentUser);
    }

    // Action: Login user with email and password
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
                            goToEditProfileActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Action: Register new user
    private void registerUser(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "User created", Toast.LENGTH_SHORT).show();

                            // On successfull signup navigate to RegistrationActivity
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // create database using new user's unique id
                            userId = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userId);
                            Map<String,Object> user_map = new HashMap<>();
                            user_map.put("email", email);
                            documentReference.set(user_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.i(TAG, "onSuccess: user profile created for " + userId);
                                }
                            });

                            // navigate to next activity
                            goToRegisterActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    // use intent system to navigate to Main activity
    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    // use intent system to navigate to RegistrationForm activity
    private void goToRegisterActivity() {
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);

    }

    // use intent system to navigate to EditProfile activity
    private void goToEditProfileActivity() {
        Intent i = new Intent(this, EditProfileActivity.class);
        startActivity(i);

    }

    // if user is not null display a toast and navigate to main activity
    private void checkCurrentUser(FirebaseUser currentUser) {
        if (currentUser != null) { // if user is not null display a toast and navigate to
            Log.i(TAG,"Current User: " + currentUser.getUid());
            Toast.makeText(LoginActivity.this, "Member is still signed in", Toast.LENGTH_SHORT).show();
            goToMainActivity();
        }
    }

}

