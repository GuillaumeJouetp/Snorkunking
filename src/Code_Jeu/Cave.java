package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Cave {

    private static int WIDTH = DivingArea.WIDTH;
    private static int HEIGHT;
    private int x = DivingArea.x;
    private int y;
    private int nbLevels;
    private List<Level> levels = new ArrayList<>();

    public Cave(int id) {
        switch (id)
        {
            case 1:
                nbLevels = (int)((12-9 + 1)*Math.random())+ 9; // between 9 & 12 levels in the 1st cave
                for (int i = 0; i < nbLevels; i++) {
                    levels.add(new Level(id));

                }
                break;
            case 2:
                nbLevels = (int)((9-6 + 1)*Math.random())+ 6; // between 6 & 9 levels in the 1st cave
                for (int i = 0; i < nbLevels; i++) {
                    levels.add(new Level(id));
                }
                break;
            case 3:
                nbLevels = (int)((6-3 + 1)*Math.random())+ 3; // between 3 & 6 levels in the 1st cave
                for (int i = 0; i < nbLevels; i++) {
                    levels.add(new Level(id));
                }
                break;
        }
        System.out.println("Nombre de niveau cave : "+ nbLevels);
    }


    public void drawCave(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.fillRect(x , y, WIDTH, HEIGHT);
    }

    public int getNbLevels() {
        return nbLevels;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}
