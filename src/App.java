import java.util.Scanner;

import game.FeedHandler;
import game.GameHandler;

public class App {

    public static void main(String[] args) throws Exception {
        new FeedHandler(new Scanner(System.in));

        new GameHandler();
    }
}
