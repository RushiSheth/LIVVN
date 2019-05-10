package com.example.livvn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText editEmail, editPassword;
    ProgressBar progressb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.usernameedit);
        editPassword = findViewById(R.id.passedit);
        mAuth = FirebaseAuth.getInstance();
        progressb = findViewById(R.id.Progressb);
        findViewById(R.id.LOGIN).setOnClickListener(this);
        findViewById(R.id.ForgotPassword).setOnClickListener(this);
    }

    private void userlogin() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editEmail.setError("Email required");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Enter valid email");
            editEmail.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            editPassword.setError("Password required");
            editPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editPassword.setError("Minimum length 6");
            editPassword.requestFocus();
            return;
        }
        progressb.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressb.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Logged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Navigation.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void passlink() {
        Intent intent = new Intent(Login.this, ForgotPassword.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ForgotPassword:
                passlink();
                break;

            case R.id.LOGIN:
                userlogin();
                break;
        }
    }
}

