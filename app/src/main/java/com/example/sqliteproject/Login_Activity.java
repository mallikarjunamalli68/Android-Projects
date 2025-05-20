package com.example.sqliteproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqliteproject.databinding.ActivityLoginBinding;
import com.example.sqliteproject.databinding.ActivitySignUpBinding;

public class Login_Activity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHepler databaseHepler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHepler = new DatabaseHepler(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if (email.equals(" ") || password.equals(" ")) {
                    Toast.makeText(Login_Activity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {

                    boolean checkCredentials = databaseHepler.checkEmailAndPassword(email, password);
                    if(checkCredentials){
                        Toast.makeText(Login_Activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Login_Activity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.teLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });

    }
}