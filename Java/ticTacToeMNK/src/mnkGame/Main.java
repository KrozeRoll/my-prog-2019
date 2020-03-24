package mnkGame;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(false, new RandomPlayer(), new RandomPlayer());
        final Scanner in = new Scanner(System.in);
        final PrintStream out = System.out;

        int result;


        int m = readInt(in);
        int n = readInt(in);
        int k = readInt(in);
        int target = readInt(in);

        //result = game.play(new PlayerBoard(m, n, k));
        result = game.match(new PlayerBoard(m, n, k), target);
        out.println("Game result: " + result);
    }

    public static int readInt(Scanner in) {
        if (in.hasNextInt()) {
            return in.nextInt();
        }
        in.next();
        throw new IllegalArgumentException("Integer number expected");
    }
}
