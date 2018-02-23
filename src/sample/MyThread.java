package sample;

import javafx.scene.shape.Circle;
//Класс не используется
public class MyThread implements Runnable {

    private  Circle circle;
    @Override
    public void run() {
        while (circle.getCenterX()<500-25) {
            circle.setCenterX(circle.getCenterX()+5);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    MyThread(Circle circle){
        this.circle = circle;
    }
}
