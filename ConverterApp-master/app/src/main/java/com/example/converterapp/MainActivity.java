package com.example.converterapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView conversion1;
    TextView conversion2;

    //define spinner
    Spinner sp;

    //make string Array
    String conversion[] = {"Miles to Kilometers", "Kilometers to Miles", "Inches to Centimeters", "Centimeters to Inches"};

    //define array adapter of string type
    ArrayAdapter <String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversion1 = findViewById(R.id.conversion1);
        conversion2 = findViewById(R.id.conversion2);

        sp = (Spinner) findViewById(R.id.measure_spinner);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conversion);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp.setAdapter(adapter);

        //set spinner method
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    conversion1.setText("Miles");
                    conversion2.setText("Kilometers");
                }
                if (position==1){
                    conversion1.setText("Kilometers");
                    conversion2.setText("Miles");
                }
                if (position==2){
                    conversion1.setText("Inches");
                    conversion2.setText("Centimeters");
                }
                if (position==3){
                    conversion1.setText("Centimeters");
                    conversion2.setText("Inches");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }







}
