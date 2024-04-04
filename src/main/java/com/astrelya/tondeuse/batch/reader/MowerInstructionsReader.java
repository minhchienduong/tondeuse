package com.astrelya.tondeuse.batch.reader;

import com.astrelya.tondeuse.model.Command;
import com.astrelya.tondeuse.model.Lawn;
import com.astrelya.tondeuse.model.Mower;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class MowerInstructionsReader implements ItemReader<Mower>{

    private Resource inputFile;
    private BufferedReader reader;
    private Lawn lawn;

    public MowerInstructionsReader(Resource inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public Mower read() throws IOException {
        if (reader == null) {
            initializeReader();
        }

        String mowerPositionLine = reader.readLine();
        if (mowerPositionLine == null) {
            return null;
        }

        String mowerCommandsLine = reader.readLine();
        if (mowerCommandsLine == null) {
            throw new IOException("Unexpected end of file; expected mower commands.");
        }

        return createMowerWithCommands(mowerPositionLine, mowerCommandsLine);
    }

    private void initializeReader() throws IOException {
        reader = new BufferedReader(new FileReader(inputFile.getFile()));
        String lawnSizeLine = reader.readLine();
        if (lawnSizeLine == null) {
            throw new IOException("Input file is empty, expected lawn size.");
        }
        lawn = parseLawnSize(lawnSizeLine);
    }

    private Lawn parseLawnSize(String lawnSizeLine) {
        String[] parts = lawnSizeLine.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid lawn size format. Expected format: 'width height'");
        }
        return new Lawn(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    private Mower createMowerWithCommands(String position, String commands) {
        String[] positionParts = position.split(" ");
        if (positionParts.length != 3) {
            throw new IllegalArgumentException("Invalid mower position format. Expected format: 'x y orientation'");
        }

        int x = Integer.parseInt(positionParts[0]);
        int y = Integer.parseInt(positionParts[1]);
        char orientation = positionParts[2].charAt(0);

        List<Command> commandList = parseCommands(commands);

        Mower mower = new Mower(x, y, orientation, commandList, lawn);
        return mower;
    }

    private List<Command> parseCommands(String commands) {
        List<Command> commandList = new ArrayList<>();
        for (char commandChar : commands.toCharArray()) {
            commandList.add(Command.valueOf(String.valueOf(commandChar)));
        }
        return commandList;
    }
}
