package Model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
    final static Queue<Integer> ratesQueue = new LinkedList<Integer>(Arrays.asList(new Integer[] {100, 200, 300, 400, 500}));
    //final static Queue<Integer> ratesQueue = new LinkedList<Integer>(Arrays.asList(new Integer[] {0}));

    public Player(GameField field, ReentrantLock lock) {
        this.field = field;
        this.timeToSleep = GetNextRate();
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
                //make Move
                locker.lock();
                if (field.Exist(cl)) {
                    continue;
                }
                ball.ChangeState(cl);
            } finally {
                locker.unlock();
            }
            i = 0;
        }
    }

    //Случайное направление
    public Direction GetDirection() {
        Random r = new Random();
        return Direction.values()[r.nextInt(4)];
    }

    //Cкорость
    public static int GetNextRate() {
        Integer i= ratesQueue.poll();
        ratesQueue.add(i);
        return i;
    }

}

