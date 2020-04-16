package com.example.markovlit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Markov {
    private String SRC;
    private int Depth;
    private HashMap<String, ArrayList<String>> FollowMap;
    private boolean ready;

    public Markov(int depth){
        init();
        Depth = depth;
    }

    public Markov(int depth, String source){
        init();
        Depth = depth;
        setSource(source);
    }

    private void init(){
        FollowMap = new HashMap<String, ArrayList<String>>();
        SRC = new String();
        ready = new Boolean(false);
    }

    public void setSource(String source){
        SRC = source;
        configureFollowMap();
        ready=true;
    }

    private void configureFollowMap(){
        StringBuilder buck = new StringBuilder();
        String bucket = new String();
        String[] choppedUp = SRC.split("\\s+");
        int startIdx = 0;
        int nextIdx = Depth;
        while(nextIdx<choppedUp.length-Depth){
            //build next "sample"
            for(int k=startIdx;k<startIdx+Depth;++k){
                buck.append(choppedUp[k]+" ");
            }
            bucket = buck.toString().trim();
            buck = new StringBuilder();
            //identify "follow" word
            String follower = choppedUp[startIdx+Depth];
            //Map
            mapKeyAndFollower(bucket,follower);
            startIdx++;
            nextIdx = startIdx+Depth;
        }
    }

    private void mapKeyAndFollower(String key, String follower){
        ArrayList<String> temp = new ArrayList<String>();
        if(FollowMap.containsKey(key)){
            temp=FollowMap.get(key);
            temp.add(follower);
            FollowMap.put(key,temp);
        }else{
            temp.add(follower);
            FollowMap.put(key,temp);
        }
    }

    public String generateSnippet(int lengthInWords){
        String ret = "Please initialize or load a source text, first";
        if(ready){
            StringBuilder sb = new StringBuilder();
            Random rnd = new Random();
            int startHere = rnd.nextInt(FollowMap.size()-Depth-1);
            int genCount=0;
            String key = FollowMap.keySet().toArray()[startHere].toString().trim();
            sb.append(key+" ");
            while(genCount<(lengthInWords-Depth)){
                String nextWord = getNextWord(key);
                sb.append(nextWord+" ");
                key = (key.substring(key.indexOf(" "))+" "+nextWord).trim();
            }
            ret = sb.toString();
        }
        return(ret);
    }

    private String getNextWord(String key){
        String ret = new String(" ");
        ArrayList<String> temp = FollowMap.get(key);
        if(temp.size()>1){
            Random rend = new Random();
            ret = temp.get(rend.nextInt(temp.size()-1));
        }else{
            if(temp.size()==1){
                ret = temp.get(0);
            }else{
                ret="of";
            }
        }
        return(ret);
    }
}
