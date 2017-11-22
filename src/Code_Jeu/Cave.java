package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Cave {

    private int width;
    private int height;
    private int x,y;
    private int id;


    public Cave(int width, int height, int id) {


    }


    public void dispCave(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.fillRect(x , y, width, height);
    }
}
