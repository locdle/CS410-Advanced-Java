package edu.pdx.cs410J.locle;

/**
 * Created by locle on 7/23/14.
 */
public class GameBoard {

    int Score;
    int Dim;
    PacMan pacman = new PacMan();
    Ghost Inkie = new Ghost();
    Ghost Pinkie = new Ghost();
    Ghost Blinky = new Ghost();
    Ghost Bob = new Ghost();

    GameSquare[][] Level;

    public GameBoard(int Dimensions) {
        this.Dim = Dimensions;
        this.Level = new GameSquare[Dim][Dim];

    }


    public void drawPacMan(){
        int x = Dim/2;
        int y = Dim/2;
        Level[x][y].setPacMan(true);
    }

    public void DrawBoard(){
        for (int i = 0; i < Dim; i++) {
            for (int j = 0; j < Dim; j++) {
                Level[i][j] = new GameSquare(false,false,true,false,false);
                //SquareConstructor
            }
        }
        drawPacMan();
    }



    public void DisplayBoard(){
        for(int i = 0; i < Dim; ++i){
            System.out.println();
            for( int j = 0; j < Dim; ++j){
                Level[i][j].Display();
                System.out.print(" ");
            }
        }
    }



}
