package Code_Jeu;


import org.newdawn.slick.Graphics;
import java.util.List;

public class Oxygene {

    public int x,y;
    public int height,width;
    private int value;


    public Oxygene() {
       value = 2*DivingArea.NBLEVELS; // Define the startup oxygen area
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void drawBottle(Graphics graphics){

    }

    public void drawOxygen(Graphics graphics){

        graphics.drawString("Reserve d'oxygène : "+Integer.toString(value),20,20);
    }


}
