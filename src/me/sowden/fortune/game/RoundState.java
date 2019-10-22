package me.sowden.fortune.game;

import me.sowden.fortune.Entity.Player;
import me.sowden.fortune.Entity.Wheel;
import me.sowden.fortune.graphics.Graphics;
import me.sowden.fortune.state.GameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoundState implements GameState {
    private ArrayList<Player> players;
    private Wheel wheel;
    private boolean done = false;
    private Graphics g;
    private boolean hardEnd = false;
    private String round;

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
    public String checkForStateChange() {
        return null;
    }

    @Override
    public void start() throws IOException {
        this.clearScreen();
        this.log("Round "+round);
        wheel.setWord();
        while(wheel.isComplete() == false){
            for (int i = 0; i < players.size(); i++) {
                this.clearScreen();
                boolean turn = true;
                while(turn){
                    Player currentPlayer = players.get(i);
                    this.draw(new String[] {g.OPEN_ARCH,g.BLANK_STRING,g.BLANK_STRING,wheel.maskedWord(),g.BLANK_STRING,g.BLANK_STRING,g.setCategory(wheel.getCategory()),g.CLOSED_ARCH});
                    this.draw(new String[] {g.OPEN_ARCH,g.setPlayers(players),g.CLOSED_ARCH});
                    this.draw(new String[] {currentPlayer.getName()+" what do you want to do?","1: Spin, 2: Buy Vowel, 3: Guess Word"});
                    Scanner playerEntry = new Scanner(System.in);
                    int playerChoice = playerEntry.nextInt();
                    switch(playerChoice){
                        case 3:
                            this.draw(new String[] {currentPlayer.getName()+" Enter your Guess:"});
                            Scanner playerGuessPuzzle = new Scanner(System.in);
                            String puzzleGuess = playerGuessPuzzle.nextLine();
                            boolean boolGuess = wheel.guessPuzzle(puzzleGuess);
                            if(boolGuess == true){
                                wheel.isComplete(true);
                            }
                            break;
                        case 2:
                            // todo: buy vowel
                            break;
                        case 1:
                            // todo: spin
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
                                    Scanner playerEntry2 = new Scanner(System.in);
                                    String guess = playerEntry2.nextLine();
                                    int multiplier = 0;
                                    try {
                                        multiplier = wheel.guessLetter(guess,false);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if(multiplier == 0){
                                        this.draw(new String[]{"Sorry! That letter isn't in here! Press Enter to Continue."});
                                        turn = false;
                                        System.in.read();
                                    }else{
                                        this.draw(new String[]{"Yes we have "+multiplier+" of those!","Press Enter to Continue."});
                                        currentPlayer.addToRoundScore(Integer.parseInt(spin) * multiplier);
                                        System.in.read();
                                    }
                            }
                            break;
                        case -1:
                            turn = false;
                            break;
                    }
                    if(wheel.isComplete()){
                        turn = false;
                    }
                }
            }
        }
        this.draw(new String[] {g.OPEN_ARCH,g.setPlayers(players),g.CLOSED_ARCH});
        stop();
    }

    @Override
    public void stop() {
        this.done = true;
    }

    @Override
    public void update(long elapsedTime) {

    }

    @Override
    public void draw(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
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
