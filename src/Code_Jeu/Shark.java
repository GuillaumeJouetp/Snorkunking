package Code_Jeu;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;



public class Shark {


    //test Maxence

    private int x ;
    private double y ;
    private int p;
    private int width;
    private int height;



    public Shark(int x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        p=0;

    }

    public void movementshark(){

        x+=1;

        if (p==0){
            y+=0.3;
            if(y>=14*Snorkunking.HEIGHT/70){
                p=1;
            }
        }
        if (p==1){
            y+=-0.3;
            if (y<=Snorkunking.HEIGHT/7){
                p=0;
            }
        }
        if (x > Snorkunking.WIDTH + 2*Snorkunking.WIDTH/7) {
            x = -3*Snorkunking.WIDTH/7;
        }
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void drawShark() throws SlickException{

        Image shark = new Image("res/image/shark.png");
        shark.draw(x - (width-width/4),(int)y - height/2,width,height);
    }
}
