package me.sowden.fortune.Entity;

import me.sowden.fortune.game.WordGenerator;
import me.sowden.fortune.util.Loggable;
import me.sowden.fortune.util.StringSearcher;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Wheel extends Loggable {
    private ArrayList<String> guessedLetters;
    private String word;
    private String category;
    private WordGenerator generator;
    private String[] wheel;
    private boolean guessedAnswer = false;
    public Wheel() throws FileNotFoundException {
        //Load words
        //Load wheel
        generator = new WordGenerator("words.txt");
        guessedLetters = new ArrayList<String>();
        wheel = new String[]{"L", "800", "350", "450", "700", "300", "600", "5000", "300", "600", "300", "500", "800", "550", "400", "300", "900", "500", "F", "900", "B", "600", "400", "300"};
    }
    public void setWord() {
        // pick word from generator
        guessedLetters = new ArrayList<String>();
        guessedAnswer = false;
        this.word = generator.getRandomWord();
        this.category = generator.getCategory();
    }

    public String spinWheel(){
        Random rand = new Random();
        int x = rand.nextInt(wheel.length);
        return wheel[x];
    }

    public String getWord() {
        return word;
    }
    public String getCategory(){
        return category;
    }

    public boolean guessPuzzle(String guess){
        log(guess.toLowerCase());
        log(getWord().toLowerCase());
        if(guess.toLowerCase().compareTo(getWord().toLowerCase()) == 0){
            return true;
        }
        return false;
    }

    public String maskedWord() {
        String[] wordLookup = this.word.toLowerCase().split("");
        for (int i = 0; i < wordLookup.length; i++) {
            if(guessedLetters.contains(wordLookup[i]) == false){
                wordLookup[i] = "*";
            }
        }
        return new String().join(",",wordLookup).replace(",","");
    }

    public int guessLetter(String letter, boolean buyVowel) throws Exception {
        // todo: guess a letter please.
        boolean guess = false;
        letter = letter.toLowerCase();
        if(buyVowel == true){
            if(letter == "a" || letter == "e" || letter == "i" || letter == "o" || letter == "u"){
                // is a vowel.
                if(isGuessed(letter)){
                    throw new Exception("Letter was already guessed. (Vowel)");
                }else{
                    //letter was not guessed
                    guess = true;
                }
            }else{
                throw new Exception("Letter was not a Vowel");
            }
        }else {
            if (isGuessed(letter.toLowerCase())) {
                throw new Exception("Letter was already guessed.");
            } else {
                //letter was not guessed
                guess = true;
            }
        }
        if(guess){
            // check against
            StringSearcher check = new StringSearcher(this.word.toLowerCase());
            guessedLetters.add(letter);
            log("Guessed Letters: "+guessedLetters.toString());
            return check.numberOfIterations(letter);
        }
        return -1;
    }
    public boolean isGuessed(String letter){
        return this.guessedLetters.contains(letter.toLowerCase());
    }
    public boolean isComplete(){
        // todo: check if letters match up then return false or true.
        if(guessedAnswer){
            return true;
        }
        return new StringSearcher(this.word).checkAllMatch(guessedLetters);

    }
    public void isComplete(boolean guessedAnswer) {
        this.guessedAnswer = true;
    }
}
