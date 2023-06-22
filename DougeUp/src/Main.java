import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet applet;
    public PFont font;

    public static ArrayList<Block> blocks = new ArrayList<>();
    public static ArrayList<Star> stars = new ArrayList<>();

    public static int windowWidth = 400;
    public static int windowLength = 700;
    public int sunXCoordinate = 50;

    public static boolean isOver;
    public int score = 0;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    @Override
    public void setup() {
        applet = this;
        isOver = false;
        font = createFont("Arial", 16, true);
        Block block = new Block(0, 0);
        block.createBlock();
        Star star = new Star(0, 0);
        star.createStar();
    }

    @Override
    public void settings() {
        size(windowWidth, windowLength);
        smooth(55);
    }

    @Override
    public void draw() {
        if (!isOver) {
            noStroke();
            wallpaper(sunXCoordinate);
            sunXCoordinate += 1;

            Dummy dummy = new Dummy(mouseX, windowLength - 30);

            Star.showStar(2);
            Block.showBlock(2);

            for (var b : blocks)
                if (doesCollide(b.getXCoordinate(), b.getYCoordinate(), dummy)
                        || doesCollide(b.getXCoordinate() + Block.blockWidth, b.getYCoordinate(), dummy)
                        || doesCollide(b.getXCoordinate(), b.getYCoordinate() + Block.blockLength, dummy)
                        || doesCollide(b.getXCoordinate() + Block.blockWidth, b.getYCoordinate() + Block.blockLength, dummy)
                        || doesCollide(b.getXCoordinate(), (2 * b.getYCoordinate() + Block.blockLength) / 2, dummy)
                        || doesCollide(b.getXCoordinate() + Block.blockWidth, (2 * b.getYCoordinate() + Block.blockLength) / 2, dummy)) {

                    isOver = true;
                }

            for (var s : stars)
                if (s.canShow()) {
                    if (doesCollide(s.getXCoordinate(), s.getYCoordinate(), dummy)) {
                        score += 1;
                        Star.deleteStar(s);
                    }
                }

            fill(146, 240, 58);
            rect(0, windowLength - 30, windowWidth, 30);
            fill(245, 21, 5);
            textSize(20);
            text("Score: " + score, 5, windowLength - 15);
        } else if (isOver) {
            lost();
        }
    }

    private void lost() {
        background(0);
        textAlign(CENTER);
        fill(245, 21, 5);
        textSize(32);
        text("Game Over", windowWidth / 2f, windowLength / 2f);
        textSize(24);
        text("Score: " + score, (windowWidth / 2f), (windowLength / 2f) + 30);
        text("Exit", (windowWidth / 2f), (windowLength / 2f) + 60);
        if (mousePressed)
            if (mouseButton == LEFT)
                if (mouseX >= (windowWidth / 2) - 30 && mouseX <= (windowWidth / 2) + 30)
                    if (mouseY >= (windowLength / 2) + 40 && mouseY <= (windowLength / 2) + 70)
                        exit();
    }

    private void wallpaper(int xCoordinate) {
        int yCoordinate = 40;

        background(75, 191, 199);

        // SUN
        fill(232, 207, 21);
        if (xCoordinate >= 30 && xCoordinate < windowWidth - 30) {
            circle(xCoordinate, yCoordinate, 30);
        } else if (xCoordinate >= windowWidth - 30 && xCoordinate < windowWidth + 30) {
            circle(xCoordinate, yCoordinate, 30);
            circle(xCoordinate - windowWidth, yCoordinate, 30);
        } else if (xCoordinate >= windowWidth + 30) {
            wallpaper(xCoordinate - windowWidth);
        }

        // CLOUDS
        fill(93, 65, 201);

        // CLOUD 1
        if (xCoordinate - 70 >= 19 && xCoordinate - 70 <= windowWidth - 19) {
            createCloud(xCoordinate - 70, yCoordinate - 10);
        } else if (xCoordinate - 70 >= windowWidth - 19 || xCoordinate - 70 <= windowWidth + 19) {
            createCloud(xCoordinate - 70, yCoordinate - 10);
            createCloud(xCoordinate - 70 - windowWidth, yCoordinate - 10);
        }

        // CLOUD 2
        if (xCoordinate >= 19 && xCoordinate + 90 <= windowWidth - 19) {
            createCloud(xCoordinate + 90, yCoordinate + 50);
        } else if (xCoordinate + 90 >= windowWidth - 19 || xCoordinate + 90 <= windowWidth + 19) {
            createCloud(xCoordinate + 90, yCoordinate + 50);
            createCloud(xCoordinate + 90 - windowWidth, yCoordinate + 50);
        }

        // CLOUD 3
        if (xCoordinate >= 19 && xCoordinate + 170 <= windowWidth - 19) {
            createCloud(xCoordinate + 170, yCoordinate + 15);
        } else if (xCoordinate + 170 >= windowWidth - 19 || xCoordinate + 170 <= windowWidth + 19) {
            createCloud(xCoordinate + 170, yCoordinate + 15);
            createCloud(xCoordinate + 170 - windowWidth, yCoordinate + 15);
        }
    }

    private void createCloud(int xCoordinate, int yCoordinate) {
        circle(xCoordinate - 19, yCoordinate,20); // most left
        circle(xCoordinate - 10, yCoordinate - 7,20); // upper left
        circle(xCoordinate - 7, yCoordinate + 5,20); // lower left
        circle(xCoordinate + 19, yCoordinate,20); // most right
        circle(xCoordinate + 7, yCoordinate - 7,20); // upper right
        circle(xCoordinate + 9, yCoordinate + 5,20); // lower right
    }

    private boolean doesCollide(int xCoordinate, int yCoordinate, Dummy dummy) {
        if (yCoordinate <= dummy.getYCoordinate() && yCoordinate > dummy.getYCoordinate() - 40) {
            return xCoordinate >= dummy.getXCoordinate() - 9 && xCoordinate <= dummy.getXCoordinate() + 9;

        } else if (yCoordinate <= dummy.getYCoordinate() - 40 && yCoordinate >= dummy.getYCoordinate() - 60) {
            return xCoordinate >= dummy.getXCoordinate() - 13 && xCoordinate <= dummy.getXCoordinate() + 13;

        } else if (yCoordinate < dummy.getYCoordinate() - 60 && yCoordinate >= dummy.getYCoordinate() - 68) {
            return xCoordinate >= dummy.getXCoordinate() - 4 && xCoordinate <= dummy.getXCoordinate() + 4;

        } else {
            return false;
        }
    }
}