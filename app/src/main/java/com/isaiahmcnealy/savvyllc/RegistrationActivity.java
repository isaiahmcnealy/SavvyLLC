package com.isaiahmcnealy.savvyllc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG = "RegistrationForm";

    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private String userId;

    private EditText etName;
    private EditText etMajor;
    private EditText etUniversity;
    private EditText etAbout;


    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        // connect Firebase Objects
        fStore = FirebaseFirestore.getInstance();       // Initialize Firestore
        mAuth = FirebaseAuth.getInstance();             // initialize auth


        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etUniversity = findViewById(R.id.etUniversity);
        etAbout = findViewById(R.id.etAbout);
        btnSubmit = findViewById(R.id.btnSubmit);

        userId = mAuth.getCurrentUser().getUid();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String major = etMajor.getText().toString().trim();
                String university = etUniversity.getText().toString().trim();
                String about = etAbout.getText().toString().trim();

                // create database using new user's unique id
                DocumentReference documentReference = fStore.collection("users").document(userId);
                Map<String,Object> user_map = new HashMap<>();
                user_map.put("name", name);
                user_map.put("major", major);
                user_map.put("university", university);
                user_map.put("about", about);
                documentReference.set(user_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "btnSubmit: user profile created for " + userId);
                    }
                });

                // navigate to next activity
                goToMainActivity();
                Toast.makeText(RegistrationActivity.this, "Profile sucessfully created", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // use intent system to navigate to Main activity
    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
