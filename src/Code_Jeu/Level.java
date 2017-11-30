package Code_Jeu;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public static int WIDTH = DivingArea.WIDTH ;
    public static int HEIGHT =DivingArea.HEIGHT/(DivingArea.NBLEVELS+4);
    public static int x =DivingArea.x;
    public static int y;

    private List<Chest> chests = new ArrayList<>();

    public Level(int caveId,int y) {
        switch (caveId){

        }
        this.y = y + DivingArea.y;
        chests.add(new Chest(caveId)); // One chest per level at the start of the game
    }
}
