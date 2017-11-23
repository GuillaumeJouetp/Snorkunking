package Code_Jeu;

public class Money {
    private int y;
    private int speed;

    public Money( int speed) {
        y = 0;
        this.speed = speed;
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }
}
