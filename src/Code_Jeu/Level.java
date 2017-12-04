package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public static int WIDTH = DivingArea.WIDTH ;
    public static int HEIGHT;
    public static int x =DivingArea.x;
    public  int y;

    private List<Chest> chests = new ArrayList<>();

    public Level(int caveId) {

        chests.add(new Chest(caveId)); // One chest per level at the start of the game
    }

    public static void setHEIGHT(int HEIGHT) {
        Level.HEIGHT = HEIGHT;
    }

    public static void drawLevel(Graphics graphics,int y){
        graphics.drawRect(x,y,WIDTH,HEIGHT);

    }
}
