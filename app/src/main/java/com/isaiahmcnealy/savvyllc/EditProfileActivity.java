package com.isaiahmcnealy.savvyllc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class EditProfileActivity extends AppCompatActivity {

    public static final String TAG = "EditProfileActivity";

    private TextView tvFullName;
    private EditText etMajor;
    private EditText etUniversity;
    private EditText etAboutMe;
    private Button btnUploadResume;
    private Button btnCancel;
    private Button btnSubmit;
    private Button btnSignout;
    private ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // connect layout objects to java file
        tvFullName = findViewById(R.id.textView_FullName);
        etMajor = findViewById(R.id.editText_Major);
        etUniversity = findViewById(R.id.editText_University);
        etAboutMe = findViewById(R.id.editText_AboutMe);
        btnUploadResume = findViewById(R.id.button_UploadResume);
        btnCancel = findViewById(R.id.button_Cancel);
        btnSubmit = findViewById(R.id.button_Submit);
        btnSignout = findViewById(R.id.btnSignout);
        ivProfileImage = findViewById(R.id.imageView_ProfileImage);

        // read current user data from database

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "User signing out");
                Toast.makeText(EditProfileActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                goToLogin();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "returning to main activity");
                goToMainActivity();
            }
        });



    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }

    private void goToLogin() {
        Intent i = new Intent(this, FirebaseUIActivity.class);
        startActivity(i);
        finish();
    }
}


// Code Outlines below
//
//    // Write a message to the database
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("message");
//
//     myRef.setValue("Hello, World!");
