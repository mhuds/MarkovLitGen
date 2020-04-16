package com.example.markovlit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//currently just keeping everything in memory.  Will eventually move some of this
//over to a SQLite database.
public class Librarian {
    private List<String> AUTHORS;
    private String SELECTED_AUTHOR;
    private String SOURCE;
    private HashMap<String,String> LITERATURE;
    private String STOP;

    public Librarian(){
        this.AUTHORS = new ArrayList<String>();
        this.SOURCE = new String();
        this.LITERATURE = new HashMap<String,String>();
        this.STOP = new String("End of the Project Gutenberg EBook");
    }

    private void populateAuthors(){
        String[] pile = {"Lovecraft","Shakespeare","Milton","Bible"};
        for(String name : pile){
            this.AUTHORS.add(name);
        }
    }

    private void populateLit(){
        String[] pile = {"http://www.gutenberg.org/cache/epub/50133/pg50133.txt",
            "https://www.gutenberg.org/files/100/100-0.txt",
            "https://www.gutenberg.org/files/20/20-0.txt",
            "http://www.gutenberg.org/cache/epub/10/pg10.txt"};
        for(int idx=0; idx<pile.length;++idx){
            LITERATURE.put(AUTHORS.get(idx),pile[idx]);
        }
    }

    public List<String> getAuthors(){
        return(this.AUTHORS);
    }

    public void selectAuthor(String authName){
        int idx = AUTHORS.indexOf(authName);
        if(idx>=0){
            this.SELECTED_AUTHOR = this.AUTHORS.get(idx);
            fillSource();
        }
    }

    private void fillSource(){
        //reach out to URL - key off of SELECTED_AUTHOR
        String thisOne = LITERATURE.get(SELECTED_AUTHOR);
        //download text
        try{
            SOURCE = readURL(thisOne);
        }catch(Exception e){
            //do something clever with the exception
            SOURCE = "";
        }
        //Scrub leading and training gutenberg boilerplate
        int startIdx = SOURCE.indexOf("***");
        startIdx = SOURCE.indexOf("***",startIdx+1);
        SOURCE = SOURCE.substring(startIdx+3);
        if(SOURCE.indexOf(STOP)<0){
            STOP="End of Project Gutenberg";
        }
        SOURCE = SOURCE.substring(0,SOURCE.indexOf(STOP));
    }

    private String readURL(String url) throws Exception{
        URL docsite = new URL(url);
        URLConnection conxn = docsite.openConnection();
        InputStreamReader inStrReader = new InputStreamReader(conxn.getInputStream());
        BufferedReader bufInstr = new BufferedReader(inStrReader);
        StringBuilder contents = new StringBuilder();
        String conByLine = new String();
        while((conByLine=bufInstr.readLine())!=null){
            contents.append(conByLine);
        }
        bufInstr.close();
        return(contents.toString());
    }

    public String presentSource(){
        return this.SOURCE;
    }

}
