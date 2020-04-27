package com.example.markovlit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Librarian libr = new Librarian();

        //need to populate spinner
        Spinner authors = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> authAdapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                libr.getAuthors());
        authors.setAdapter(authAdapt);

        TextView tview = (TextView)findViewById(R.id.lit_gen_display);
        tview.setText(libr.getAuthors().get(0));

        /*

        //This is example useage from the vanilla Java implementation
        Markov mrkv = new Markov(4,libr.presentSource());
        System.out.println(mrkv.generateSnippet(100));

         */
    }

}
