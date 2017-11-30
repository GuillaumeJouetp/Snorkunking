package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class DivingArea {
    public static int WIDTH = 96*Snorkunking.WIDTH/100 ;
    public static int HEIGHT = 78*Snorkunking.HEIGHT/100;
    public static int x = 2*Snorkunking.WIDTH/100;
    public static int y = 20*Snorkunking.HEIGHT/100;

    public static int NBLEVELS;
    private List<Cave> caves = new ArrayList<>();

    public DivingArea() {
        caves.add(new Cave(1));
        caves.add(new Cave(2));
        caves.add(new Cave(3));
        NBLEVELS = caves.get(0).getNbLevels() + caves.get(1).getNbLevels() + caves.get(2).getNbLevels();
        System.out.println("Nombre de niveaux total :"+ NBLEVELS);
    }

    public void drawDivingArea(Graphics graphics){
        graphics.setColor(Color.green);
        graphics.drawRect(x,y,WIDTH,HEIGHT);
    }


}
