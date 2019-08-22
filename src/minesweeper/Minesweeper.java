package minesweeper;

import java.util.Scanner;


public class Minesweeper {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        View console = new ConsoleView(keyboard, System.out, System.err);
        Controller game = new Controller(9, 9, 0.15, console);
        game.start();
    }
    
}
