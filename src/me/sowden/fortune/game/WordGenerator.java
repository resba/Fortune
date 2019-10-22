package me.sowden.fortune.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordGenerator {
    private String selectedWord;
    private String selectedCategory;
    private ArrayList<String> words;
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
    public String getRandomWord(){
        // todo: Return word from Generator List
        Random rand = new Random();
        int x = rand.nextInt(words.size());
        String[] selectedEntry = words.get(x).split(":");
        this.selectedWord = selectedEntry[0];
        this.selectedCategory = selectedEntry[1];
        return selectedWord;
    }
    public String getSelectedWord(){
        return selectedWord;
    }
    public String getCategory(){
        return selectedCategory;
    }
}
