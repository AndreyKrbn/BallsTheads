package Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AnimationEventHandler implements EventHandler<ActionEvent> {

    Ball ball;

    public AnimationEventHandler(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void handle(ActionEvent event) {
        ball.JoinAnimation();
    }
}
