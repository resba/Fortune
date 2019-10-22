package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.graphics.Graphics;
import me.sowden.fortune.state.GameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * RoundState
 * 
 * The general State containing Game Code.
 * Upon entering this state, the game will generate
 * a new word and category as well as set the player
 * roundScores to 0. Keeping track of players is 
 * dynamic thanks to the ArrayList.
 * 
 * Detailed comments for overridden functions can be found in me.sowden.fortune.state.GameState
 * 
 * @author Matthew Sowden
 *
 */
public class RoundState implements GameState {
    private ArrayList<Player> players;
    private Wheel wheel;
    private boolean done = false;
    private Graphics g;
    private boolean hardEnd = false;
    private String round;

    /**
     * 
     * Initializes the Round and Fetches helper classes like Graphics.
     * 
     * @param players - The player array list that transits through States
     * @param wheel - The Wheel entity that transits through States to keep the dictionary loaded once.
     * @param round - A text unique identifier for the round so the GameManager can predictably load the correct state.
     */
    public RoundState(ArrayList<Player> players, Wheel wheel, String round) {
        this.players = players;
        this.wheel = wheel;
        this.g = new Graphics();
        this.round = round;
    }

    @Override
    public String getName() {
        return "round"+this.round;
    }

    @Override
    public void start() throws IOException {
        this.clearScreen();
        this.log("Round "+round);
        wheel.setWord();
        int winningPlayer = 0;
        while(wheel.isComplete() == false){
            for (int i = 0; i < players.size(); i++) {
                this.clearScreen();
                boolean turn = true;
                while(turn){
                    if(wheel.isComplete()) {
                        break;
                    }
                    Player currentPlayer = players.get(i);
                    this.draw(new String[] {g.OPEN_ARCH,g.BLANK_STRING,g.BLANK_STRING,wheel.maskedWord(),g.BLANK_STRING,g.BLANK_STRING,g.setCategory(wheel.getCategory()),g.CLOSED_ARCH});
                    this.draw(new String[] {g.OPEN_ARCH,g.setPlayers(players),g.CLOSED_ARCH});
                    this.draw(new String[] {currentPlayer.getName()+" what do you want to do?","1: Spin, 2: Buy Vowel, 3: Guess Word"});
                    Scanner playerEntry = new Scanner(System.in);
                    int playerChoice = 0;
                    try {
                        playerChoice = Integer.parseInt(playerEntry.nextLine());
                    }catch(NumberFormatException e){
                        System.out.println("What?");
                    }
                    switch(playerChoice){
                        case 3:
                            this.draw(new String[] {currentPlayer.getName()+" Enter your Guess:"});
                            Scanner playerGuessPuzzle = new Scanner(System.in);
                            String puzzleGuess = playerGuessPuzzle.nextLine();
                            boolean boolGuess = wheel.guessPuzzle(puzzleGuess.toLowerCase());
                            if(boolGuess == true){
                                wheel.isComplete(true);
                            }else{
                                System.out.println("Unfortunately that's not the word! Press Enter to Continue");
                                System.in.read();
                                turn = false;
                            }
                            break;
                        case 2:
                            if(currentPlayer.canBuyVowel()){
                                System.out.println("Enter a vowel to buy: ");
                                Scanner playerEntry2 = new Scanner(System.in);
                                String guess = playerEntry2.nextLine();
                                int multiplier = 0;
                                try {
                                    multiplier = wheel.guessLetter(guess,true);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Press Any Key to Continue.");
                                    System.in.read();
                                    break;
                                }
                                if(multiplier > 0){
                                    this.draw(new String[]{"Yes we have "+multiplier+" of those! You spent $100","Press Enter to Continue."});
                                    currentPlayer.addToRoundScore(-100);
                                }else{
                                    this.draw(new String[]{"Sorry, we don't have any of those. You spent $100","Press Enter to Continue."});
                                    currentPlayer.addToRoundScore(-100);
                                    turn = false;
                                }
                                System.in.read();
                            }else{
                                System.out.println("You don't have enough money to buy a Vowel. Press Enter to Continue.");
                                System.in.read();
                            }
                            break;
                        case 1:
                            String spin = wheel.spinWheel();
                            boolean freeSpin = false;
                            switch(spin){
                                case "L":
                                    turn = false;
                                    this.draw(new String[]{"Sorry! You've lost a turn! Press Enter to Continue."});
                                    System.in.read();
                                    break;
                                case "B":
                                    turn = false;
                                    this.draw(new String[]{"Sorry! You've gone Bankrupt! Press Enter to Continue."});
                                    currentPlayer.bankrupt();
                                    System.in.read();
                                    break;
                                case "F":
                                    this.draw(new String[]{"You've earned a Free Spin! Guess a Letter: "});
                                    currentPlayer.addFreeSpin();
                                    freeSpin = true;
                                default:
                                    if(spin != "F") {
                                        this.draw(new String[]{"$" + spin + "! Guess a Letter: "});
                                    }else{
                                        spin = "250";
                                    }

                                    int multiplier = 0;
                                    boolean moveOn = false;
                                    while(moveOn == false) {
                                        Scanner playerEntry2 = new Scanner(System.in);
                                        String guess = playerEntry2.nextLine();
                                        moveOn = true;
                                        try {
                                            multiplier = wheel.guessLetter("" + guess.charAt(0), false);
                                        } catch (StringIndexOutOfBoundsException e) {
                                            System.out.println("You did not enter a guess.");
                                            System.out.println("Please Guess a Letter.");
                                            moveOn = false;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            //e.printStackTrace();
                                            System.out.println("Press Any Key to Continue.");
                                            System.in.read();
                                            multiplier = -1;
                                            turn = false;
                                            break;
                                        }
                                    }
                                    if (multiplier == 0) {
                                        this.draw(new String[]{"Sorry! That letter isn't in here! Press Enter to Continue."});
                                        if (freeSpin) {
                                            freeSpin = false;
                                        } else {
                                            turn = false;
                                        }
                                        System.in.read();
                                        this.clearScreen();
                                    } else if(multiplier == -1){
                                        break;
                                    } else {
                                        this.draw(new String[]{"Yes we have " + multiplier + " of those!", "Press Enter to Continue."});
                                        currentPlayer.addToRoundScore(Integer.parseInt(spin) * multiplier);
                                        freeSpin = false;
                                        System.in.read();
                                        this.clearScreen();
                                    }
                            }
                            break;
                        case 0:
                            this.draw(new String[]{"Please enter 1-3."});
                        case -1:
                            turn = false;
                            break;
                    }
                    if(wheel.isComplete()){
                        turn = false;
                        winningPlayer = i;
                        break;
                    }
                }
            }
        }
        this.clearScreen();
        Player winner = players.get(winningPlayer);
        winner.winRound();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).clearRoundScore();
        }
        this.draw(new String[] {g.OPEN_ARCH,winner.getName()+" Wins the Round!",g.CLOSED_ARCH});
        this.draw(new String[] {g.OPEN_ARCH,"                       Current Grand Totals",g.setPlayersTotalScore(players),g.CLOSED_ARCH});
        this.draw(new String[] {"Press Enter to Continue..."});
        System.in.read();
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
