package me.sowden.fortune.graphics;

import com.sun.xml.internal.ws.util.StringUtils;
import me.sowden.fortune.Entity.Player;

import java.util.ArrayList;

public class Graphics {
    public String OPEN_ARCH =    "/-------------------------------------------------------\\";
    public String CATEGORY =     "                      /+++++++++++++++\\";
    public String CLOSED_ARCH =  "\\-------------------------------------------------------/";
    public String BLANK_STRING = "";
    public String setCategory(String word){
        String s = CATEGORY;
        int middle = s.length()/2;
        return s.replace("+++++++++++++++",word);
    }
    public String setPlayers(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName()+": "+players.get(i).getRoundScore());
        }
        return "";
    }
}
