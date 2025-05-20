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

import com.example.sqliteproject.databinding.ActivitySignUpBinding;

public class SignUp_Activity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DatabaseHepler databaseHepler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHepler = new DatabaseHepler(this);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.edEmail.getText().toString();
                String password = binding.edPassword.getText().toString();
                String confirmpassword = binding.confirmPassword.getText().toString();

                if(email.equals(" ") || password.equals(" ") || confirmpassword.equals(" ")){
                    Toast.makeText(SignUp_Activity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (password.equals(confirmpassword)) {
                        boolean checkUserEmail = databaseHepler.checkEmail(email);
                        if (!checkUserEmail) {
                            boolean insert = databaseHepler.insertData(email, password);

                            if (insert) {
                                Toast.makeText(SignUp_Activity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUp_Activity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUp_Activity.this, "User already exist, please login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp_Activity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.teBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}