package Model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.random;

enum BallColor{
    BLUE,
    RED,
    BROWN,
    GREEN,
    GOLD,
    AQUA,
    ORANGE,
    GRAY,
    PINK
}

public class Ball {

   volatile int positionX;
   volatile int positionY;
    final static Queue<BallColor> BallColorQueue = new LinkedList<BallColor>(Arrays.asList(BallColor.values()));

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
        if (withCircle)
            this.circle = new Circle(FieldX(), FieldY(), 25, Color.valueOf(getNextColor()));
    }

    private int FieldX() {
        return 50 * positionX - 25;
    }

    private int FieldY() {
        return 50 * positionY - 25;
    }

    private static String getNextColor() {
        BallColor bc = BallColorQueue.poll();
        BallColorQueue.add(bc);
        return bc.toString();
    }

/*    public void NewPosition() {
        circle.setCenterX(FieldX());
        circle.setCenterY(FieldY());
    }*/
    public synchronized void AnimationMove() {

        Timeline timeline = new Timeline();

        timeline.setCycleCount(1);

        KeyValue kvx = new KeyValue(circle.centerXProperty(), FieldX());
        KeyFrame kfx = new KeyFrame(Duration.millis(1), kvx);
        timeline.getKeyFrames().add(kfx);

        KeyValue kvy = new KeyValue(circle.centerYProperty(), FieldY());
        KeyFrame kfy = new KeyFrame(Duration.millis(1), kvy);

        timeline.getKeyFrames().add(kfy);
        timeline.setOnFinished(new AnimationEventHandler(this));
        timeline.play();

        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void JoinAnimation(){
        this.notify();
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
        //return result;
        return 1;
    }

    public Ball clone() {
        return new Ball(this.positionX, this.positionY);
    }

    public void ChangeState(Ball ball) {
        this.positionX = ball.positionX;
        this.positionY = ball.positionY;
    }
}
