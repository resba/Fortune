package me.sowden.fortune.Entity;

import me.sowden.fortune.game.WordGenerator;
import me.sowden.fortune.util.Loggable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Wheel extends Loggable {
    private ArrayList<String> guessedLetters;
    private String word;
    private String category;
    private WordGenerator generator;
    public Wheel(){
        //Load words
        //Load wheel
        generator = new WordGenerator("words.txt");

    }
    public void setWord(){
        // pick word from generator
        this.word = generator.getRandomWord();
        this.category = generator.getCategory();
    }
}
