import processing.core.PApplet;
import java.util.ArrayList;

public class Block {
    private static PApplet applet = Main.applet;
    private static ArrayList<Block> blocks = Main.blocks;
    public static int maxYCoordinate;

    public final static int blockWidth = 20;
    public final static int blockLength = 60;

    private int xCoordinate;
    private int yCoordinate;
    private final int rColor;
    private final int bColor;
    private final int gColor;

    public Block(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.rColor = (int) applet.random(255);
        this.bColor = (int) applet.random(255);
        this.gColor = (int) applet.random(255);
    }

    public void createBlock() {
        applet.fill(0);

        int lowYCoordinate = -blockLength;

        for (int i = 0; i < 30; i++) {
            // X Coordinates
            int numberOfBlocks = (int) applet.random(4, 7);
            int scapeWidth = 60;

            int sumOfScapeWidth = scapeWidth * (numberOfBlocks - 1);
            final int highXCoordinate = (Main.windowWidth - sumOfScapeWidth) / numberOfBlocks;

            int lowCoordinate = 0;
            int highCoordinate = highXCoordinate;

            for (int j = 1; j <= numberOfBlocks; j++) {

                if (highCoordinate >= Main.windowWidth - blockWidth)
                    highCoordinate = Main.windowWidth - blockWidth;

                blocks.add(new Block( (int) applet.random( lowCoordinate, highCoordinate ), (int) applet.random(lowYCoordinate - 80, lowYCoordinate) ));

                lowCoordinate = (j * (highXCoordinate + scapeWidth));
                highCoordinate += (highXCoordinate + scapeWidth);
            }

            lowYCoordinate -= 190;
            maxYCoordinate = lowYCoordinate - 88;
        }
    }

    public static void showBlock(int ySpeed) {
        if (!blocks.isEmpty()) {
            for (var b: blocks) {
                applet.fill(b.rColor, b.bColor, b.gColor);
                applet.rect(b.xCoordinate, b.yCoordinate, blockWidth, blockLength);
                b.setYCoordinate(ySpeed + b.yCoordinate);
            }
        }
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
