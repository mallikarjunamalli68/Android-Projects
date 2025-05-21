package com.example.numberguessing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    Button btnConfirm;
    EditText numberGuess;

    Random r = new Random();
    int random;
    int remaining = 8;
    ArrayList<Integer> guessesList = new ArrayList<>();
    int userAttempts;

    boolean twoDigit, threeDigit, fourDigit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        btnConfirm = findViewById(R.id.submit);
        numberGuess = findViewById(R.id.numGuess);

        twoDigit = getIntent().getBooleanExtra("two", false);
        threeDigit = getIntent().getBooleanExtra("three", false);
        fourDigit = getIntent().getBooleanExtra("four", false);

        if (twoDigit) {
            random = r.nextInt(90) + 10; // Ensure it's always a two-digit number
        }
        if (threeDigit) {
            random = r.nextInt(900) + 100; // Ensure it's always a three-digit number
        }
        if (fourDigit) {
            random = r.nextInt(9000) + 1000; // Ensure it's always a four-digit number
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guessStr = numberGuess.getText().toString();

                if (guessStr.equals("")) {
                    Toast.makeText(GameActivity.this, "Please enter a guess", Toast.LENGTH_SHORT).show();
                } else {
                    int guessLength = guessStr.length();
                    if ((twoDigit && guessLength != 2) || (threeDigit && guessLength != 3) || (fourDigit && guessLength != 4)) {
                        Toast.makeText(GameActivity.this, "Please enter a " + (twoDigit ? "two" : threeDigit ? "three" : "four") + "-digit number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);

                    userAttempts++;
                    remaining--;

                    int userGuess = Integer.parseInt(guessStr);
                    guessesList.add(userGuess);
                    textView1.setText("Your last guess is: " + guessStr);
                    textView2.setText("Your remaining attempts: " + remaining);

                    if (random == userGuess) {
                        showEndDialog("Congratulations! My number was " + random + ". You guessed it in " + userAttempts + " attempts.\n\nYour guesses: " + guessesList + "\n\nWould you like to play again?");
                    } else if (remaining == 0) {
                        showEndDialog("Sorry, you are out of attempts.\n\nMy number was " + random + ".\n\nYour guesses: " + guessesList + "\n\nWould you like to play again?");
                    } else {
                        if (random < userGuess) {
                            textView3.setText("Decrease your guess");
                        } else {
                            textView3.setText("Increase your guess");
                        }
                    }
                    numberGuess.setText("");
                }
            }
        });
    }

    private void showEndDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Number Guessing Game");
        builder.setCancelable(false);
        builder.setMessage(message);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.create().show();
    }
}
