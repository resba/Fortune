package me.sowden.fortune;

import me.sowden.fortune.game.GameManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        GameManager gm = new GameManager();
        gm.run();
    }
}
