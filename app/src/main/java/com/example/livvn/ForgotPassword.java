package com.example.livvn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText passemail;
    private Button passlink;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        passemail = findViewById(R.id.passemail);
        passlink = findViewById(R.id.emaillink);
        mauth = FirebaseAuth.getInstance();
        passlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = passemail.getText().toString().trim();
                if (email.equals("")) {
                    Toast.makeText(ForgotPassword.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else {
                    mauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPassword.this, "Link sent successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this, Login.class));
                            } else {
                                Toast.makeText(ForgotPassword.this, "Not a valid User", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
