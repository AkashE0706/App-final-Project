package com.example.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.auth.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private AuthRepository authRepo = new AuthRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailInput = findViewById(R.id.emailInput);
        EditText passInput = findViewById(R.id.passInput);
        Button loginBtn = findViewById(R.id.loginButton);
        Button signupBtn = findViewById(R.id.signupButton);

        loginBtn.setOnClickListener(v ->
            authRepo.signIn(emailInput.getText().toString(),
                            passInput.getText().toString(),
                            new AuthRepository.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    navigateToMain();
                }
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(LoginActivity.this, "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            })
        );

        signupBtn.setOnClickListener(v ->
            authRepo.signUp(emailInput.getText().toString(),
                            passInput.getText().toString(),
                            new AuthRepository.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    navigateToMain();
                }
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(LoginActivity.this, "Signup failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            })
        );
    }

    private void navigateToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
