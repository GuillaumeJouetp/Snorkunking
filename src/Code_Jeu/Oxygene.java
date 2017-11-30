package Code_Jeu;

import java.util.List;

public class Oxygene {
    private int oxygeneLevel;

    public Oxygene() {
       oxygeneLevel = 2*Snorkunking.myDivingArea.NBLEVELS; // Define the startup oxygen area
    }

    public int getOxygeneLevel() {
        return oxygeneLevel;
    }

}
