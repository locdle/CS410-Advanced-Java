package edu.pdx.cs410J.locle;

/**
 * Created by locle on 7/23/14.
 */
public abstract class MOB {

    int xPos;
    int yPos;

    public void drawMOB(int xPos, int yPos){

    }

    public enum direction {
        North(1), East(2), South(3), West(4);
        private int direction;

        direction(int i) {
            this.direction = i;
        }

        public void Move() {

        }
    }
}
