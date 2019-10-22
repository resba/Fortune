package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.graphics.Graphics;
import me.sowden.fortune.state.GameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * StartGameState
 * 
 * Contains the State code for the start of the game.
 *
 * Detailed comments for overridden functions can be found in me.sowden.fortune.state.GameState
 * 
 * @author Matthew Sowden
 *
 */
public class StartGameState implements GameState {
    private ArrayList<Player> players;
    private Wheel wheel;
    private boolean done = false;
    private Graphics g;
    private boolean hardEnd = false;

    public StartGameState(ArrayList<Player> players, Wheel wheel) {
        this.players = players;
        this.wheel = wheel;
        this.g = new Graphics();
    }

    @Override
    public String getName() {
        return "start";
    }

    @Override
    public void start() throws IOException {
        this.clearScreen();
        this.log("Started Game.");
        this.draw(new String[] {g.OPEN_ARCH,g.BLANK_STRING,g.BLANK_STRING,"Welcome to Wheel of Fortune!",g.BLANK_STRING,"How Many Players?",g.BLANK_STRING,g.BLANK_STRING,g.CLOSED_ARCH,"Enter 1-3, or type -1 to exit."});
        //Looking for 1-3
        boolean looper = false;
        Scanner playerEntry = new Scanner(System.in);
        while(looper != true){
            int playerCount = playerEntry.nextInt();
            if(playerCount == -1){
                log("Lower Level Exit");
                looper = true;
                hardEnd = true;
                stop();
                break;
            }else{
                log("Entered a Number.");
                // todo: error handling for NaN
                for (int i = 0; i != playerCount; i++) {
                    Scanner playerName = new Scanner(System.in);
                    System.out.print("Enter Player "+(i+1)+"'s name: ");
                    String playerNameEntry = playerName.nextLine();
                    Player p = new Player(playerNameEntry);
                    System.out.println(g.BLANK_STRING);
                    players.add(p);
                }
                log(players.toString());
                looper = true;
            }
        }
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
