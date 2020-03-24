package mnkGame;

public class PlayerBoard extends Board {
    private MnkBoard board;

    public PlayerBoard(int m, int n, int k) {
        board = new MnkBoard(m, n, k);
    }

    public PlayerBoard(MnkBoard board) {
        this.board = board;
    }

    @Override
    public Position getPosition() {
        return board.getPosition();
    }

    @Override
    public Cell getCell() {
        return board.getCell();
    }

    @Override
    public Result makeMove(Move move) {
        return this.board.makeMove(move);
    }

    @Override
    protected void clear() {
        this.board.clear();
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
