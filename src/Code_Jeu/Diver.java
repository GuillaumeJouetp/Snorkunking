package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Diver {
    private int x;
    private int y;
    private String name;
    private int score;

    public Diver(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void dispDiver() throws SlickException {
        Image img = new Image("res/image/diver.png");
        img.draw(Snorkunking.WIDTH/2, Snorkunking.HEIGHT/2);
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
}
