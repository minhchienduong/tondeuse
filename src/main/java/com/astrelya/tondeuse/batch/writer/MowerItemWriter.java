package com.astrelya.tondeuse.batch.writer;

import com.astrelya.tondeuse.model.Mower;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MowerItemWriter implements ItemWriter<Mower> {

    private final String outputFilePath;

    public MowerItemWriter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void write(Chunk<? extends Mower> chunk) throws Exception {
        List<? extends Mower> mowers = chunk.getItems();
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath, true)))) {
            for (Mower mower : mowers) {
                writer.printf("%d %d %s%n", mower.getX(), mower.getY(), mower.getOrientation());
            }
        } catch (IOException e) {
            throw new Exception("Unable to write to file: " + outputFilePath, e);
        }
    }

}
