package me.sowden.fortune.util;

import java.util.ArrayList;

public class StringSearcher extends Loggable {
    private String string;
    public StringSearcher(String s){
        this.string = s.toLowerCase();
    }
    public int numberOfIterations(String searchLetter){
        String lowerCaseSearchLetter = searchLetter.toLowerCase();
        log(lowerCaseSearchLetter);
        char[] search = string.toCharArray();
        int iterations = 0;
        for (int i = 0; i < search.length; i++) {
            if(search[i] == searchLetter.toCharArray()[0]){
                iterations++;
            }
        }
        return iterations;
    }
    public boolean search(String searchLetter){
        String lowerCaseSearchLetter = searchLetter.toLowerCase();
        log(lowerCaseSearchLetter);
        char[] search = string.toCharArray();
        int iterations = 0;
        for (int i = 0; i < search.length; i++) {
            if(search[i] == searchLetter.toCharArray()[0]){
               return false;
            }
        }
        return true;
    }
    public boolean checkAllMatch(ArrayList<Character> guessedList){
        char[] search = string.toCharArray();
        boolean found = true;
        for (int i = 0; i < search.length; i++) {
            log(search.toString());
            if(guessedList.contains(search[i]) == false){
                found = false;
            }
        }
        return found;
    }
}
