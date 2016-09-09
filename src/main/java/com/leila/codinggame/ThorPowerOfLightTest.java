package com.leila.codinggame;

import java.awt.Point;

import org.junit.Test;

public class ThorPowerOfLightTest {

    enum Dest {
        N(0,1), NE(1,1), E(1,0), SE(1,-1), S(0,-1), SW(-1,-1), W(-1,0), NW(-1,1), NONE(0,0);

        private int depX;
        private int depY;

        Dest(int depX, int depY) {
            this.depX = depX;
            this.depY = depY;
        }
    }

    @Test
    public void test() throws Exception {
        int lightX = 39;
        int lightY = 17;

        int initialTX = 0;
        int initialTY = 0;

        final Point lightPos = new Point(lightX, lightY);
        final Point thorPos = new Point(initialTX, initialTY);

        while (!thorPos.equals(lightPos)) {
            translate(thorPos, getDest(lightPos, thorPos));
        }


    }

    private void translate(Point thorPos, Dest dest) {
        System.err.print(print(thorPos) + " -> ");
        thorPos.translate(dest.depX, dest.depY);
        System.err.println(print(thorPos));
    }


    private Dest getDest(Point lightPos, Point thorPos) {

        Dest result;
        int diffX = lightPos.x - thorPos.x;
        int diffY = lightPos.y - thorPos.y;
        if (diffX == 0 && diffY == 0) {
            return Dest.NONE;
        }

        if (diffX >= 0) {
            if (diffY >= 0){
                if (diffX == diffY) {
                    result = Dest.NE;
                } else if(diffX >= diffY) {
                    result = Dest.E;
                } else {
                    result = Dest.N;
                }
            } else {
                if (diffX == diffY) {
                    result = Dest.SE;
                } else if(diffX >= diffY) {
                    result = Dest.S;
                } else {
                    result = Dest.E;
                }
            }
        } else {
            if (diffY >= 0){
                if (diffX == diffY) {
                    result = Dest.NW;
                } else if(diffX >= diffY) {
                    result = Dest.W;
                } else {
                    result = Dest.N;
                }
            } else {
                if (diffX == diffY) {
                    result = Dest.SW;
                } else if(diffX >= diffY) {
                    result = Dest.S;
                } else {
                    result = Dest.W;
                }
            }
        }
        return result;
    }

    private String print(Point point) {
        return String.format("[x=%s,y=%s]", point.x, point.y);
    }

}
