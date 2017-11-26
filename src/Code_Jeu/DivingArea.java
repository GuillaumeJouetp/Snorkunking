package Code_Jeu;

import java.util.ArrayList;
import java.util.List;

public class DivingArea {
    public static int NBLEVELS;
    private List<Cave> caves = new ArrayList<>();

    public DivingArea() {
        caves.add(new Cave(1));
        caves.add(new Cave(2));
        caves.add(new Cave(3));
        NBLEVELS = caves.get(0).getNbLevels() + caves.get(1).getNbLevels() + caves.get(2).getNbLevels();
    }


}
