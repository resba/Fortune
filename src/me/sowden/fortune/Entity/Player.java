package me.sowden.fortune.Entity;
import me.sowden.fortune.util.Loggable;
/**
 * 
 * Player
 * 
 * The player class holds all information about the player for the game.
 * 
 * @author Matthew Sowden
 *
 */
public class Player extends Loggable {
    private String name;
    private int roundScore;
    private int totalScore;
    private int numFreeSpin;
    /**
     * Initialize the Player by giving the name of the Player.
     * 
     * @param name - The Name of the Player
     */
    public Player(String name){
        this.name = name;
        this.roundScore = 0;
        this.totalScore = 0;
        this.numFreeSpin = 0;
        this.log("Initialized Player " + name);
    }
    /**
     * getName
     * 
     * Fetches the current name of the player
     * @return - The name of the Player
     */
    public String getName(){
        return this.name;
    }
    /**
     * bankrupt
     * 
     * Sets roundScore to 0 as per game logic, to be called when player spins "Bankrupt"
     */
    public void bankrupt(){
        this.roundScore = 0;
    }
    /**
     * addToRoundScore
     * 
     * Adds to the current round score. Used when a player gains points.
     * Can be used in negative amounts, such as when we are deduction for a vowel purchase
     * 
     * @param score - the score to be added
     */
    public void addToRoundScore(int score){
        this.roundScore += score;
    }
    /**
     * getRoundScore
     * 
     * Fetch the current round score
     * @return - The Current Round Score
     */
    public int getRoundScore(){
        return this.roundScore;
    }
    
    /**
     * 
     * canBuyVowel
     * 
     * Checks to see if the player has enough money to buy a vowel.
     * 
     * @return - True if able, false if not.
     */
    public boolean canBuyVowel(){
        if(this.roundScore >= 100){
            return true;
        }
        return false;
    }
    /**
     * winRonud
     * 
     * Called when and if the player has won the round. Adds roundTotal to totalScore.
     */
    public void winRound(){
        this.totalScore += this.roundScore;
    }
    /**
     * getTotalScore
     * 
     * Gets the totalScore of the player.
     * @return - The totalScore of the Player.
     */
    public int getTotalScore(){
        return this.totalScore;
    }
    /**
     * clearRoundScore
     * 
     * Clears the round score and sets it to 0
     */
    public void clearRoundScore(){
        this.roundScore = 0;
    }
    /**
     * addFreeSpin
     * 
     * Adds a free spin to the Free Spin Counter. Kept in the game, but unsure if it's proper game mechanics
     * so it remains unimplemented beyond the counter.
     */
    public void addFreeSpin(){
        numFreeSpin++;
    }
    /**
     * 
     * useFreeSpin
     * 
     * Checks if the player has a free spin to use. Kept in the game, but unsure if it's proper game mechanics
     * so it remains unimplemented beyond the counter.
     * 
     * @return - True if able, false if not.
     */
    public boolean useFreeSpin(){
        if(numFreeSpin==0){
            return false;
        }
        numFreeSpin--;
        return true;
    }



}
