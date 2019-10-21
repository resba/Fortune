package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.graphics.Graphics;
import me.sowden.fortune.state.GameState;

import java.util.ArrayList;
import java.util.Scanner;

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
    public String checkForStateChange() {
        return null;
    }

    @Override
    public void start() {
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
    public void update(long elapsedTime) {

    }

    @Override
    public void draw(String[] lines) {
        this.clearScreen();
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
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
