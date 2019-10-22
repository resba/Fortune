package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.graphics.Graphics;
import me.sowden.fortune.state.GameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * VictoryGameState
 * 
 * Contains code for the ending portion of the game.
 * 
 * Detailed comments for overridden functions can be found in me.sowden.fortune.state.GameState
 * 
 * @author Matthew Sowden
 *
 */

public class VictoryGameState implements GameState {
    private ArrayList<Player> players;
    private Wheel wheel;
    private boolean done = false;
    private Graphics g;
    private boolean hardEnd = false;

    public VictoryGameState(ArrayList<Player> players, Wheel wheel) {
        this.players = players;
        this.wheel = wheel;
        this.g = new Graphics();
    }

    @Override
    public String getName() {
        return "victory";
    }

    @Override
    public void start() throws IOException {
        log("Entering Victory Screen");
        this.clearScreen();
        // check whos won
        int winnerIndex = 0;
        int winnerTotal = 0;
        for (int i = 0; i < players.size(); i++) {
            if(winnerTotal < players.get(i).getTotalScore()){
                winnerTotal = players.get(i).getTotalScore();
                winnerIndex = i;
            }
        }
        this.draw(new String[] {g.OPEN_ARCH,players.get(winnerIndex).getName()+" Wins the Game!",g.CLOSED_ARCH});
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName()+": "+players.get(i).getTotalScore());
        }
        System.out.println("Press Any Key to End the Game.");
        System.in.read();
        stop();
    }

    @Override
    public void stop() {
        this.done = true;
    }

    @Override
    public boolean hardExit() {
        return hardEnd;
    }

    @Override
    public void setDone(){
        this.done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
