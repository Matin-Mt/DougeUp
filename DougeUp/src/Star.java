import processing.core.PApplet;

import java.util.ArrayList;

public class Star {
    private static PApplet applet = Main.applet;

    private static ArrayList<Star> stars = Main.stars;

    private boolean canShow;

    private int xCoordinate;
    private int yCoordinate;

    private final int rColor = 240;
    private final int gColor = 27;
    private final int bColor = 12;

    public Star(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        canShow = true;
    }

    public void createStar() {
        for (int i = 0; i < 70; i++) {
            stars.add(new Star((int) applet.random(Main.windowWidth), (int) applet.random(Block.maxYCoordinate)));
        }
    }

    public static void showStar(int ySpeed) {
        if (!stars.isEmpty()) {
            for (var s : stars) {
                if (s.canShow()) {
                    applet.fill(s.rColor, s.gColor, s.bColor);
                    applet.circle(s.getXCoordinate(), s.getYCoordinate(), 5);
                    s.setYCoordinate(ySpeed + s.yCoordinate);
                }
            }
        }
    }

    public static void deleteStar(Star star) {
        star.setShow(false);
    }

    public boolean canShow() {
        return canShow;
    }

    public void setShow(boolean canShow) {
        this.canShow = canShow;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
