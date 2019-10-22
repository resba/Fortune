package me.sowden.fortune.state;

import java.io.IOException;
/**
 * 
 * GameState
 * 
 * A GameState interface for generalizing the implementation of GameState functions
 * that are shared across States in the Program. This was borrowed from an 
 * older project of mine that utilized AWT and JavaX and was adapted to be
 * used strictly for the command line.
 * 
 * @author Matthew Sowden
 *
 */
public interface GameState {
	/**
	 * 
	 * getName
	 * 
	 * Sets up the name for the Game State to be called by the GameStateManager
	 * 
	 * @return The name of the GameState
	 */
    String getName();
    /**
     * 
     * start
     * 
     * Contains the Game Code and primary loops for that State. Will be run over and over until the stop function is called.
     * 
     * @throws IOException
     */
    void start() throws IOException;
    /**
     * 
     * stop
     * 
     * Handy function to easily call setDone. I try to use stop instead of setDone that way its clearer to read.
     * 
     */
    void stop();
    /**
     * 
     * draw
     * 
     * Draws onto the interface, in this case command line.
     * 
     * @param lines - Takes a String Array for lines to draw.
     */
    default void draw(String[] lines){
        //this.clearScreen();
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
    }
    /**
     * log
     * 
     * Debug command to print to screen any variables I need to keep track of.
     * 
     * @param t - String to be displayed. 
     */
    default void log(String t){
        //System.out.println("DEBUG: "+t);
    }
    /**
     * 
     * clearScreen
     * 
     * Clears the screen to make for easier reading. Will check for the Operating
     * System to determine which clearing function to use, as Windows needs a more
     * specific clearing method than *nix systems.
     * 
     * @throws IOException
     */
    default void clearScreen() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        }catch(IOException e){
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.log("Cleared Screen.");
    }
    /**
     * 
     * hardExit
     * 
     * An "emergency stop button" for the program in case we need to exit without the general conditions
     * 
     * @return - If true, the state will hard quit and move onto the next one.
     */
    boolean hardExit();
    /**
     * setDone
     * 
     * Sets a private class as a flag to softly end the state on next loop.
     */
    void setDone();
    /**
     * isDone
     * 
     * A boolean flag to check if the state can be ended safely.
     * @return - True if ready to end, False if the state is still in operation.
     */
    boolean isDone();
}
