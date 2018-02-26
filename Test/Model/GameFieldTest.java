package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class GameFieldTest {

    @Test
    public void add() {
        int expected = 3;
        int actual;

        GameField field = new GameField(10, 10);
        field.balls.add(new Ball(1,2));
        field.balls.add(new Ball(2,1));
        field.balls.add(new Ball(1,1));
        field.balls.add(new Ball(1,1));

        actual = field.balls.size();
        assertEquals(expected, actual);
    }

    @Test
    //hashCode если объект-елемент меняет свое состояние то Hash должен быть константой тоесть не учитываться
    public void exist() {
        boolean expected = true;
        boolean actual;
        Set<Ball> field = new HashSet<Ball>();
        Ball b1 = new Ball(1, 2);
        Ball b2 = new Ball(2, 1);
        Ball b3 = new Ball(1, 1);
        field.add(b1);
        field.add(b2);
        field.add(b3);
        b2.positionX = 1;
        b3.positionX = 2;
        actual = field.contains(new Ball(2, 1));
        assertEquals(expected, actual);
    }
}