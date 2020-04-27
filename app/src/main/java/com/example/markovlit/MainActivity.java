package com.example.markovlit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Librarian LIBR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LIBR = new Librarian();

        //need to populate spinner
        Spinner authors = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> authAdapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                LIBR.getAuthors());
        authors.setAdapter(authAdapt);

        /*

        //This is example useage from the vanilla Java implementation
        Markov mrkv = new Markov(4,libr.presentSource());
        System.out.println(mrkv.generateSnippet(100));

         */
    }

    public void makeLiterature(View view){
        Spinner authors = (Spinner)findViewById(R.id.spinner);
        String author = (String)authors.getSelectedItem();
        TextView litDisplay = (TextView)findViewById(R.id.lit_gen_display);

        LIBR.selectAuthor(author);
        //Markov mkv = new Markov(3,LIBR.presentSource());


    }

}
