package me.sowden.fortune.state;

import java.io.IOException;

public interface GameState {
    String getName();
    String checkForStateChange();
    void start() throws IOException;
    void stop();
    void update(long elapsedTime);
    void draw(String[] lines);
    default void log(String t){
        System.out.println("DEBUG: "+t);
    }
    default void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        this.log("Cleared Screen.");
    }
    boolean hardExit();
    void setDone();
    boolean isDone();
}