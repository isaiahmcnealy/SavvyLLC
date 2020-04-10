package com.isaiahmcnealy.savvyllc;

// TODO: create a preferences activity
// TODO: add ability to UPDATE database information in edit user
// TODO: navigate to registration form when register is selected
// TODO: add a menu bar
// TODO: add activities to menu bar navigation
// TODO: move signout to toolbar in order to logout from any page
// TODO: set user profile image
// TODO: update information in database
// Database filepath = "users"/userId/about || email || major || name || university

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

        // check if user is currently signed in
        if (mAuth.getCurrentUser() != null) {
            goToMainActivity();
            finish();
        }

        // connect layout objects
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);


        // Action: Create new account with email and password.
        // Action: Navigate to RegistrationActivity.
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                loginUser(email, password);
            }
        });

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
                            goToMainActivity();
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
        Log.i(TAG, "registerUser - creating new user with email and password");
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
                            Log.i(TAG, "RegisterUser > CreateUser > onComplete - calling goToRegisterActivity");
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


}

