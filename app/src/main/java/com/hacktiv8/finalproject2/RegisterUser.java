package com.hacktiv8.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    private EditText etNamaUser, etNoHp, etEmail, etPassword;

    private Button btnRegister, btnLogin;
    Boolean valid = true;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        etNamaUser = findViewById(R.id.namaUser);
        etNoHp = findViewById(R.id.noHpUser);
        etEmail = findViewById(R.id.loginUser);
        etPassword = findViewById(R.id.passwordUser);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);





        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(etNamaUser);
                checkField(etEmail);
                checkField(etNoHp);
                checkField(etPassword);

                if (valid) {
                    //Add data staff method
                    mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            Toast.makeText(RegisterUser.this, "Account Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = firebaseFirestore.collection("Users").document(firebaseUser.getUid());
                            Map<String, Object> staffInfo = new HashMap<>();
                            staffInfo.put("Nama User", etNamaUser.getText().toString());
                            staffInfo.put("Email User", etEmail.getText().toString());
                            staffInfo.put("No HP", etNoHp.getText().toString());

                            staffInfo.put("isUser", "3");
                            df.set(staffInfo);

                            startActivity(new Intent(getApplicationContext(), LoginUser.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterUser.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    public boolean checkField (EditText textField) {
        if(textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        setContentView(R.layout.activity_register_user);
//
//        etNamaUser = findViewById(R.id.namaUser);
//        etNoHp = findViewById(R.id.noHpUser);
//        etEmail = findViewById(R.id.loginUser);
//        etPassword = findViewById(R.id.passwordUser);
//        btnLogin = findViewById(R.id.btn_login);
//        btnRegister = findViewById(R.id.btn_register);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        btnLogin.setOnClickListener(v -> {
//            finish();
//        });
//
//        btnRegister.setOnClickListener(v -> {
//            if (etNoHp.getText().length()>0 && etEmail.getText().length()>0 && etPassword.getText().length()>0) {
//                register(etNoHp.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
//            }else {
//                Toast.makeText(getApplicationContext(),"Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void register (String nohp, String email, String password){
//        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful() && task.getResult().getUser()!=null) {
//                    FirebaseUser firebaseUser = task.getResult().getUser();
//                    Toast.makeText(RegisterUser.this, "Account Created", Toast.LENGTH_SHORT).show();
//                    DocumentReference df = firebaseFirestore.collection("Users").document(firebaseUser.getUid());
//                    Map<String, Object> userInfo = new HashMap<>();
//                    userInfo.put("Nama User", etNamaUser.getText().toString());
//                    userInfo.put("Email User", etEmail.getText().toString());
//                    userInfo.put("No HP", etNoHp.getText().toString());
//
//                    userInfo.put("isUser", "3");
//                    df.set(userInfo);
//                    if (firebaseUser !=null){
//                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
//                                .setDisplayName(nohp)
//                                .build();
//                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                reload();
//                            }
//                        });
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Register gagal!", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void reload(){
//        startActivity(new Intent(getApplicationContext(), LoginUser.class));
//    }
//    @Override
//    public void onStart(){
//        super.onStart();
//        FirebaseUser currentuser = mAuth.getCurrentUser();
//        if (currentuser != null){
//            reload();
//        }
//    }
}