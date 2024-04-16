package com.astrelya.tondeuse.batch.processor;

import com.astrelya.tondeuse.model.Position;
import com.astrelya.tondeuse.model.enums.Command;
import com.astrelya.tondeuse.model.Coordinate;
import com.astrelya.tondeuse.model.Mower;
import com.astrelya.tondeuse.model.enums.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

class MowerItemProcessorTest {

    private MowerItemProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new MowerItemProcessor();
    }

    @Test
    void testProcessExecutesCommandsCorrectly() throws Exception {

        Coordinate coordinate = new Coordinate(5, 5);

        Position initialPosition = new Position(1, 2, Orientation.N);

        Mower mower = new Mower(initialPosition,
                Arrays.asList(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A),
                coordinate);

        Mower processedMower = processor.process(mower);

        assertEquals(1, processedMower.getPosition().getX(), "Mower's X position should be 1");
        assertEquals(3, processedMower.getPosition().getY(), "Mower's Y position should be 3");
        assertEquals(Orientation.N, processedMower.getPosition().getOrientation(), "Mower's orientation should be North");

        assertEquals(mower, processedMower, "Should return the same Mower object after processing");
    }
}