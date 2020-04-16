package com.example.markovlit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Librarian libr = new Librarian();

        /*

        //This is example useage from the vanilla Java implementation
        Markov mrkv = new Markov(4,libr.presentSource());
        System.out.println(mrkv.generateSnippet(100));

         */
    }
}
