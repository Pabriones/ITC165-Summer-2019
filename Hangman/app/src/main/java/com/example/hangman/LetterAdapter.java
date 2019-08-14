package com.example.hangman;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

public class LetterAdapter extends BaseAdapter {

    private String[] letters; //to store the letters of the alphabet
    private LayoutInflater letterInf; //to apply yhe button layout


    public LetterAdapter(Context c) {
        //instantiate the alphabet array and assign the letters A-Z to each position
        letters = new String[26];
        for (int a = 0; a < letters.length; a++) {
            letters[a] = "" + (char)(a+'A');
        }
        //loop starting at zero by adding the value of the character A to each array index
        letterInf = LayoutInflater.from(c);
    }


    public int getCount() {
        return letters.length; //represents the number of views, one for each letter
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        //create a button for the letter at this position in the alphabet
        Button letterBtn;
        if (convertView == null) {
            //inflate the button layout
            letterBtn = (Button)letterInf.inflate(R.layout.letter, parent, false);
        } else {
            letterBtn = (Button) convertView;
        }
        //set the text to this letter
        letterBtn.setText(letters[position]);
        return letterBtn;
    }
}
