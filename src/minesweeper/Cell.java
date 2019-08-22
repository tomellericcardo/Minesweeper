package minesweeper;


public class Cell {
    
    private final boolean mine;
    private boolean discovered = false;
    private int nearbyMines;
    
    Cell(boolean mine) {
        this.mine = mine;
    }
    
    public boolean isMine() {
        return this.mine;
    }
    
    public void discover() {
        this.discovered = true;
    }
    
    public boolean isDiscovered() {
        return this.discovered;
    }
    
    public void setNearbyMines(int mines) {
        this.nearbyMines = mines;
    }
    
    public int getNearbyMines() {
        return this.nearbyMines;
    }
    
    @Override
    public String toString() {
        String string = "";
        if (!this.discovered) {
            string += "+ ";
        } else {
            if (this.mine) {
                string += ConsoleView.RED;
                string += "*";
            } else if (this.nearbyMines > 0) {
                string += ConsoleView.GREEN;
                string += this.nearbyMines;
            } else {
                string += ConsoleView.GREEN;
                string += '.';
            }
            string += ConsoleView.RESET + " ";
        }
        return string;
    }
    
}
