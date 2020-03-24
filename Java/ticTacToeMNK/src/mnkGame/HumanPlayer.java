package mnkGame;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Enter row and column");

            int r, c;
            while (true) {
                try {
                    r = Main.readInt(in);
                    c = Main.readInt(in);
                    break;
                } catch (IllegalArgumentException e) {
                    out.println("Write integer number");
                }
            }
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
