package mnkGame;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int match(Board board, int target) {
        int[] res = new int[2];
        int firstPlayer = 1;
        int gameCnt = 1;
        while (res[0] < target && res[1] < target) {
            log("Game number: " + gameCnt);
            int result = this.play(board, firstPlayer);
            if (result > 0) {
                res[result - 1]++;
            }
            board.clear();
            firstPlayer = (firstPlayer == 1 ? 2 : 1);
            gameCnt++;
        }
        int matchRes = res[0] == target ? 1 : 2;
        log("Player " + matchRes + " won match!");
        return (matchRes);
    }

    private int play(Board board, int firstPlayer) {
        log(board.toString());
        log("Player " + firstPlayer + " moves first");
        while (true) {
            final int result1 = move(board, player1, firstPlayer);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 3 - firstPlayer);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board + "\n");
        switch (result) {
            case WIN:
                log("Player " + no + " won game!");
                return no;
            case LOSE:
                log("Player " + no + " lose game!");
                return 3 - no;
            case DRAW:
                log("Draw");
                return 0;
            default:
                return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
