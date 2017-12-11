package Code_Jeu;


import org.newdawn.slick.Color;
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
        graphics.setColor(Color.blue);
        graphics.drawRect(2 * Snorkunking.WIDTH / 100, 2 * Snorkunking.WIDTH / 100, 96 * Snorkunking.WIDTH / 100, 14 * Snorkunking.HEIGHT / 100);

    }

    public void drawOxygen(Graphics graphics){

        graphics.drawString("Reserve d'oxygène : "+Integer.toString(value),20,20);
    }


}
