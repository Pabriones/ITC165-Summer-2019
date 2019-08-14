package com.example.hangman;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WordInputActivity extends AppCompatActivity{

    Button addWordToBeGuessed;
    EditText wordAdded;
    String stringWord;
    public static final String VALUE = "VALUE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_input);

        addWordToBeGuessed = findViewById(R.id.btn_add_word);
        wordAdded = findViewById(R.id.insert_word);

        addWordToBeGuessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringWord = wordAdded.getText().toString();
                Intent multiplayer = new Intent(WordInputActivity.this, multiplayerActivity.class);
                multiplayer.putExtra("VALUE", stringWord);
                startActivity(multiplayer);
                finish();
            }
        });

    }

}