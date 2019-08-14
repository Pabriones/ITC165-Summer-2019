package com.example.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button singleplayerButton =findViewById(R.id.singleplayerButton);
        singleplayerButton.setOnClickListener(this);
        Button multiplayerButton = findViewById(R.id.multiplayerButton);
        multiplayerButton.setOnClickListener(this);


    }

    public void onClick(View view) {
        if(view.getId() == R.id.singleplayerButton){
            Intent single_player = new Intent(this, singleplayerActivity.class);
            this.startActivity(single_player);
        }
        else if(view.getId() == R.id.multiplayerButton){
            Intent multi_player = new Intent(this, WordInputActivity.class);
            this.startActivity(multi_player);
        }
    }

}

