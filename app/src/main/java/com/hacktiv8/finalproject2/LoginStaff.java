package com.hacktiv8.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginStaff extends AppCompatActivity {

    EditText emailStaff, passwordStaff;
    Button btnStaff;
    Boolean valid = true;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_staff);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        emailStaff = findViewById(R.id.input_email);
        passwordStaff = findViewById(R.id.input_password);
        btnStaff = findViewById(R.id.btn_login);

        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(emailStaff);
                checkField(passwordStaff);

                if (valid) {
                    firebaseAuth.signInWithEmailAndPassword(emailStaff.getText().toString(), passwordStaff.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginStaff.this, "Login as Admin succes", Toast.LENGTH_SHORT).show();
                            checkUserLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginStaff.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


    }

    private void checkUserLevel(String uid) {
        DocumentReference df = firebaseFirestore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSucces" + documentSnapshot.getData());

                if (documentSnapshot.getString("isStaff") != null) {
                    startActivity(new Intent(getApplicationContext(), AddStaff.class));
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
}