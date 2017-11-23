package Code_Jeu;

public class Shark {

    private int x ;
    private double y ;
    private int p;

    public Shark(int x, double y) {
        this.x = x;
        this.y = y;
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

}
