package game;

public class GameHandler {

    private static final String[] wordleWords = {
        "apple", "brave", "chair", "dream", "flock",
        "grass", "house", "light", "month", "quest",
        "storm", "angle", "beach", "crisp", "dance",
        "ember", "frost", "glove", "heart", "jelly",
        "knife", "layer", "magic", "night", "ocean",
        "plane", "quiet", "river", "score", "taste",
        "umbra", "voice", "water", "yield", "zebra",
        "bluff", "cargo", "dwell", "equip", "flash",
        "giant", "haste", "ivory", "joint", "karma",
        "latch", "manor", "novel", "otter", "prank"
    };

    private static int currentWordValue;

    public GameHandler() {
        setCurrentWordValue((int)(Math.random() * 50));

        startRound();
    }

    public static void startRound() {
        FeedHandler.startRound();
        for(int i = 0; i < 6; i++) {
            String guess = FeedHandler.getNextInput(getCurrentWord());

            char[] correct = new char[5];
            char[] semiCorrect = new char[5];
            char[] wrong = new char[5];
            for(int j = 0; j < guess.length(); j++) {
                if(guess.charAt(j) == getCurrentWord().charAt(j)) correct[j] = guess.charAt(j);
                else if(getCurrentWord().contains(guess.charAt(j)+"")) semiCorrect[j] = guess.charAt(j);
                else wrong[j] = guess.charAt(j);
            }

            FeedHandler.appendUpdate(correct, semiCorrect, wrong);

            if(guess.equals(getCurrentWord())) {
                FeedHandler.endRound(true);

                return;
            }
        }

        FeedHandler.endRound(false);
    }

    public static String getCurrentWord() {
        return wordleWords[currentWordValue];
    }

    public static void setCurrentWordValue(int i) {
        currentWordValue = i;
    }

    public String toString() {
        return "a game is currently running using the word: "+getCurrentWord();
    }
}
