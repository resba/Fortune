package me.sowden.fortune.state;

import java.io.IOException;

public interface GameState {
    String getName();
    void start() throws IOException;
    void stop();
    default void draw(String[] lines){
        //this.clearScreen();
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
    }
    default void log(String t){
        //System.out.println("DEBUG: "+t);
    }
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
    boolean hardExit();
    void setDone();
    boolean isDone();
}
