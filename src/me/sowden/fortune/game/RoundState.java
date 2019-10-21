package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.graphics.Graphics;
import me.sowden.fortune.state.GameState;

import java.util.ArrayList;
import java.util.Scanner;

public class RoundState implements GameState {
    private ArrayList<Player> players;
    private Wheel wheel;
    private boolean done = false;
    private Graphics g;
    private boolean hardEnd = false;
    private String round;

    public RoundState(ArrayList<Player> players, Wheel wheel, String round) {
        this.players = players;
        this.wheel = wheel;
        this.g = new Graphics();
        this.round = round;
    }

    @Override
    public String getName() {
        return "round";
    }

    @Override
    public String checkForStateChange() {
        return null;
    }

    @Override
    public void start() {
        this.clearScreen();
        this.log("Round "+round);

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
