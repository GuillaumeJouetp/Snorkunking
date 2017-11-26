package Code_Jeu;

import java.util.List;

public class Oxygene {
    private int oxygeneLevel;
    private List<OxygeneBar> oxygenBars;

    public Oxygene() {
       oxygeneLevel = 2*DivingArea.NBLEVELS; // Define the startup oxygen area
        for (int i = 0; i <oxygeneLevel ; i++) {
            oxygenBars.add(new OxygeneBar());

        }
    }

    public List<OxygeneBar> getOxygenBars() {
        return oxygenBars;
    }
}
