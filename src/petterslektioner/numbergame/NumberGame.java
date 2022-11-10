package petterslektioner.numbergame;

public class NumberGame {
    private final int correctNumber;
    private int numberOfGuesses;
    private int guessesLeft;
    private final int maxNumberOfGuesses;

    /**
     * first value is the minimum number, second value is the maximum number
     */
    private final int[] range = new int[2];

    public enum Difficulty {
        EASY("EASY"), MEDIUM("MEDIUM"), HARD("HARD"), IMPOSSIBLE("IMPOSSIBLE");

        private String difficulty;
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

        NumberGameGUI.currentGame = this;
        new NumberGameGUI().setVisible(true);
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    /**
     * @param guess Returns true if guess is correct
     */
    public boolean checkIfWin(int guess){
        guessesLeft -= 1;
        return guess == correctNumber;
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }

}
