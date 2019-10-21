package me.sowden.fortune.game;

public class WordGenerator {
    private String selectedWord;
    private String selectedCategory;
    public WordGenerator(String path){
        // Load word list based on file
        // Formatting: word:category

    }
    public String getRandomWord(){
        // todo: Return word from Generator List

        return selectedWord;
    }
    public String getCategory(){
        return selectedCategory;
    }
}
