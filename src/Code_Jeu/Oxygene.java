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


    }

    public void drawOxygen(Graphics graphics){

        graphics.setColor(Color.blue);
        graphics.drawString("("+Integer.toString(value)+")",ScorePanel.x + 5*ScorePanel.WIDTH/100,ScorePanel.y + 70*ScorePanel.HEIGHT/100);
        graphics.fillRect(ScorePanel.x + 10*ScorePanel.WIDTH/100,ScorePanel.y + 70*ScorePanel.HEIGHT/100,value*2*ScorePanel.WIDTH/120,10*ScorePanel.HEIGHT/100);
    }


}
