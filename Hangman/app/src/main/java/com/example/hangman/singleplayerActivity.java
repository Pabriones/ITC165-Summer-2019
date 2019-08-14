package com.example.hangman;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import java.util.Random;
import com.example.hangman.LetterAdapter;


public class singleplayerActivity extends AppCompatActivity {

    //adds the elements to manipulate the body parts for hangman
    private int numParts = 6;
    private ImageView[] bodyParts;
    private int numChars;
    private int numCorr;
    private int currPart;

    //declare variables
    private String[] words;
    private Random rand = new Random();
    private String currWord = "";
    private LinearLayout wordLayout;
    private TextView[] charViews;

    private GridView letters;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer);

        Resources res = getResources();
        words = res.getStringArray(R.array.words);

        wordLayout = findViewById(R.id.word);

        //initialize the body parts and sets them to something different in array
        bodyParts = new ImageView[numParts];
        bodyParts[0] =  findViewById(R.id.head);
        bodyParts[1] =  findViewById(R.id.body);
        bodyParts[2] =  findViewById(R.id.arm1);
        bodyParts[3] =  findViewById(R.id.arm2);
        bodyParts[4] =  findViewById(R.id.leg1);
        bodyParts[5] =  findViewById(R.id.leg2);


        letters = findViewById(R.id.letters);
        LetterAdapter ltrAdapt = new LetterAdapter((singleplayerActivity.this));
        letters.setAdapter(ltrAdapt);

        playGame();

    }


    private void playGame() {
        numParts = 6;
        numCorr = 0;
        numChars = currWord.length();
        currPart = 0;


        //hide the images when starting the game
        for (int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);

        }
        //randomly chooses a word
        String newWord = words[rand.nextInt(words.length)];
        
        //assign the word chosen to the variable currWord
        while(newWord.equals(currWord)) newWord = words[rand.nextInt(words.length)];
        currWord = newWord;

        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();
        
        // iterate over each letter of the answer, create a text view for each letter
        // and set the text view's text to the current letter
        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText("" + currWord.charAt(c));
            
            // set the display properties on the text view
            charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.GREEN);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);
           
            //add to layout
            wordLayout.addView(charViews[c]);
        }
    }

    public void letterPressed(View view) {
        //check which letter the player has chosen
        String ltr = ((TextView) view).getText().toString();
        //get the character from the string
        char letterChar = ltr.charAt(0);
        
        //disable the letter button and update the background drawable
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);
        
        //loop through the characters of the target word to verify whether
        // the player's guess is in it
        boolean correct = false;
        for (int k = 0; k < currWord.length(); k++) {
            if (currWord.charAt(k) == letterChar) {
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
                charViews[k].setTextSize(16);
            }
        }
        //correct guess
        if (correct) {
            if (numCorr == currWord.length()) {
                // Disable Buttons
                disableBtns();

                // If this is true, we notify the player that they won the game
                AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
                winBuild.setTitle("YAY");
                winBuild.setMessage("You win!\n\nThe answer was:\n\n" + currWord);
                winBuild.setPositiveButton("Play Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                resetGame();
                            }
                        });

                winBuild.setNegativeButton("Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                singleplayerActivity.this.finish();
                            }
                        });

                winBuild.show();
            }

        } else if (currPart < numParts) {
            //some guesses left
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            //user has lost
            disableBtns();

            // Display Alert Dialog to notify the player they lost the game
            AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
            loseBuild.setTitle("OOPS");
            loseBuild.setMessage("You lose!\n\nThe answer was:\n\n" + currWord);
            loseBuild.setPositiveButton("Play Again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            resetGame();
                        }
                    });

            loseBuild.setNegativeButton("Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            singleplayerActivity.this.finish();
                        }
                    });

            loseBuild.show();
        }

    }

    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
        }
    }

    public void resetGame(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    }


































