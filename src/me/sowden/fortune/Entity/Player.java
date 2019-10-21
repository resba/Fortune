package me.sowden.fortune.Entity;
import me.sowden.fortune.util.Loggable;

public class Player extends Loggable {
    private String name;
    private int roundScore;
    private int totalScore;
    public Player(String name){
        this.name = name;
        this.roundScore = 0;
        this.totalScore = 0;
        this.log("Initialized Player " + name);
    }
    public String getName(){
        return this.name;
    }
    public int getRoundScore(){
        return this.roundScore;
    }
    public int getTotalScore(){
        return this.totalScore;
    }



}
