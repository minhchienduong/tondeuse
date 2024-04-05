package com.astrelya.tondeuse.model;

import com.astrelya.tondeuse.model.enums.Command;
import com.astrelya.tondeuse.model.enums.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.astrelya.tondeuse.model.enums.Orientation.N;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    private Lawn lawn;

    @BeforeEach
    void setUp() {
        lawn = new Lawn(5, 5);
    }

    @Test
    void testMowerExecutesCommandsCorrectly() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(1, 2, N);
        Mower mower = new Mower(position, Arrays.asList(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A), lawn);
        mower.executeCommands();

        assertEquals(1, mower.getPosition().getX(), "Mower's X position should be 1");
        assertEquals(3, mower.getPosition().getY(), "Mower's Y position should be 3");
        assertEquals(Orientation.N, mower.getPosition().getOrientation(), "Mower's orientation should be North");
    }

    @Test
    void testRotateRight() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(2, 2, N);
        Mower mower = new Mower(position, Arrays.asList(Command.D), lawn);
        mower.executeCommands();
        assertEquals(Orientation.E, mower.getPosition().getOrientation(), "Mower should be facing East after rotating right from North.");
    }

    @Test
    void testRotateLeft() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(2, 2, N);
        Mower mower = new Mower(position, Arrays.asList(Command.G), lawn);
        mower.executeCommands();
        assertEquals(Orientation.W, mower.getPosition().getOrientation(), "Mower should be facing West after rotating left from North.");
    }

    @Test
    public void testAdvanceNorth() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(2, 2, N);
        Mower mower = new Mower(position, Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(3, mower.getPosition().getY(), "Mower should have advanced North by one position.");
    }

    @Test
    public void testAdvanceSouth() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(2, 3, Orientation.S);
        Mower mower = new Mower(position, Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(2, mower.getPosition().getY(), "Mower should have advanced South by one position.");
    }

    @Test
    public void testAdvanceEast() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(2, 2, Orientation.E);
        Mower mower = new Mower(position, Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(3, mower.getPosition().getX(), "Mower should have advanced East by one position.");
    }

    @Test
    public void testAdvanceWest() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(3, 2, Orientation.W);
        Mower mower = new Mower(position, Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(2, mower.getPosition().getX(), "Mower should have advanced West by one position.");
    }

    @Test
    public void testAdvanceDoesNotExceedLawnBoundaries() {
        Lawn lawn = new Lawn(5, 5);
        Position position = new Position(5, 5, N);
        Mower mower = new Mower(position, Arrays.asList(Command.A), lawn);
        mower.executeCommands();
        assertEquals(5, mower.getPosition().getY(), "Mower should not move beyond the lawn's boundary.");
    }
}
