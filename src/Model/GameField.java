package Model;

import java.util.HashSet;
import java.util.Set;

public class GameField {
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    int sizeX;
    int sizeY;
    Set<Ball> balls;

    public GameField(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.balls = new HashSet<Ball>();
    }

    public synchronized boolean Add(Ball ball)
    {
        return balls.add(ball);
    }
    public boolean Exist(Ball ball)
    {
        //return balls.contains(ball);

        for (Ball i: balls) {
            if (i.equals(ball)) return true;
        }
        return false;
    }

    public void Display(){
        for (Ball i: balls) {
            i.AnimationMove();
        }
    }
}
