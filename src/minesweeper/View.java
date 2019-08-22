package minesweeper;


public interface View {
    
    public void printError(String errorString);
    public void showBoard(Board board);
    public int[] getTouch();
    
}
