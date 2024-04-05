package com.astrelya.tondeuse.model;

import com.astrelya.tondeuse.model.enums.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    private Lawn lawn;

    @BeforeEach
    void setUp() {
        lawn = new Lawn(5, 5);
    }

    @Test
    void testMowerExecutesCommandsCorrectly() {
        Mower mower = new Mower(1, 2, 'N', Arrays.asList(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A), lawn);
        mower.executeCommands();

        assertEquals(1, mower.getX());
        assertEquals(3, mower.getY());
        assertEquals('N', mower.getOrientation());
    }

    @Test
    public void testRotateRight() {
        Mower mower = new Mower(2, 2, 'N', Arrays.asList(Command.D), lawn);
        mower.executeCommands();
        assertEquals('E', mower.getOrientation(), "Mower should be facing East after rotating right from North.");
    }

    @Test
    public void testRotateLeft() {
        Mower mower = new Mower(2, 2, 'N', Arrays.asList(Command.G), lawn);
        mower.executeCommands();
        assertEquals('W', mower.getOrientation(), "Mower should be facing West after rotating left from North.");
    }

    @Test
    public void testAdvanceNorth() {
        Mower mower = new Mower(2, 2, 'N', Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(3, mower.getY(), "Mower should have advanced North by one position.");
    }

    @Test
    public void testAdvanceSouth() {
        Mower mower = new Mower(2, 3, 'S', Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(2, mower.getY(), "Mower should have advanced South by one position.");
    }

    @Test
    public void testAdvanceEast() {
        Mower mower = new Mower(2, 2, 'E', Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(3, mower.getX(), "Mower should have advanced East by one position.");
    }

    @Test
    public void testAdvanceWest() {
        Mower mower = new Mower(3, 2, 'W', Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(2, mower.getX(), "Mower should have advanced West by one position.");
    }

    @Test
    public void testAdvanceDoesNotExceedLawnBoundaries() {
        Mower mower = new Mower(5, 5, 'N', Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(5, mower.getY(), "Mower should not move beyond the lawn's boundary.");
    }
}
