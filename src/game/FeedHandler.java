package game;

import java.util.Scanner;

public class FeedHandler {
    private static Scanner scanner;

    /**
     * Constructs a FeedHandler with the given Scanner.
     *
     * @param s the Scanner to be used for input
     */
    public FeedHandler(Scanner s) {
        scanner = s;
    }

    private static final String LENGTH_ERROR(int len) {
        return colorize("$REDERROR$RESET The target word is $GREEN" + len + "$RESET letters long");
    }

    private static final String WELCOME_MESSAGE() {
        return colorize("Welcome to $GREENWordle!$RESET\n\nGuess a $GREEN5$RESET-letter word.\n" + //
                        "When you enter an answer, letters will turn:\n" + //
                        "$GREENGreen$RESET: Right letter, right spot.\n" + //
                        "$YELLOWYellow$RESET: Right letter, wrong spot.\n" + //
                        "$REDRed$RESET: Letter not in the word.\n" + //
                        "Use the clues to guess the word in $GREEN6$RESET tries or less!");
    }

    private static final String WIN_MESSAGE() { 
        return colorize("You have $GREENWON$RESET WORDLE!"); 
    }

    private static final String LOSE_MESSAGE() { 
        return colorize("You have $REDLOST$RESET WORDLE!\nTry again another time..."); 
    }

    private static final String ANSI_RESET = "\u001B[0m"; 
    private static final String ANSI_GREEN_HIGHLIGHT = "\u001B[42m";
    private static final String ANSI_YELLOW_HIGHLIGHT = "\u001B[43m";
    private static final String ANSI_RED_HIGHLIGHT = "\u001B[41m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";

    private static final String[] colorKey = { 
        "$RESET", "$GREEN_HIGHLIGHT", "$YELLOW_HIGHLIGHT", "$RED_HIGHLIGHT", "$GREEN", "$YELLOW", "$RED"
    };
    private static final String[] colorValue = { 
        ANSI_RESET, ANSI_GREEN_HIGHLIGHT, ANSI_YELLOW_HIGHLIGHT, ANSI_RED_HIGHLIGHT, ANSI_GREEN, ANSI_YELLOW, ANSI_RED
    };

    /**
     * Colorizes the given string by replacing color keys with ANSI color codes.
     *
     * @param s the string to colorize
     * @return the colorized string
     */
    public static String colorize(String s) {
        for(int i = 0; i < colorKey.length; i++) {
            for(int j = 0; j <= s.indexOf(colorKey[i]); j++) {
                s = s.replace(colorKey[i], colorValue[i]);
            }
        }

        return s;
    }

    // This is a thread holding method...
    /**
     * Gets the next input from the user that matches the target word length.
     *
     * @param targetWord the word to match the input length against
     * @return the valid input string from the user
     */
    public static String getNextInput(String targetWord) {
        String s = "";
        while(true) {
            s = scanner.nextLine();

            if(targetWord.length() == s.length()) break;
            appendMessage(LENGTH_ERROR(targetWord.length()));

        }

        return s.toLowerCase();
    }

    /**
     * Appends an announcement message to the console.
     *
     * @param s the announcement message to append
     */
    public static void appendAnnouncement(String s) {
        System.out.println("\n"+s+"\n");
    }

    /**
     * Appends a message to the console.
     *
     * @param s the message to append
     */
    public static void appendMessage(String s) {
        System.out.println(s);
    }        

    /**
     * Appends an update to the console based on the provided character arrays.
     *
     * @param c the array of correct letters in the correct position
     * @param s the array of correct letters in the wrong position
     * @param w the array of wrong letters
     */
    public static void appendUpdate(char[] c, char[] s, char[] w) {
        String[] newFormat = new String[5];

        for(int i = 0; i < 5; i++) {
            if(c[i] != '\0') newFormat[i] = "$GREEN"+c[i]+"$RESET";
            if(s[i] != '\0') newFormat[i] = "$YELLOW"+s[i]+"$RESET";
            if(w[i] != '\0') newFormat[i] = w[i]+"";
        }

        String newString = "";
        for(int i = 0; i < newFormat.length; i++) {
            newString += "["+newFormat[i]+"]";
        }

        appendMessage(colorize(newString));
    }

    public static void startRound() {
        appendAnnouncement(WELCOME_MESSAGE());
    }

    /**
     * Ends the current round by displaying a win or lose message.
     *
     * @param win true if the user won the round, false otherwise
     */
    public static void endRound(Boolean win) {
        if(win) appendAnnouncement(WIN_MESSAGE());
        else appendAnnouncement(LOSE_MESSAGE());

        appendAnnouncement(colorize("Play $GREENagain$RESET? ($GREENy$RESET/$REDn$RESET)"));
        String r = scanner.nextLine();

        if(r.toLowerCase().equals("y")) new GameHandler();
    }
}
