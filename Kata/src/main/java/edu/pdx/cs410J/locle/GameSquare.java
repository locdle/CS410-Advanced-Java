package edu.pdx.cs410J.locle;

/**
 * Created by locle on 7/23/14.
 */
public class GameSquare {

    boolean isPacMan = false;
    boolean isGhost = false;
    boolean hasDot = false;
    boolean isWall = false;
    boolean isPellet = false;

    public GameSquare(boolean isPacMan, boolean isGhost, boolean hasDot, boolean isWall, boolean isPellet) {
        this.isPacMan = isPacMan;
        this.isGhost = isGhost;
        this.hasDot = hasDot;
        this.isWall = isWall;
        this.isPellet = isPellet;
    }

    public void Display(){
        if(isPacMan){
            System.out.print("V");
        }
        else if(isGhost){
            System.out.print("G");
        }
        else if(hasDot){
            System.out.print(".");
        }
        else if(isWall){
            System.out.print("#");
        }
        else if(isPellet){
            System.out.print("o");
        }
        else{
            System.out.print(" ");
        }

    }

    public boolean isPacMan() {
        return isPacMan;
    }

    public void setPacMan(boolean isPacMan) {
        this.isPacMan = isPacMan;
    }

    public boolean isGhost() {
        return isGhost;
    }

    public void setGhost(boolean isGhost) {
        this.isGhost = isGhost;
    }

    public boolean isHasDot() {
        return hasDot;
    }

    public void setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean isWall) {
        this.isWall = isWall;
    }

    public boolean isPellet() {
        return isPellet;
    }

    public void setPellet(boolean isPellet) {
        this.isPellet = isPellet;
    }
}
