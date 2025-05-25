package com.example.shopflowscreen;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shopflowscreen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.oldPrice.setPaintFlags(binding.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.oldPrice2.setPaintFlags(binding.oldPrice2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }
}
