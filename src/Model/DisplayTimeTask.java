package Model;

import java.util.TimerTask;

public class DisplayTimeTask extends TimerTask {
    GameField gameField;

    public DisplayTimeTask(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void run() {
      gameField.Display();
    }
}
