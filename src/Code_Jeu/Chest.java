package Code_Jeu;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.awt.Image;

public class Chest {


    public int x;
    public int y;
    private int value;



    public Chest(int CaveId) {
        //x = (int)((DivingArea.WIDTH-WIDTH+1)*Math.random())+WIDTH; // Random placement on the level
        x = (int)(((DivingArea.WIDTH-10*Snorkunking.WIDTH/100) - DivingArea.x +1)*Math.random())+DivingArea.x; // Random placement on the level
        switch (CaveId)
        {
            case 1:
                value = (int)((3-1 + 1)*Math.random())+ 1; // between 1 & 3 treasures

                break;
            case 2:
                value = (int)((8-5 + 1)*Math.random())+ 5; // between 5 & 8 treasures
                break;
            case 3:
                value = (int)((12-10 + 1)*Math.random())+ 10; // between 10 & 12 treasures
                break;
        }
    }


    public int getValue() {
        return value;
    }

    public void drawChest(int y)  throws SlickException {

        org.newdawn.slick.Image chest = new org.newdawn.slick.Image("res/image/chest.png");
        int WIDTH = 150*Level.HEIGHT/100;
        int HEIGHT = Level.HEIGHT;
        chest.draw(x,y,WIDTH,HEIGHT);

    }

}

