/**
 *
 *
 * This class is to serve as a backup to EditprofileActivity while testing
 * Please do not alter
 *
 *
 *
 */








//package com.isaiahmcnealy.savvyllc;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.isaiahmcnealy.savvyllc.models.Member;
//
//public class EditProfileActivity extends AppCompatActivity {
//
//    public static final String TAG = "EditProfileActivity";
//
//    DatabaseReference DbRef;
//
//    Member member;
//
//    private EditText etFullName;
//    private EditText etMajor;
//    private EditText etUniversity;
//    private EditText etAboutMe;
//    private TextView tvEmail;
//    private Button btnUploadResume;
//    private Button btnCancel;
//    private Button btnSubmit;
//    private Button btnSignout;
//    private ImageView ivProfileImage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile);
//
//        // connect layout objects to java file
//        etFullName = findViewById(R.id.et_FullName);
//        etMajor = findViewById(R.id.editText_Major);
//        etUniversity = findViewById(R.id.editText_University);
//        etAboutMe = findViewById(R.id.editText_AboutMe);
//        btnUploadResume = findViewById(R.id.button_UploadResume);
//        btnCancel = findViewById(R.id.button_Cancel);
//        btnSubmit = findViewById(R.id.button_Submit);
//        btnSignout = findViewById(R.id.btnSignout);
////        ivProfileImage = findViewById(R.id.imageView_ProfileImage);
//
//        member = new Member();
//
//        DbRef = FirebaseDatabase.getInstance().getReference().child("Member");
//
//        // read current user data from database
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = etFullName.getText().toString().trim();
//                String major = etMajor.getText().toString().trim();
//                String university = etUniversity.getText().toString().trim();
//                String about = etAboutMe.getText().toString().trim();
//                //TODO: set user profile image
////                File? profileImage = ????
//
//                member.setUsername(etFullName.getText().toString().trim());
//                member.setMajor(etMajor.getText().toString().trim());
//                member.setUniversity(etUniversity.getText().toString().trim());
//                member.setAbout(etAboutMe.getText().toString().trim());
//
//                DbRef.push().setValue(member);
//
//                Toast.makeText(EditProfileActivity.this, "Profile sucessfully updated", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // TODO: move this to toolbar in order to logout from any page
//        btnSignout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "Member signing out");
//                Toast.makeText(EditProfileActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
//                FirebaseAuth.getInstance().signOut();
//                goToLogin();
//
//            }
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "returning to main activity");
//                goToMainActivity();
//            }
//        });
//
//
//
//    }
//
//    public void updateProfile(){
//        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference mDbRef = mDatabase.getReference();
//
//    }
//
//    private void goToMainActivity() {
//        Intent i = new Intent(this, MainActivity.class);
//        startActivity(i);
//        finish();
//
//    }
//
//    private void goToLogin() {
//        Intent i = new Intent(this, FirebaseUIActivity.class);
//        startActivity(i);
//        finish();
//    }
//}
