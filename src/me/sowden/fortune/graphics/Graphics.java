package me.sowden.fortune.graphics;

import com.sun.xml.internal.ws.util.StringUtils;

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
}
