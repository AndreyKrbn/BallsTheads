package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

enum BallColor{
    BLUE,
    RED,
    BROWN,
    GREEN,
    GOLD
}

public class Ball {

    int positionX;
    int positionY;
    static Queue<BallColor> BallColorQueue;
    static{
        BallColorQueue = new LinkedList<BallColor>();
        BallColorQueue.addAll(Arrays.asList(BallColor.values()));
    }

    public Circle getCircle() {
        return circle;
    }

    Circle circle = null;

    public Ball(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Ball(int positionX, int positionY, boolean withCircle) {
        this(positionX, positionY);
        if(withCircle) {
            BallColor bc = BallColorQueue.poll();
            this.circle = new Circle(FieldX(), FieldY(), 25, Color.valueOf(bc.toString()));
            BallColorQueue.add(bc);
        }
    }

    private int FieldX(){
        return 50*positionX-25;
    }

    private int FieldY(){
        return 50*positionY-25;
    }

    public void AnimationMove(){
        circle.setCenterX(FieldX());
        circle.setCenterY(FieldY());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ball ball = (Ball) o;

        if (positionX != ball.positionX) return false;
        return positionY == ball.positionY;
    }

    @Override
    public int hashCode() {
        int result = positionX;
        result = 31 * result + positionY;
        return result;
    }

    public Ball clone()  {
        return new Ball(this.positionX, this.positionY);
    }

    public void ChangeState(Ball ball){
        this.positionX = ball.positionX;
        this.positionY= ball.positionY;
    }
}
