package minesweeper;

import java.io.PrintStream;
import java.util.Scanner;


public class ConsoleView implements View {
    
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    
    private final Scanner keyboard;
    private final PrintStream output;
    private final PrintStream error;
    
    ConsoleView(Scanner keyboard, PrintStream output, PrintStream error) {
        super();
        this.keyboard = keyboard;
        this.output = output;
        this.error = error;
    }

    @Override
    public void printError(String errorString) {
        error.println(errorString);
    }
    
    @Override
    public void showBoard(Board board) {
        this.output.println(board);
    }

    @Override
    public int[] getTouch() {
        int[] touch = new int[2];
        this.output.print("Enter a command: ");
        String command = this.keyboard.nextLine();
        String[] touchStrings = command.split(",");
        touch[0] = Integer.parseInt(touchStrings[0]) - 1;
        touch[1] = Integer.parseInt(touchStrings[1]) - 1;
        return touch;
    }
    
}
