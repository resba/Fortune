package me.sowden.fortune.state;

import me.sowden.fortune.util.Loggable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * 
 * GameStateManager
 * 
 * Manages the states of the game and allows for loading and unloading of game states
 * at will. Functions in this class are mostly used by GameManager to control the 
 * State of the Game.
 * 
 * @author Matthew Sowden
 *
 */
public class GameStateManager extends Loggable {
    private GameState currentState;
    private boolean done;
    private Map gameStates;
    /**
     * Initializes the GameStateManager 
     */
    public GameStateManager(){
        gameStates = new HashMap();
    }
    /**
     * 
     * addState
     * 
     * Adds a state to the GameState Map. Can be any extended instance of GameState.
     * 
     * @param state - The state to add.
     */
    public void addState(GameState state){
        gameStates.put(state.getName(), state);
        this.log("Added State. Current States: "+gameStates.toString());
    }
    
    /**
     * 
     * getStates
     * 
     * Returns an iterator of game states so they can be quickly run though.
     * Primarialy used by GameManager to run the game.
     * 
     * @return - An Iterator of GameStates from the Private Map
     */
    public Iterator getStates(){
        return gameStates.values().iterator();
    }

    /**
     * isDone
     * 
     * An upper level boolean that reveals isDone from the current state.
     * Used to manage state completion at the GameManager level.
     * 
     * @return - True if currentState is Completed, False if not.
     */
    public boolean isDone() {
        if(currentState != null){
            return currentState.isDone();
        }else {
            this.log("Could not find game state specified.");
        }
        return false;
    }
    
    /**
     * 
     * isHardDone
     * 
     * An upper level boolean that reveals isHardDone from the current state.
     * Used to manage hard exit at the GameManager level.
     * 
     * @return True if Hard Exit is required, False if not.
     */
    public boolean isHardDone() {
        if(currentState != null){
            return currentState.hardExit();
        }else {
            this.log("Could not find game state specified.");
        }
        return false;
    }

    /**
     * 
     * setState
     * 
     * Sets the state based on the name given by the GameManager.
     * 
     * @param name - The name of the State
     * @throws IOException
     */
    public void setState(String name) throws IOException {
        if(currentState != null){
            currentState.stop();
        }
        if (name == "EXIT_GAME"){
            done = true;
        }else{
            currentState = (GameState)gameStates.get(name);
            if(currentState != null){
                currentState.start();
            }else {
                this.log("Could not find game state specified.");
            }
        }
    }
}
