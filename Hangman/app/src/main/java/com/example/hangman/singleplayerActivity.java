package com.example.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    private void displayWordOnScreen(){
        String formattedString = "";
        for(char character : wordDisplayedCharArray){
            formattedString += character + " ";
        }
        txtWordToBeGuessed.setText(formattedString);
    }

    private void initializeGame() {
        //1. WORD
        //shuffle array list and get first element, and then remove it
        Collections.shuffle(myListOfWords);
        wordToBeGuessed = myListOfWords.get(0); //get the first element of the list
        myListOfWords.remove(0); //remove to make sure the word won't appear again in the game

        //initialize charArray
        wordDisplayedCharArray = wordToBeGuessed.toCharArray();

        //add underscores
        for(int i = 0; i < wordDisplayedCharArray.length; i++){
            wordDisplayedCharArray[i] = '_';
        }

        //initialize a string from this char array (for search purposes)
        wordDisplayedString = String.valueOf(wordDisplayedCharArray);

        //display words
        displayWordOnScreen();

        //2.INPUT
        //clear input field
        edtInput.setText(""); //make sure input text is clear

        //3. LETTERS TRIED
        //initialize string for letters tried with a space
        lettersTried = " ";

        //display on screen
        txtLettersTried.setText(MESSAGE_WITH_LETTER_TRIED);


    }

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
                if(myInputStream != null) {
                    myInputStream.close();
                }
            } catch (IOException e) {
                Toast.makeText(singleplayerActivity.this,
                        e.getClass().getSimpleName() + ": " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }

        initializeGame();

        //setup the text chamged listener for the edit text
        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }




}






