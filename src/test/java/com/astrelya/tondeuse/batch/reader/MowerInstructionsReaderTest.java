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
        assertEquals(1, mower.getX());
        assertEquals(2, mower.getY());
        assertEquals('N', mower.getOrientation());
        assertEquals(9, mower.getCommands().size());
        assertEquals(5, mower.getLawn().getHeight());
        assertEquals(5, mower.getLawn().getWidth());

        assertNull(reader.read());
    }

}