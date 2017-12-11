package Code_Jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class DivingArea {
    public static int WIDTH = 96*Snorkunking.WIDTH/100 ;
    public static int HEIGHT = 78*Snorkunking.HEIGHT/100;
    public static int x = 2*Snorkunking.WIDTH/100;
    public static int y = 20*Snorkunking.HEIGHT/100;

    public static int NBLEVELS;
    public List<Cave> caves = new ArrayList<>();

    public DivingArea() {
        caves.add(new Cave(1));
        caves.add(new Cave(2));
        caves.add(new Cave(3));
        NBLEVELS = caves.get(0).getNbLevels() + caves.get(1).getNbLevels() + caves.get(2).getNbLevels();
        System.out.println("Nombre de niveaux total :"+ NBLEVELS);

        Level.HEIGHT = (HEIGHT/(NBLEVELS));
        System.out.println("pas: "+Level.HEIGHT);
        initChests();

    }

    /*public void drawDivingArea(Graphics graphics){
        graphics.setColor(Color.green);
        graphics.drawRect(x,y,WIDTH,HEIGHT);
    }*/

    public void drawLevels(Graphics graphics){
        int p=0;
        for (int j = 0; j < caves.size(); j++) {
            switch (j){
                case 0:
                    graphics.setColor(Color.green);
                    break;
                case 1:
                    graphics.setColor(Color.orange);
                    break;
                case 2:
                    graphics.setColor(Color.magenta);
                    break;
            }
            for (int i = 0; i <caves.get(j).getNbLevels() ; i++) {
                caves.get(j).getLevels().get(i).y = y + p*Level.HEIGHT;
                p++;
                Level.drawLevel(graphics,caves.get(j).getLevels().get(i).y);

            }
        }
    }

    public void drawChests() throws SlickException{
        //int p=0;
        for (int j = 0 ; j < caves.size(); j++) {
            for (int i = 0 ; i <caves.get(j).getNbLevels() ; i++) {
                for (int k = 0; k < caves.get(j).getLevels().get(i).getChests().size(); k++) {
                    //caves.get(j).getLevels().get(i).getChests().get(k).y = y + p * Level.HEIGHT;
                    //p++;
                    caves.get(j).getLevels().get(i).getChests().get(k).drawChest(caves.get(j).getLevels().get(i).getChests().get(0).y);
                }
            }
        }
    }

    public void initChests() {
        int p=0;
        for (int j = 0 ; j < caves.size(); j++) {
            for (int i = 0 ; i <caves.get(j).getNbLevels() ; i++) {
                for (int k = 0; k < caves.get(j).getLevels().get(i).getChests().size(); k++) {
                    caves.get(j).getLevels().get(i).getChests().get(k).y = y + p * Level.HEIGHT;
                    p++;
                }
            }
        }
    }



}
