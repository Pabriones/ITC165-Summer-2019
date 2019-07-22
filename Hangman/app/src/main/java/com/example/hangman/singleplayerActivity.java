package com.example.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class singleplayerActivity extends AppCompatActivity {

    //declare variables
    TextView txtWordToBeGuessed;
    String wordToBeGuessed;
    String wordDisplayedString;
    char[] wordDisplayedCharArray;
    ArrayList<String> myListOfWords;
    EditText edtInput;
    TextView txtLettersTried;
    String lettersTried;

    final String MESSAGE_WITH_LETTER_TRIED = "Letters tried: ";
    final String WINNING_MESSAGE = "You won!";
    final String LOSING_MESSAGE = "You lost!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer);

        //initialize variables
        myListOfWords = new ArrayList<String>();
        txtWordToBeGuessed = findViewById(R.id.WordToBeGuessed);
        edtInput = findViewById(R.id.userGuess);
        txtLettersTried = findViewById(R.id.wordsGuessed);

        //populate array list with data from a file
        InputStream myInputStream = null;
        Scanner in = null;
        String aWord = "";

        try {
            myInputStream = getAssets().open("words.txt");
            in = new Scanner(myInputStream);
            while (in.hasNext()) {
                aWord = in.next();
                myListOfWords.add(aWord);
            }
        } catch (IOException e) {
            Toast.makeText(singleplayerActivity.this,
                    e.getClass().getSimpleName() + ": " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } finally {
            //close Scanner
            if (in != null) {
                in.close();
            }
            //close InputStream
            try {
                myInputStream.close();
            } catch (IOException e) {
                Toast.makeText(singleplayerActivity.this,
                        e.getClass().getSimpleName() + ": " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


}






