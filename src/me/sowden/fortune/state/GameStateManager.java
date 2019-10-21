package me.sowden.fortune.state;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.game.RoundState;
import me.sowden.fortune.util.Loggable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameStateManager extends Loggable {
    private GameState currentState;
    private boolean done;
    private Map gameStates;

    public GameStateManager(){
        gameStates = new HashMap();
    }
    public void addState(GameState state){
        gameStates.put(state.getName(), state);
        this.log("Added State. Current States: "+gameStates.toString());
    }
    public Iterator getStates(){
        return gameStates.values().iterator();
    }

    public boolean isDone() {
        if(currentState != null){
            return currentState.isDone();
        }else {
            this.log("Could not find game state specified.");
        }
        return false;
    }
    public boolean isHardDone() {
        if(currentState != null){
            return currentState.hardExit();
        }else {
            this.log("Could not find game state specified.");
        }
        return false;
    }

    public void setState(String name){
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
