package com.astrelya.tondeuse.batch.reader;

import com.astrelya.tondeuse.model.Mower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;


public class MowerInstructionsReaderTest {

    Resource mockResource = Mockito.mock(Resource.class);
    MowerInstructionsReader reader;

    @BeforeEach
    void setUp() throws Exception {
        mockResource = Mockito.mock(Resource.class);
    }


    @Test
    void testInitializeReaderThrowsIOExceptionForEmptyFile(@TempDir Path tempDir) throws IOException {
        File nonExistentFile = tempDir.resolve("non_existent_input.txt").toFile();
        Mockito.when(mockResource.getFile()).thenReturn(nonExistentFile);
        MowerInstructionsReader reader = new MowerInstructionsReader(mockResource);

        assertThrows(IOException.class, reader::read,
                "Expected IOException to be thrown due to empty or non-existent input file.");
    }


    @Test
    public void testReadValidInputFile() throws Exception {

        File mockFile = new File("src/test/resources/input1.txt");
        Mockito.when(mockResource.getFile()).thenReturn(mockFile);
        reader = new MowerInstructionsReader(mockResource);

        Mower mower = reader.read();
        assertNotNull(mower);

        assertEquals(1, mower.getPosition().getX(), "Mower's X position should be 1");
        assertEquals(2, mower.getPosition().getY(), "Mower's Y position should be 2");
        assertEquals('N', mower.getPosition().getOrientation(), "Mower's orientation should be North");
        assertEquals(9, mower.getCommands().size(), "Mower should have 9 commands");
        assertEquals(5, mower.getLawn().getHeight(), "Lawn's height should be 5");
        assertEquals(5, mower.getLawn().getWidth(), "Lawn's width should be 5");

        assertNull(reader.read(), "No more mowers should be available for reading");
    }

}