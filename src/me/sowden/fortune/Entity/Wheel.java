package me.sowden.fortune.Entity;

import me.sowden.fortune.game.WordGenerator;
import me.sowden.fortune.util.Loggable;
import me.sowden.fortune.util.StringSearcher;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * Wheel
 * 
 * Contains wheel code as well as loading the words list through WordGenerator.
 * 
 * @author Matthew Sowden
 *
 */
public class Wheel extends Loggable {
    private ArrayList<String> guessedLetters;
    private String word;
    private String category;
    private WordGenerator generator;
    private String[] wheel;
    private boolean guessedAnswer = false;
    /**
     * 
     * Initializes the Wheel entity as well as loading the WordGenerator and defining wheel slices.
     * 
     * @throws FileNotFoundException - For if the WordGenerator file is not found.
     */
    public Wheel() throws FileNotFoundException {
        //Load words
        //Load wheel
        generator = new WordGenerator("words.txt");
        guessedLetters = new ArrayList<String>();
        wheel = new String[]{"L", "800", "350", "450", "700", "300", "600", "5000", "300", "600", "300", "500", "800", "550", "400", "300", "900", "500", "F", "900", "B", "600", "400", "300"};
    }
    /**
     * setWord
     * 
     * Sets the word through the word generator as well as the category. Also resets guessedLetters and other game flags,
     * as one instance is used through the game to avoid loading the WordGenerator more than once.
     */
    public void setWord() {
        // pick word from generator
        guessedLetters = new ArrayList<String>();
        guessedAnswer = false;
        guessedLetters.add(" ");
        this.word = generator.getRandomWord();
        this.category = generator.getCategory();
    }
    /**
     * spinWheel
     * 
     * Contains the code to spin the wheel. Returns the wheel's choice, based on a random selection.
     * 
     * @return - The wheels selection.
     */
    public String spinWheel(){
        Random rand = new Random();
        int x = rand.nextInt(wheel.length);
        return wheel[x];
    }

    /**
     * getWord
     * 
     * Fetches the current word to guess, unmasked
     * @return - The word
     */
    public String getWord() {
        return word;
    }
    /**
     * getCategory
     * 
     * Fetches the current category of the word
     * @return - The category
     */
    public String getCategory(){
        return category;
    }

    /**
     * 
     * guessPuzzle
     * 
     * Contains the logic to guess the puzzle.
     * 
     * @param guess - The string containing the guess
     * @return - True if correct, false if Incorrect
     */
    public boolean guessPuzzle(String guess){
        log(guess.toLowerCase());
        log(getWord().toLowerCase());
        if(guess.toLowerCase().compareTo(getWord().toLowerCase()) == 0){
            return true;
        }
        return false;
    }

    
    /**
     * maskedWord
     * 
     * Masks the word with asterisks except for guessed letters to display on the Board.
     * 
     * @return - Masked word with asterisks for unguessed letters.
     */
    public String maskedWord() {
        String[] wordLookup = this.word.toLowerCase().split("");
        for (int i = 0; i < wordLookup.length; i++) {
            if(guessedLetters.contains(wordLookup[i]) == false){
                if(wordLookup[i] == " "){
                    wordLookup[i] = " ";
                } else {
                    wordLookup[i] = "*";
                }
            }else{
                wordLookup[i] = wordLookup[i].toUpperCase();
            }
        }
        return new String().join(",",wordLookup).replace(",","");
    }

    /**
     * 
     * guessLetter
     * 
     * Contains logic to check the guessed letter. Also checks if the user is in buyVowel mode, 
     * and if the letter has been guessed before. If the letter has been guessed before, the 
     * program will alert the user and move on to the next turn.
     * 
     * @param letter - Letter to guess
     * @param buyVowel - True if in Buy Vowel mode, false if in constant mode.
     * @return - Number of times that letter appears.
     * @throws Exception
     */
    public int guessLetter(String letter, boolean buyVowel) throws Exception {
        // todo: guess a letter please.
        boolean guess = false;
        letter = letter.toLowerCase();
        ArrayList<String> vowels = new ArrayList<String>();
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");
        if(buyVowel == true){
            if(vowels.contains(letter)){
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
            if(vowels.contains(letter)){
                throw new Exception("Letter is a Vowel and was not bought.");
            }else {
                if (isGuessed(letter.toLowerCase())) {
                    throw new Exception("Letter was already guessed.");
                } else {
                    //letter was not guessed
                    guess = true;
                }
            }
        }
        if(guess){
            // check against
            if(vowels.contains(letter)){
                if(buyVowel==false) {
                    throw new Exception("Letter is a Vowel and was not bought.");
                }
            }
            StringSearcher check = new StringSearcher(this.word.toLowerCase());
            guessedLetters.add(letter);
            log("Guessed Letters: "+guessedLetters.toString());
            return check.numberOfIterations(letter);
        }
        return -1;
    }
    /**
     * 
     * isGuessed
     * 
     * A helper function to check if a letter has been guessed.
     * 
     * @param letter - the letter to check
     * @return - True if guessed, false if not guessed.
     */
    public boolean isGuessed(String letter){
        return this.guessedLetters.contains(letter.toLowerCase());
    }
    /**
     * 
     * isComplete
     * 
     * Checks to see if the puzzle has been solved.
     * 
     * @return - True for the puzzle having been solved, false if not solved.
     */
    public boolean isComplete(){
        // todo: check if letters match up then return false or true.
        if(guessedAnswer){
            return true;
        }
        return new StringSearcher(this.word).checkAllMatch(guessedLetters);

    }
    /**
     * 
     * isComplete w/ Param
     * 
     * Used to set a guessedAnswer flag to immediately end the round, even if all the letters
     * havent been guessed yet.
     * 
     * @param guessedAnswer - The boolean flag stating the round is over.
     */
    public void isComplete(boolean guessedAnswer) {
        this.guessedAnswer = true;
    }
}
