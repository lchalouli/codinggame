package com.leila.codinggame;

import java.awt.Point;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ThorPowerOfLightTest {

    enum Dest {
        N(0,-1), NE(1,-1), E(1,0), SE(1,1), S(0,1), SW(-1,1), W(-1,0), NW(-1,-1), NONE(0,0);

        private int depX;
        private int depY;

        Dest(int depX, int depY) {
            this.depX = depX;
            this.depY = depY;
        }
    }

    @Test
    public void test() throws Exception {
        int lightX = 0;
        int lightY = 17;

        int initialTX = 31;
        int initialTY = 4;

        final Point lightPos = new Point(lightX, lightY);
        final Point thorPos = new Point(initialTX, initialTY);

        while (!thorPos.equals(lightPos)) {
            Dest dest = getDest(lightPos, thorPos);
            translate(thorPos, dest);

        }

    }

    @Test
    public void testDest() throws Exception {
        final Point thorPos = new Point(0, 0);
        Assertions.assertThat(getDest(new Point(1,0), thorPos)).isEqualTo(Dest.E);
        Assertions.assertThat(getDest(new Point(1,1), thorPos)).isEqualTo(Dest.SE);
        Assertions.assertThat(getDest(new Point(0,1), thorPos)).isEqualTo(Dest.S);
        Assertions.assertThat(getDest(new Point(1,-1), thorPos)).isEqualTo(Dest.NE);
        Assertions.assertThat(getDest(new Point(0,-1), thorPos)).isEqualTo(Dest.N);
        Assertions.assertThat(getDest(new Point(-1,0), thorPos)).isEqualTo(Dest.W);
        Assertions.assertThat(getDest(new Point(-1,1), thorPos)).isEqualTo(Dest.SW);
        Assertions.assertThat(getDest(new Point(-1,-1), thorPos)).isEqualTo(Dest.NW);


    }

    private static void translate(Point thorPos, Dest dest) {
        System.err.print("(" + dest + ") -----> " + print(thorPos) + " -> ");
        thorPos.translate(dest.depX, dest.depY);
        System.err.println(print(thorPos));
    }


    private static Dest getDest(Point lightPos, Point thorPos) {

        Dest result;
        int diffX = lightPos.x - thorPos.x;
        int diffY = lightPos.y - thorPos.y;

        int absDiffX = Math.abs(diffX);
        int absDiffY = Math.abs(diffY);

        if (diffX == 0 && diffY == 0) {
            return Dest.NONE;
        }

        if (diffX >= 0) {
            if (diffY >= 0){
                if (absDiffX == absDiffY) {
                    result = Dest.SE;
                } else if(absDiffX > absDiffY) {
                    result = Dest.E;
                } else {
                    result = Dest.S;
                }
            } else {
                if (absDiffX == absDiffY) {
                    result = Dest.NE;
                } else if(absDiffX > absDiffY) {
                    result = Dest.E;
                } else {
                    result = Dest.N;
                }
            }
        } else {
            if (diffY >= 0){
                if (absDiffX == absDiffY) {
                    result = Dest.SW;
                } else if(absDiffX > absDiffY) {
                    result = Dest.W;
                } else {
                    result = Dest.S;
                }
            } else {
                if (absDiffX == absDiffY) {
                    result = Dest.NW;
                } else if(absDiffX > absDiffY) {
                    result = Dest.W;
                } else {
                    result = Dest.N;
                }
            }
        }
        return result;
    }

    private static String print(Point point) {
        return String.format("[x=%s,y=%s]", point.x, point.y);
    }

}
