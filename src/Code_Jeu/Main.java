package Code_Jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    public static void main(String[] args){
        try{
            AppGameContainer appGameContainer = new AppGameContainer(new Snorkunking("Snorkunking"));
            appGameContainer.setDisplayMode(Snorkunking.WIDTH, Snorkunking.HEIGHT, false);
            appGameContainer.setTargetFrameRate(100);
            appGameContainer.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }



    }
}
