package me.sowden.fortune.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * 
 * WordGenerator
 * 
 * Loads words and categories from file and selects them at random for use in the game.
 * 
 * @author Matthew Sowden
 *
 */
public class WordGenerator {
    private String selectedWord;
    private String selectedCategory;
    private ArrayList<String> words;
    /**
     * 
     * Initialize the WordGenerator by opening the file and loading the words into an array list.
     * 
     * Words file format:
     * word:category
     * word2:category2
     * 
     * @param path - The File System path to the words file.
     * @throws FileNotFoundException
     */
    public WordGenerator(String path) throws FileNotFoundException {
        // Load word list based on file
        // Formatting: word:category
        File file = new File(path);
        Scanner sc = new Scanner(file);
        this.words = new ArrayList<String>();
        while(sc.hasNextLine()){
            String tempStorage = sc.nextLine();
            words.add(tempStorage);
        }
    }
    /**
     * getRandomWord
     * 
     * Returns a random word for the list, setting it into the word generator's string references.
     * This should be called before anything else to generate the word.
     * 
     * @return - The selected random word.
     */
    public String getRandomWord(){
        // todo: Return word from Generator List
        Random rand = new Random();
        int x = rand.nextInt(words.size());
        String[] selectedEntry = words.get(x).split(":");
        this.selectedWord = selectedEntry[0];
        this.selectedCategory = selectedEntry[1];
        return selectedWord;
    }
    /**
     * getSelectedWord
     * 
     * returns the selected word
     * @return - the selected word
     */
    public String getSelectedWord(){
        return selectedWord;
    }
    /**
     * getSelectedCategory
     * 
     * returns the selected category
     * @return - the selected category
     */
    public String getCategory(){
        return selectedCategory;
    }
}
