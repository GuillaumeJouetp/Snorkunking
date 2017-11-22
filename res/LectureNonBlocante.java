                                           
import java.awt.event.KeyEvent;

class Star {
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
public class Starfield {
    public final static double WIN_WIDTH= 640.0;
    public final static double WIN_HEIGHT= 640.0;
    public final static int NB_STARS= 64;
    public final static double STAR_SIZE= 1./128;
    public final static int FPS= 1000;

    public static void main(String [] args){
        StdDraw.setXscale(0, WIN_WIDTH);
        StdDraw.setYscale(0, WIN_HEIGHT);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(STAR_SIZE);

        Star [] stars= new Star[NB_STARS];
        for (int i=0; i != stars.length; ++i){
            stars[i]= new Star();
        }

        while(true){
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2, "Utilisez les flèches de direction: ←, →, ↑, ↓");
            double deltaX=0;
            double deltaY=0;
            double deltaZ=1;

            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                deltaY= +1;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                deltaY= -1;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                deltaX= +1;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                deltaX= -1;
            }
            for(int i=0; i != stars.length; ++i){
                stars[i].move(deltaX, deltaY, deltaZ);
                draw(stars[i]);
            }
            // Could/should be improved
            StdDraw.show(1000/FPS);
        }
    }
    private static void draw(Star s){
        // we could move ex,ey, ez instead of all the stars !
        double ex= 0.;
        double ey= 0.;
        double ez= -1.;
        StdDraw.point ((ez*(s.getX()-ex)*WIN_WIDTH/2.0)/(ez+s.getZ()) + ex + WIN_WIDTH/2.0,
                       (ez*(s.getY()-ey)*WIN_HEIGHT/2.0)/ (ez+ s.getZ()) + ey + WIN_HEIGHT/2.0);
    }
}
