package mnkGame;

public class UnchangeableBoard extends MnkBoard {
    public UnchangeableBoard(Cell[][] cells) {
        super(cells[0].length, cells.length, 1);
        this.cells = new Cell[cells.length][cells[0].length];
        for (int r = 0; r < cells.length; r++) {
            this.cells[r] = cells[r].clone();
        }
    }
}
