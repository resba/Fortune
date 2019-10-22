package me.sowden.fortune.util;

import java.util.ArrayList;
/**
 * 
 * StringSearcher
 * 
 * Utility class for doing string comparisons.
 * 
 * @author Matthew Sowden
 *
 */
public class StringSearcher extends Loggable {
    private String string;
    /**
     * Initializes the searcher with the Wheel word with spaces removed.
     * @param s - The string from Wheel
     */
    public StringSearcher(String s){
        this.string = s.replace(" ","").toLowerCase();
    }
    /**
     * numberOfIterations
     * 
     * Checks the number of iterations that a letter appears in the word.
     * 
     * @param searchLetter - The letter to search
     * @return - An integer of the number of times that letter has appeared.
     */
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
    /**
     * 
     * search
     * 
     * Searches for a letter in the word.
     * 
     * @param searchLetter - The letter to search
     * @return - True if there, false if not.
     */
    public boolean search(String searchLetter){
        String lowerCaseSearchLetter = searchLetter.toLowerCase();
        log(lowerCaseSearchLetter);
        String[] search = string.split("");
        int iterations = 0;
        for (int i = 0; i < search.length; i++) {
            if(search[i] == searchLetter){
               return false;
            }
        }
        return true;
    }
    /**
     * 
     * searchGuessCheck
     * 
     * Offloaded work to have the guessedList checked from Wheel if a letter is in the list.
     * Immediately returns true if found.
     * 
     * @param searchLetter - the letter to search
     * @param guessedList - the list to check
     * @return - true if found, false if not found
     */
    public boolean searchGuessCheck(String searchLetter, ArrayList<String> guessedList){
        for (int i = 0; i < guessedList.size(); i++) {
            if(searchLetter == guessedList.get(i)){
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * checkAllMatch
     * 
     * Checks the guessedList to the wheel word to see if all letters have been found
     * 
     * @param guessedList - the guessed list
     * @return - true if all letters have been found, false if not
     */
    public boolean checkAllMatch(ArrayList<String> guessedList){
        String[] search = string.split("");
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
