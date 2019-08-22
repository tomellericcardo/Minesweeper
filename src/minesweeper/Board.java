package minesweeper;

import java.util.ArrayList;
import java.util.Random;


public class Board {
    
    private final int rows;
    private final int cols;
    private Cell[][] matrix;
    
    Board(int rows, int cols, double mineProbability) {
        this.rows = rows;
        this.cols = cols;
        this.generateMatrix(mineProbability);
    }
    
    private void generateMatrix(double mineProbability) {
        this.matrix = new Cell[this.rows][this.cols];
        Random random = new Random();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                boolean mine = false;
                if (random.nextFloat() < mineProbability) {
                    mine = true;
                }
                this.matrix[i][j] = new Cell(mine);
            }
        }
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.countNearbyMines(i, j);
            }
        }
    }
    
    private void countNearbyMines(int row, int col) {
        int mines = 0;
        Cell cell = matrix[row][col];
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && i < this.rows && j < this.cols) {
                    Cell currentCell = this.matrix[i][j];
                    if (currentCell != cell && currentCell.isMine()) {
                        mines++;
                    }
                }
            }
        }
        cell.setNearbyMines(mines);
    }

    public boolean touch(int[] touch) {
        boolean playing = true;
        Cell cell = this.matrix[touch[0]][touch[1]];
        if (cell.isMine()) {
            this.discoverBoard();
            playing = false;
        } else {
            cell.discover();
            if (cell.getNearbyMines() == 0) {
                ArrayList<Cell> ignore = new ArrayList();
                this.discoverNeighbours(touch[0], touch[1], ignore);
            }
            if (this.isClear()) {
                playing = false;
            }
        }
        return playing;
    }
    
    private void discoverBoard() {
        for (Cell[] row : this.matrix) {
            for (Cell cell : row) {
                cell.discover();
            }
        }
    }
    
    private void discoverNeighbours(int row, int col, ArrayList<Cell> ignore) {
        Cell cell = this.matrix[row][col];
        Cell currentCell;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && i < this.rows && j < this.cols) {
                    currentCell = this.matrix[i][j];
                    if (
                        currentCell != cell &&
                        !ignore.contains(currentCell)
                    ) {
                        if (!currentCell.isMine()) {
                            currentCell.discover();
                        }
                        if (currentCell.getNearbyMines() == 0) {
                            ignore.add(currentCell);
                            this.discoverNeighbours(i, j, ignore);
                        }
                    }
                }
            }
        }
    }
    
    private boolean isClear() {
        boolean isClear = true;
        for (int i = 0; i < this.rows && isClear; i++) {
            for (int j = 0; j < this.cols && isClear; j++) {
                Cell cell = this.matrix[i][j];
                if (!(cell.isDiscovered() || cell.isMine())) {
                    isClear = false;
                }
            }
        }
        return isClear;
    }
    
    @Override
    public String toString() {
        String string = "\n";
        for (int i = -1; i < this.rows; i++) {
            for (int j = -1; j < this.cols; j++) {
                if (i < 0) {
                    string += j + 1 + " ";
                } else if (j < 0) {
                    string += i + 1 + " ";
                } else {
                    Cell cell = this.matrix[i][j];
                    string += cell.toString();
                }
            }
            string += "\n";
        }
        return string;
    }
    
}
