package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ScorePanel {

    public static Image SCOREPANEL;
    public static int x = 2 * Snorkunking.WIDTH / 100;
    public static int y = 2 * Snorkunking.WIDTH / 100;
    public static int WIDTH = 96 * Snorkunking.WIDTH / 100;
    public static int HEIGHT = 14 * Snorkunking.HEIGHT / 100;

    public static void drawScorePanel()throws SlickException {
        SCOREPANEL = new Image("res/image/diver.png");
        SCOREPANEL.draw(x, y, WIDTH, HEIGHT);

    }
}
