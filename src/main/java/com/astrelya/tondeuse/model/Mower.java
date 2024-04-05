package com.astrelya.tondeuse.model;

import com.astrelya.tondeuse.model.enums.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Mower {
    private int x;
    private int y;
    private char orientation;
    private List<Command> commands;
    private Lawn lawn;

    public void executeCommands() {
        for (Command command : commands) {
            move(command);
        }
    }

    private void move(Command command) {
        switch (command) {
            case D:
                rotateRight();
                break;
            case G:
                rotateLeft();
                break;
            case A:
                advance();
                break;
        }
    }

    private void rotateRight() {
        orientation = switch (orientation) {
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            case 'W' -> 'N';
            default -> orientation;
        };
    }


    private void rotateLeft() {
        orientation = switch (orientation) {
            case 'N' -> 'W';
            case 'W' -> 'S';
            case 'S' -> 'E';
            case 'E' -> 'N';
            default -> orientation;
        };
    }

    private void advance() {
        switch (orientation) {
            case 'N':
                if (y < lawn.getHeight()) y++;
                break;
            case 'S':
                if (y > 0) y--;
                break;
            case 'E':
                if (x < lawn.getWidth()) x++;
                break;
            case 'W':
                if (x > 0) x--;
                break;
        }
    }

}
