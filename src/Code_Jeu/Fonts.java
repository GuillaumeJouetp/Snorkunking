package Code_Jeu;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class Fonts {

    public static void setFont1 (Graphics graphics){

        graphics.setFont(new Font() {
            @Override
            public int getWidth(String s) {
                return 0;
            }

            @Override
            public int getHeight(String s) {
                return 0;
            }

            @Override
            public int getLineHeight() {
                return 0;
            }

            @Override
            public void drawString(float v, float v1, String s) {
            }

            @Override
            public void drawString(float v, float v1, String s, org.newdawn.slick.Color color) {
            }

            @Override
            public void drawString(float v, float v1, String s, org.newdawn.slick.Color color, int i, int i1) {
            }
        });
    }
}
