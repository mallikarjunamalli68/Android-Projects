package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button but0, but1, but2, but3, but4, but5, but6, but7, but8, but9, clearButton, delButton,doubleZ;
    Button butDiv,butMin,butPlus,butDot,butEqual,butMul;
    TextView result;

    String number = null;
    double num1 = 0;
    double num2 = 0;
    String status = null;
    boolean operator = false;
    TextView viewHystory;
    String history, currentResult;
    boolean dot = false;
    boolean acControl = true;
    boolean equalControl = false;
    DecimalFormat myFormat = new DecimalFormat("####.####");
    // Use StringBuilder for efficient string manipulation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        but0 = findViewById(R.id.but0);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        but4 = findViewById(R.id.but4);
        but5 = findViewById(R.id.but5);
        but6 = findViewById(R.id.but6);
        but7 = findViewById(R.id.but7);
        but8 = findViewById(R.id.but8);
        but9 = findViewById(R.id.but9);
        result=findViewById(R.id.result);
        doubleZ=findViewById(R.id.doubleZ);
        clearButton = findViewById(R.id.clear);
        delButton = findViewById(R.id.del);
        butDiv = findViewById(R.id.div);
        butPlus = findViewById(R.id.plus);
        butMin = findViewById(R.id.min);
        butMul = findViewById(R.id.mul);
        butEqual = findViewById(R.id.equal);
        butDot = findViewById(R.id.dot);
        viewHystory = findViewById(R.id.finalHistory);

        number = result.getText().toString();

        but0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                numberClick("0");
            }
        });


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("3");
            }
        });

        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("4");
            }
        });

        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("5");
            }
        });

        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("6");
            }
        });

        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("7");
            }
        });

        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("8");
            }
        });

        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("9");
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=null;
                number=null;
                result.setText("0");
                viewHystory.setText("");
                num1=0;
                num2=0;
                dot = true;
                acControl = true;
            }
        });

        butPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = viewHystory.getText().toString();
                currentResult = result.getText().toString();
                viewHystory.setText(history+currentResult+"+");

                if(operator){
                    if(status == "multiplication"){
                        mul();
                    } else if (status=="division") {
                        div();
                    } else if (status=="substraction") {
                        min();
                    }else {
                        plus();
                    }
                }

                status="sum";
                operator = false;
                number=null;

            }
        });

        butMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = viewHystory.getText().toString();
                currentResult = result.getText().toString();
                viewHystory.setText(history+currentResult+"*");

                if(operator){
                    if(status == "sum"){
                        plus();
                    } else if (status=="division") {
                        div();
                    } else if (status=="substraction") {
                        min();
                    }else {
                        mul();
                    }
                }

                status="multiplication";
                operator = false;
                number=null;
            }
        });

        butMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = viewHystory.getText().toString();
                currentResult = result.getText().toString();
                viewHystory.setText(history+currentResult+"-");

                if(operator){
                    if(status == "multiplication"){
                        mul();
                    } else if (status=="division") {
                        div();
                    } else if (status=="sum") {
                        plus();
                    }else {
                        min();
                    }
                }

                status="substraction";
                operator = false;
                number=null;
            }
        });

        butDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = viewHystory.getText().toString();
                currentResult = result.getText().toString();
                viewHystory.setText(history+currentResult+"/");

                if(operator){
                    if(status == "multiplication"){
                        mul();
                    } else if (status=="sum") {
                        plus();
                    } else if (status=="substraction") {
                        min();
                    }else {
                        div();
                    }
                }

                status="division";
                operator = false;
                number=null;
            }
        });

        butDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dot){
                    if(number==null){
                        number = "0.";
                    }else {
                        number = number + ".";
                    }
                }
                result.setText(number);
                dot = false;
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(acControl){
                    result.setText("0");
                }else {

                    number = number.substring(0,number.length()-1);

                    if(number.length()==0){
                        delButton.setClickable(false);
                    } else if (number.contains(".")) {
                        dot = false;
                    }else {
                        dot = true;
                    }
                    result.setText(number);

                }
            }
        });

        butEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(operator){
                    if (status=="multiplication"){
                        mul();
                    } else if (status=="sum") {
                        plus();
                    } else if (status=="division") {
                        div();
                    } else if (status=="substraction") {
                        min();
                    }
                    else {
                        num1=Double.parseDouble(result.getText().toString());
                    }
                }
                operator = false;
                equalControl = true;
            }
        });
    }

    public void numberClick(String view){
        if(number==null){
            number = view;
        } else if (equalControl) {
            num1=0;
            num2=0;
            number=view;
        } else {
           number = number + view;
        }
        result.setText(number);
        operator = true;
        acControl = false;
        delButton.setClickable(true);
        equalControl = false;
    }

    public void plus(){
        num2 = Double.parseDouble(result.getText().toString());
        num1 = num1+num2;
        result.setText(myFormat.format(num1));
        dot = true;
    }

    public void min(){
        if(num1==0){
            num2 = Double.parseDouble(result.getText().toString());
        }else {
            num1 = Double.parseDouble(result.getText().toString());
            num1 = num1-num2;
        }
        result.setText(myFormat.format(num1));
        dot = true;
    }

    public void mul(){
        if(num1==0){
            num1 = 1;
            num2 = Double.parseDouble(result.getText().toString());
            num1 = num1*num2;
        }else {
            num2 = Double.parseDouble(result.getText().toString());
            num1 = num1*num2;
        }
        result.setText(myFormat.format(num1));
        dot = true;
    }

    public void div(){
        if(num1==0){
            num2 = Double.parseDouble(result.getText().toString());
            num1 = num2/1;
        }else {
            num2 = Double.parseDouble(result.getText().toString());
            num1 = num1 / num2;
        }
        result.setText(myFormat.format(num1));
        dot = true;
    }
}
