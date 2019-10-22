package me.sowden.fortune.Entity;
import me.sowden.fortune.util.Loggable;

public class Player extends Loggable {
    private String name;
    private int roundScore;
    private int totalScore;
    private int numFreeSpin;
    public Player(String name){
        this.name = name;
        this.roundScore = 0;
        this.totalScore = 0;
        this.numFreeSpin = 0;
        this.log("Initialized Player " + name);
    }
    public String getName(){
        return this.name;
    }
    public void bankrupt(){
        this.roundScore = 0;
    }
    public void addToRoundScore(int score){
        this.roundScore += score;
    }
    public int getRoundScore(){
        return this.roundScore;
    }
    public boolean canBuyVowel(){
        if(this.roundScore >= 100){
            return true;
        }
        return false;
    }
    public void winRound(){
        this.totalScore += this.roundScore;
    }
    public int getTotalScore(){
        return this.totalScore;
    }
    public void clearRoundScore(){
        this.roundScore = 0;
    }
    public void addFreeSpin(){
        numFreeSpin++;
    }
    public boolean useFreeSpin(){
        if(numFreeSpin==0){
            return false;
        }
        numFreeSpin--;
        return true;
    }



}
