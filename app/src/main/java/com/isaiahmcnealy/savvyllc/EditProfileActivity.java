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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;
import com.isaiahmcnealy.savvyllc.models.Member;

import javax.annotation.Nullable;

public class EditProfileActivity extends AppCompatActivity {

    public static final String TAG = "EditProfileActivity";

    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private String userId;

    private EditText etFullName;
    private EditText etMajor;
    private EditText etUniversity;
    private EditText etAboutMe;
    private TextView tvEmail;
    private Button btnUploadResume;
    private Button btnCancel;
    private Button btnSubmit;
    private Button btnSignout;
    private ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // connect Firebase Objects
        fStore = FirebaseFirestore.getInstance();       // Initialize Firestore
        mAuth = FirebaseAuth.getInstance();             // initial

        // connect layout objects to java file
        etFullName = findViewById(R.id.et_FullName);
        etMajor = findViewById(R.id.editText_Major);
        etUniversity = findViewById(R.id.editText_University);
        etAboutMe = findViewById(R.id.editText_AboutMe);
        btnUploadResume = findViewById(R.id.button_UploadResume);
        btnCancel = findViewById(R.id.button_Cancel);
        btnSubmit = findViewById(R.id.button_Submit);
        btnSignout = findViewById(R.id.btnSignout);
        ivProfileImage = findViewById(R.id.imageView_ProfileImage);
        tvEmail = findViewById(R.id.tvEmail);

        userId = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                tvEmail.setText((documentSnapshot.getString("email")));
                etFullName.setText((documentSnapshot.getString("name")));
                etMajor.setText((documentSnapshot.getString("major")));
                etAboutMe.setText((documentSnapshot.getString("about")));
                etUniversity.setText((documentSnapshot.getString("university")));
            }
        });


        // read current user data from database
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFullName.getText().toString().trim();
                String major = etMajor.getText().toString().trim();
                String university = etUniversity.getText().toString().trim();
                String about = etAboutMe.getText().toString().trim();
                //TODO: set user profile image
//                File? profileImage = ????

                Toast.makeText(EditProfileActivity.this, "Profile sucessfully updated", Toast.LENGTH_SHORT).show();
            }
        });

        // TODO: move this to toolbar in order to logout from any page
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Member signing out");
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

//    private void writeNewUser(String userId, String name, String major, String university, String about) {
//        fStore.child("users").child(userId).child("name").setValue(name);
//        fStore.child("users").child(userId).child("major").setValue(major);
//        fStore.child("users").child(userId).child("university").setValue(university);
//        fStore.child("users").child(userId).child("about").setValue(about);
//    }

    public void updateProfile(){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDbRef = mDatabase.getReference();

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


// TODO: create a registration form
// TODO: create a preferences activity
// TODO: add ability to update datbase information
// TODO: navigate to registration form when register is selected
// TODO: add a signout button on the main activity
// TODO: add a menu bar
// TODO: add activities to menu bar navigation