package edu.pdx.cs410J.locle;
import java.awt.*;
import java.lang.Object.*;
/**
 * Created by locle on 7/23/14.
 */
public class Main {
    public static void main(String[] args) {
       GameBoard level = new GameBoard(20);

        System.out.println(level.Dim);
        level.DrawBoard();
        level.DisplayBoard();

    }
}
