import processing.core.PApplet;

public class Dummy {
    private static PApplet applet = Main.applet;
    private int xCoordinate;
    private int yCoordinate;

    public Dummy(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        showDummy(xCoordinate);
    }

    private void showDummy(int xCoordinate) {
        applet.fill(222, 31, 31);
        applet.rect(xCoordinate - 4, yCoordinate - 68, 8, 8); // head
        applet.rect(xCoordinate - 9, yCoordinate - 60, 18, 40); // body
        applet.rect(xCoordinate - 9, yCoordinate - 20, 6, 20); // left leg
        applet.rect(xCoordinate + 3, yCoordinate - 20, 6, 20); // right leg
        applet.rect(xCoordinate - 13, yCoordinate - 60, 4, 20); // left hand
        applet.rect(xCoordinate + 9, yCoordinate - 60, 4, 20); // right hand

    }

    public PApplet getApplet() {
        return applet;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }
}
