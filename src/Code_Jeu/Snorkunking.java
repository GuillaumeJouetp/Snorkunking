package Code_Jeu;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class Snorkunking extends BasicGame {

    public static int WIDTH = 1200;
    public static int HEIGHT = 900 ;

    List<Cave> caves = new ArrayList<>();
    List<Diver> divers = new ArrayList<>();

    public Snorkunking(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {


    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
/*
        score.disp;
        oxygene.disp();
        caves.disp();
        water.disp();
        divers.disp();
*/
        //Diver myDiver = new Diver("Diver 1");
        //myDiver.dispDiver();

    }
}
