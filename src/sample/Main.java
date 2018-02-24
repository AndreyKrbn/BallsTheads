package sample;

import Model.GameField;
import Model.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.concurrent.locks.ReentrantLock;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        GameField field = new GameField(10,10);

        Scene scene = new Scene(root, 50*field.getSizeX(), 50*field.getSizeY(), Color.BLACK);

        ReentrantLock locker = new ReentrantLock();

        Player p1 = new Player(field, locker);
        Player p2 = new Player(field, locker);
        Player p3 = new Player(field, locker);
        Player p4 = new Player(field, locker);
        Player p5 = new Player(field, locker);
        Player p6 = new Player(field, locker);
        Player p7 = new Player(field, locker);
        Player p8 = new Player(field, locker);
        Player p9 = new Player(field, locker);

        root.getChildren().addAll(p1.getBall().getCircle());
        root.getChildren().addAll(p2.getBall().getCircle());
        root.getChildren().addAll(p3.getBall().getCircle());
        root.getChildren().addAll(p4.getBall().getCircle());
        root.getChildren().addAll(p5.getBall().getCircle());
        root.getChildren().addAll(p6.getBall().getCircle());
        root.getChildren().addAll(p7.getBall().getCircle());
        root.getChildren().addAll(p8.getBall().getCircle());
        root.getChildren().addAll(p9.getBall().getCircle());

        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(p1)).start();
        (new Thread(p2)).start();
        (new Thread(p3)).start();
        (new Thread(p4)).start();
        (new Thread(p5)).start();
        (new Thread(p6)).start();
        (new Thread(p7)).start();
        (new Thread(p8)).start();
        (new Thread(p9)).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
