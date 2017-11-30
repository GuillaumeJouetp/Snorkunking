package Code_Jeu;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Diver {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private int score = 0;
    private int nbTreasures = 0;
    private List<Chest> diverChests;

    public Diver(int x, int y, int width,int height, String name) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.name = name;
    }

    public void dispDiver() throws SlickException {
        Image diver = new Image("res/image/diver.png");
        diver.draw(Snorkunking.WIDTH/2, Snorkunking.HEIGHT/2);
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNbTreasures() {
        return nbTreasures;
    }
}
