package Code_Jeu;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Chest> chests = new ArrayList<>();

    public Level(int caveId) {
        chests.add(new Chest(caveId)); // One chest per level at the start of the game
    }
}
