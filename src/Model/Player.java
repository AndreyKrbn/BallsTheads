package Model;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

enum Direction {
    Top,
    Left,
    Right,
    Bottom
}

public class Player implements Runnable {
    public Ball getBall() {
        return ball;
    }

    Ball ball;
    GameField field;
    long timeToSleep;
    ReentrantLock locker;

    public Player(GameField field, ReentrantLock lock) {
        this.field = field;
        this.timeToSleep = GetRandomRate();
        this.locker = lock;
        while (!this.field.Add(getNewBall(field))) ;
    }

    private Ball getNewBall(GameField field) {
        this.ball = new Ball(new Random().nextInt(field.sizeX) + 1, new Random().nextInt(field.sizeY) + 1, true);
        return this.ball;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            if (i > 15) ball.circle.setRadius(100);
            try { if(i < 2)
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            Ball cl = ball.clone();
            switch (GetDirection()) {
                case Left:
                    if (cl.positionX == 1) continue;
                    cl.positionX--;
                    break;
                case Right:
                    if (cl.positionX == field.sizeX) continue;
                    cl.positionX++;
                    break;
                case Top:
                    if (cl.positionY == 1) continue;
                    cl.positionY--;
                    break;
                case Bottom:
                    if (cl.positionY == field.sizeY) continue;
                    cl.positionY++;
                    break;
            }
            try {
                locker.lock();
                if (field.Exist(cl)) {
                    //locker.unlock();
                    continue;
                }
                ball.ChangeState(cl);
            } finally {
                locker.unlock();
            }
            i = 0;

            // make move
            ball.AnimationMove();
        }
    }

    //Случайное направление
    public Direction GetDirection() {
        Random r = new Random();
        return Direction.values()[r.nextInt(4)];
    }

    //Случайная скорость
    public int GetRandomRate() {
        Random r = new Random();
        return new int[]{100, 200, 300, 400, 500}[r.nextInt(5)];
    }

}

