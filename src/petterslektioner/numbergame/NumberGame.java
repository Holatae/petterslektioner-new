package petterslektioner.numbergame;

import javax.swing.*;

public class NumberGame {
    private final int correctNumber;
    private int numberOfGuesses;
    private int guessesLeft;
    private final int maxNumberOfGuesses;

    /**
     * first value is the minimum number, second value is the maximum number
     */
    private final int[] range = new int[2];
    private String currentGameURL = "localhost:8080";

    public String getCurrentGameURL() {
        return currentGameURL;
    }

    public void setCurrentGameURL(String currentGameURL) {
        this.currentGameURL = currentGameURL;
    }

    public enum Difficulty {
        EASY("EASY"), MEDIUM("MEDIUM"), HARD("HARD"), IMPOSSIBLE("IMPOSSIBLE");

        private final String difficulty;

        Difficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getDifficulty() {
            return difficulty;
        }
    }

    private final Difficulty currentDifficulty;

    public String getCurrentDifficulty(){
        return currentDifficulty.getDifficulty();
    }

    public NumberGame(Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                range[0] = 1;
                range[1] = 10;

                maxNumberOfGuesses = 6;
                currentDifficulty = Difficulty.EASY;
            }
            case MEDIUM -> {
                range[0] = 1;
                range[1] = 100;
                maxNumberOfGuesses = 4;
                currentDifficulty = Difficulty.MEDIUM;
            }
            case HARD -> {
                range[0] = 1;
                range[1] = 1000;

                maxNumberOfGuesses = 3;
                currentDifficulty = Difficulty.HARD;
            }
            case IMPOSSIBLE -> {
                range[0] = 1;
                range[1] = 10000;

                maxNumberOfGuesses = 2;
                currentDifficulty = Difficulty.IMPOSSIBLE;
            }
            default -> throw new IllegalStateException("Unexpected value: " + difficulty);
        }
        guessesLeft = maxNumberOfGuesses;
        correctNumber = (int) (Math.random() * (range[1] - range[0] + 1) + range[0]);

        while (true) {
            String url = JOptionPane.showInputDialog("Please enter a server leaderboard URL");
            if (checkIfURLIsValid(url)) {
                currentGameURL = url;
                break;
            }
        }
        NumberGameGUI.currentGame = this;
        new NumberGameGUI().setVisible(true);
    }

    private boolean checkIfURLIsValid(String url) {
        return HttpClientClass.checkIfValidServer(url);
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    /**
     * @param guess Returns true if guess is correct
     */
    public boolean checkIfWin(int guess) {
        guessesLeft -= 1;
        return guess == correctNumber;
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }

    public int[] getRange() {
        return range;
    }

    public int getMaxNumberOfGuesses() {
        return maxNumberOfGuesses;
    }

    public boolean isToLow(int guessInt){
        return guessInt < correctNumber;
    }
}
