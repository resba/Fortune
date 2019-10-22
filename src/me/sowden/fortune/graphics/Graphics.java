package me.sowden.fortune.graphics;

import me.sowden.fortune.Entity.Player;

import java.util.ArrayList;

/**
 * 
 * Graphics
 * 
 * Helper file to make graphics rendering in the main files look a bit less ugly,
 * as well as standardizing for later find/replace if the graphics need to change.
 * 
 * @author Matthew Sowden
 *
 */

public class Graphics {
    public String OPEN_ARCH =    "/-------------------------------------------------------\\";
    public String CATEGORY =     "                      /+++++++++++++++\\";
    public String CLOSED_ARCH =  "\\-------------------------------------------------------/";
    public String BLANK_STRING = "";
    /**
     * 
     * Sets the category into the category placeholder
     * 
     * @param word - The category
     * @return - Formatted category holder.
     */
    public String setCategory(String word){
        String s = CATEGORY;
        int middle = s.length()/2;
        return s.replace("+++++++++++++++",word);
    }
    /**
     * 
     * Prints out current players and their round totals
     * 
     * @param players - The Player ArrayList
     * @return - Blank String, as the Names are printed directly out to Sysout
     */
    public String setPlayers(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName()+": "+players.get(i).getRoundScore());
        }
        return "";
    }
    /**
     * Prints out current players and their round totals
     * 
     * @param players - The Player ArrayList
     * @return - Blank String, as the Names are printed directly out to Sysout
     */
    public String setPlayersTotalScore(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName()+": "+players.get(i).getRoundScore());
        }
        return "";
    }
}
