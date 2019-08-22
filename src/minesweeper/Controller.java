package minesweeper;


public class Controller {
    
    private final Board board;
    private final View view;
    
    Controller(int rows, int cols, double mineProbability, View view) {
        this.board = new Board(rows, cols, mineProbability);
        this.view = view;
    }

    void start() {
        boolean playing = true;
        int[] touch;
        while (playing) {
            this.view.showBoard(board);
            try {
                touch = this.view.getTouch();
                playing = this.board.touch(touch);
            } catch (Exception e) {
                this.view.printError("ERROR: invalid input!");
            }
        }
        this.view.showBoard(board);
    }
    
}
