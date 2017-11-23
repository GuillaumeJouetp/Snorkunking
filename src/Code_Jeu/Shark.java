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
            if(y>=140){
                p=1;
            }
        }
        if (p==1){
            y+=-0.3;
            if (y<=100){
                p=0;
            }
        }
        if (x > Snorkunking.WIDTH + 200) {
            x = -300;
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
        shark.draw(x - 150,(int)(y - 60),width,height);
    }
}
