package Interface_graphique.LectureNonBlocante;

import java.awt.event.KeyEvent;

public class Star {
    final static double STEP= 0.01;
    private double x;
    private double y;
    private double z;
    /*
    public Star (double x, double y, double z){
        this.x= x;
        this.y= y;
        this.z= z;
    }
    */
    public Star (){
        x= Math.random()*2 -1;
        y= Math.random()*2 -1;
        z= Math.random()*2 -1;
    }

    public void move(double deltaX, double deltaY, double deltaZ){
        x = wrap(x + deltaX * STEP);
        y = wrap(y + deltaY * STEP);
        z = wrap(z + deltaZ * STEP);
    }
    // could be recursive (but this is not actually needed)
    private double wrap( double v){
        return (v >= 1.0) ? (v-2.0) : ((v < -1)? (v + 2.0) : v);
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
}