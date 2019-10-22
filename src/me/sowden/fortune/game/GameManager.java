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

    /**
     * 
     * GameManager
     * 
     * Manages the Game through GameStateManager and initializing all assets
     * from Player Lists to Wheel Management. Learn more about this from the
     * run() implementation.
     * 
     * @throws FileNotFoundException - For loading in the Wheel() Class when searching for the Dictionary File.
     */
    public GameManager() throws FileNotFoundException {
        players = new ArrayList<Player>();
        wheel = new Wheel();
        manager = new GameStateManager();
    }

    
    /**
     * 
     * run
     * 
     * Executes the game loop. Sets up the strings for GameState searches and adds
     * the prerequisite number of rounds as well as the start and end states to the
     * GameStateManager. The Manager sets up the states, and the states collect the
     * user input for the game.
     * 
     * @throws IOException
     */
    public void run() throws IOException {
        String[] states = {"start","round1","round2","round3","victory"};
        boolean done = false;
        manager.addState(new StartGameState(players,wheel));
        manager.addState(new RoundState(players,wheel,"1"));
        manager.addState(new RoundState(players,wheel,"2"));
        manager.addState(new RoundState(players,wheel,"3"));
        manager.addState(new VictoryGameState(players,wheel));
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
}
