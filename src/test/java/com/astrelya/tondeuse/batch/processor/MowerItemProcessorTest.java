package com.astrelya.tondeuse.batch.processor;

import com.astrelya.tondeuse.model.Position;
import com.astrelya.tondeuse.model.enums.Command;
import com.astrelya.tondeuse.model.Lawn;
import com.astrelya.tondeuse.model.Mower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

public class MowerItemProcessorTest {

    private MowerItemProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new MowerItemProcessor();
    }

    @Test
    void testProcessExecutesCommandsCorrectly() throws Exception {

        Lawn lawn = new Lawn(5, 5);

        Position initialPosition = new Position(1, 2, 'N');

        Mower mower = new Mower(initialPosition,
                Arrays.asList(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A),
                lawn);

        Mower processedMower = processor.process(mower);

        assertEquals(1, processedMower.getPosition().getX(), "Mower's X position should be 1");
        assertEquals(3, processedMower.getPosition().getY(), "Mower's Y position should be 3");
        assertEquals('N', processedMower.getPosition().getOrientation(), "Mower's orientation should be North");

        assertEquals(mower, processedMower, "Should return the same Mower object after processing");
    }
}