package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.graphics.Graphics;
import me.sowden.fortune.state.GameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
/**
 * 
 * StartGameState
 * 
 * Contains the State code for the start of the game.
 *
 * Detailed comments for overridden functions can be found in me.sowden.fortune.state.GameState
 * 
 * @author Matthew Sowden
 *
 */
public class StartGameState implements GameState {
    private ArrayList<Player> players;
    private Wheel wheel;
    private boolean done = false;
    private Graphics g;
    private boolean hardEnd = false;

    public StartGameState(ArrayList<Player> players, Wheel wheel) {
        this.players = players;
        this.wheel = wheel;
        this.g = new Graphics();
    }

    @Override
    public String getName() {
        return "start";
    }

    @Override
    public void start() throws IOException {
        this.clearScreen();
        this.log("Started Game.");
        this.draw(new String[] {g.OPEN_ARCH,g.BLANK_STRING,g.BLANK_STRING,"Welcome to Wheel of Fortune!",g.BLANK_STRING,"How Many Players?",g.BLANK_STRING,g.BLANK_STRING,g.CLOSED_ARCH,"Enter 1-3, or type -1 to exit."});
        //Looking for 1-3
        boolean looper = false;

        int playerCount = 1;
        while(looper != true){
            Scanner playerEntry = new Scanner(System.in);
            boolean num = false;
            while(num == false){
                try {
                    playerCount = Integer.parseInt(playerEntry.nextLine());

                }catch(NumberFormatException e) {
                    playerCount = 0;
                    playerEntry.reset();
                }
                if(playerCount >= 1 && playerCount <=3 ){
                    num=true;
                }else{
                    System.out.println("Please enter 1-3.");
                }
            }
            if(playerCount == -1){
                log("Lower Level Exit");
                looper = true;
                hardEnd = true;
                stop();
                break;
            }else{
                log("Entered a Number.");
                for (int i = 0; i != playerCount; i++) {
                    Scanner playerName = new Scanner(System.in);
                    System.out.print("Enter Player "+(i+1)+"'s name: ");
                    String playerNameEntry = playerName.nextLine();
                    if(playerNameEntry == ""){
                        System.out.println("We see you haven't chosen a name, so we've picked one for you!");
                        Random rand = new Random();
                        String[] names = new String[]{"Aayush Mcdougall", "Courteney Leblanc", "Farzana Wise", "Arissa Barnard", "Tanner Hartman", "Ronald Frost", "Leilani Medrano", "Connie Keeling", "Louise Carr", "Huseyin Browning", "Zayne Dickinson", "Juliette Milne", "Luqman Herman", "Faiza Short", "Lulu Ayala", "Mariah O'Moore", "Paula Corona", "Arran Donaldson", "Tiago Ireland", "Siobhan Farmer"};
                        playerNameEntry = names[rand.nextInt(names.length)];
                    }
                    Player p = new Player(playerNameEntry);
                    System.out.println(g.BLANK_STRING);
                    players.add(p);
                }
                log(players.toString());
                looper = true;
            }
        }
        stop();
    }

    @Override
    public void stop() {
        this.done = true;
    }

    @Override
    public boolean hardExit() {
        return hardEnd;
    }

    @Override
    public void setDone(){
        this.done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
