package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Diver {
    private String name;
    private int score;

    public Diver(String name) {
        this.name = name;
    }

    public void dispDiver() throws SlickException {
        Image img = new Image("res/image/diver.png");
        img.draw(Snorkunking.WIDTH/2, Snorkunking.HEIGHT/2);
    }
}
