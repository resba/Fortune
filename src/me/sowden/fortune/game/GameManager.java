package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.state.GameStateManager;
import me.sowden.fortune.util.Loggable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager extends Loggable {
    private Wheel wheel;
    private ArrayList<Player> players;
    private GameStateManager manager;

    public GameManager() throws FileNotFoundException {
        players = new ArrayList<Player>();
        wheel = new Wheel();
        manager = new GameStateManager();
    }

    /**
     * Implemented Game States:
     * START = Newly Initialized
     * PLAYER_ENTRY = Enter Player Names
     * PICK_WORD = Pick A Word/Category Combo at Random
     * ROUND = In Round
     */

    /**
     * Rounds:
     * -1 = Game Setup
     * 0 = Round 1
     * 1 = Round 2
     * 2 = Round 3
     */

    public void run() throws IOException {
        String[] states = {"start","round1","round2","final_round","victory"};
        boolean done = false;
        manager.addState(new StartGameState(players,wheel));
        manager.addState(new RoundState(players,wheel,"1"));
        manager.addState(new RoundState(players,wheel,"2"));
        while(done != true){
            for (int i = 0; i < states.length - 1; i++) {
                if (manager.isHardDone()) {
                    done = true;
                }else{
                    manager.setState(states[i]);
                }
            }
            done = true;
            log(players.toString());
        }
    }

    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        this.log("Cleared Screen.");
    }
}
