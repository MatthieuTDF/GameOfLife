package gameoflife;

import java.util.Arrays;
import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private int sizeGrid;
    private Random rd;

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
    }

    private void generateRandomInitialState() {
        cells = new Cell[sizeGrid][sizeGrid];

        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                cells[i][j] = new Cell(rd.nextBoolean());
            }
        }
    }

    public void generateNextState() {
        int nbNeighbourCellsAlive;

        Cell[][] newCells = new Cell[sizeGrid][sizeGrid];

        for (int indexLine = 0; indexLine < sizeGrid; indexLine++) {
            for (int indexColumn = 0; indexColumn < sizeGrid; indexColumn++) {
                nbNeighbourCellsAlive = 0;

                if (indexLine < sizeGrid - 1 && cells[indexLine + 1][indexColumn].isAlive()) { nbNeighbourCellsAlive += 1; }

                if (indexLine < sizeGrid - 1 && indexColumn > 0 && cells[indexLine + 1][indexColumn - 1].isAlive()) { nbNeighbourCellsAlive += 1; }

                if (indexLine < sizeGrid - 1 && indexColumn < sizeGrid - 1 && cells[indexLine + 1][indexColumn + 1].isAlive()) { nbNeighbourCellsAlive += 1; }

                if (indexLine > 0 && cells[indexLine - 1][indexColumn].isAlive()) { nbNeighbourCellsAlive += 1; }

                if (indexLine > 0 && indexColumn > 0 && cells[indexLine - 1][indexColumn - 1].isAlive()) { nbNeighbourCellsAlive += 1; }

                if (indexLine > 0 && indexColumn < sizeGrid - 1 && cells[indexLine - 1][indexColumn + 1].isAlive()) { nbNeighbourCellsAlive += 1; }

                if (indexColumn > 0 && cells[indexLine][indexColumn - 1].isAlive()) { nbNeighbourCellsAlive += 1; }

                if (indexColumn < sizeGrid - 1 && cells[indexLine][indexColumn + 1].isAlive()) { nbNeighbourCellsAlive += 1; }

                newCells[indexLine][indexColumn] = new Cell(Cell.processState(cells[indexLine][indexColumn].isAlive(), nbNeighbourCellsAlive));
            }
        }
        this.cells = newCells;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Cell[] cell : cells) {
            str.append(Arrays.toString(cell)
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "\n"));
        }
        return str.substring(0, str.length() - 1);
    }
}
