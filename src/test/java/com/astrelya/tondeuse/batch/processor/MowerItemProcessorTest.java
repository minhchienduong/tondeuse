package com.astrelya.tondeuse.batch.processor;

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

        Mower mower = new Mower(1, 2, 'N',
                Arrays.asList(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A),
                lawn);

        Mower processedMower = processor.process(mower);

        assertEquals(1, processedMower.getX(), "Mower's X position should be 1");
        assertEquals(3, processedMower.getY(), "Mower's Y position should be 3");
        assertEquals('N', processedMower.getOrientation(), "Mower's orientation should be North");

        assertEquals(mower, processedMower, "Should return the same Mower object after processing");
    }
}