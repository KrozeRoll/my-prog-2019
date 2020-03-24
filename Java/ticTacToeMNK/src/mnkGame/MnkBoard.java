package mnkGame;

import java.util.Arrays;
import java.util.Map;

public class MnkBoard extends Board implements Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    protected Cell[][] cells;
    private Cell turn;
    private final int m, n, k;
    private int emptyCells;


    public MnkBoard(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k <= 0) {
            throw new IllegalArgumentException("m, n, k must be positive numbers");
        }
        if (k > Math.max(m, n)) {
            throw new IllegalArgumentException("Invalid k (Imposible game)");
        }
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.m = m;
        this.n = n;
        this.k = k;
        emptyCells = m * n;
    }

    @Override
    public Position getPosition() {
        return new UnchangeableBoard(cells);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        emptyCells--;

        int inRow = countMarks(move.getRow(), move.getColumn(), 1, 0, move.getValue()) +
                    countMarks(move.getRow(), move.getColumn(), -1, 0, move.getValue()) + 1;

        int inCol = countMarks(move.getRow(), move.getColumn(), 0, 1, move.getValue()) +
                    countMarks(move.getRow(), move.getColumn(), 0, -1, move.getValue()) + 1;

        int inDiag1 = countMarks(move.getRow(), move.getColumn(), 1, 1, move.getValue()) +
                    countMarks(move.getRow(), move.getColumn(), -1, -1, move.getValue()) + 1;

        int inDiag2 = countMarks(move.getRow(), move.getColumn(), 1, -1, move.getValue()) +
                    countMarks(move.getRow(), move.getColumn(), -1, 1, move.getValue()) + 1;

        int empty = 0;

        if (inRow >= k || inCol >= k || inDiag1 >= k || inDiag2 >= k) {
            return Result.WIN;
        }

        if (emptyCells == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private int countMarks(int startR, int startC, int shiftR, int shiftC, Cell mark) {
        int ans = 0;
        int r = startR + shiftR;
        int c = startC + shiftC;
        Move move = new Move(r, c, mark);
        while (isValid(r, c) && getCell(r, c) == mark && ans < k) {
            ans++;
            r += shiftR;
            c += shiftC;
        }
        return ans;
    }

    public void clear() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = Cell.E;
            }
        }
        emptyCells = cells.length * cells[0].length;
    }

    private boolean isValid(int r, int c) {
        return 0 <= r && r < n
                && 0 <= c && c < m;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");

        for (int c = 0; c < m; c++) {
            sb.append(c);
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
