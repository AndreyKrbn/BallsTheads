package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getDirection() {
    }

    @Test
    public void getNextRate() {
        int i = 10;
        while (i-->0) {
            System.out.println(Player.GetNextRate());
        }
    }
}