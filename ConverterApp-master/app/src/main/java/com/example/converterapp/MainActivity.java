package com.example.converterapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    TextView conversion1;
    TextView conversion2;
    private EditText initialInput;
    private TextView result;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    // define instance variables that should be saved
    private String initialInputString = "";

    //define spinner
    Spinner sp;

    //make string Array
    String conversion[] = {"Miles to Kilometers", "Kilometers to Miles", "Inches to Centimeters", "Centimeters to Inches"};

    //define array adapter of string type
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversion1 = findViewById(R.id.conversion1);
        conversion2 = findViewById(R.id.conversion2);
        initialInput = findViewById(R.id.initialInput);
        result = findViewById(R.id.result);

        sp = (Spinner) findViewById(R.id.measure_spinner);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conversion);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp.setAdapter(adapter);

        // set the listeners in order for the calculation function to work
        initialInput.setOnEditorActionListener(this);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
        //set spinner method
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    conversion1.setText("Miles");
                    conversion2.setText("Kilometers");
                }
                if (position == 1) {
                    conversion1.setText("Kilometers");
                    conversion2.setText("Miles");
                }
                if (position == 2) {
                    conversion1.setText("Inches");
                    conversion2.setText("Centimeters");
                }
                if (position == 3) {
                    conversion1.setText("Centimeters");
                    conversion2.setText("Inches");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    //attempt at setting an onPause and OnResume, but it crashes the app when I call the calculateAndDisplay function
    @Override
    public void onPause() {
        // save the instance variables
        Editor editor = savedValues.edit();
        editor.putString("initialInputString", initialInputString);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        initialInputString = savedValues.getString("initialInputString", "");

        // set the bill amount on its widget
        initialInput.setText(initialInputString);

        //calculateAndDisplay();

    }

    //calculation function
    public void calculateAndDisplay() {
        initialInputString = initialInput.getText().toString();
        float input = Float.parseFloat(initialInputString);

        if (conversion1.getText().equals("Miles") && conversion2.getText().equals("Kilometers")) {
            double kilometer;
            kilometer = (double) (input * (1.60934));
            result.setText(String.valueOf(kilometer));
        }
        else if(conversion1.getText().equals("Kilometers") && conversion2.getText().equals("Miles")){
            double miles;
            miles =(double)(input * (0.6214));
            result.setText(String.valueOf(miles));

        }
        else if(conversion1.getText().equals("Centimeters") && conversion2.getText().equals("Inches")){
            double centimeter;
            centimeter =(double)(input * (0.3937));
            result.setText(String.valueOf(centimeter));
        }
        else if(conversion1.getText().equals("Inches") && conversion2.getText().equals("Centimeters")){
            double inches;
            inches =(double)(input * (2.54));
            result.setText(String.valueOf(inches));
        }

    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }

}
