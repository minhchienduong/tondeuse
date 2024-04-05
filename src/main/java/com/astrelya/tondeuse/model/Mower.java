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

    private Position position;
    private List<Command> commands;
    private Lawn lawn;

    public Mower(int x, int y, char orientation, List<Command> commands, Lawn lawn) {
        this.position = new Position(x, y, orientation);
        this.commands = commands;
        this.lawn = lawn;
    }

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
        this.position.setOrientation(switch (this.position.getOrientation()) {
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            case 'W' -> 'N';
            default -> this.position.getOrientation();
        });
    }

    private void rotateLeft() {
        this.position.setOrientation(switch (this.position.getOrientation()) {
            case 'N' -> 'W';
            case 'W' -> 'S';
            case 'S' -> 'E';
            case 'E' -> 'N';
            default -> this.position.getOrientation();
        });
    }

    private void advance() {
        switch (this.position.getOrientation()) {
            case 'N':
                if (this.position.getY() < lawn.getHeight()) this.position.setY(this.position.getY() + 1);
                break;
            case 'S':
                if (this.position.getY() > 0) this.position.setY(this.position.getY() - 1);
                break;
            case 'E':
                if (this.position.getX() < lawn.getWidth()) this.position.setX(this.position.getX() + 1);
                break;
            case 'W':
                if (this.position.getX() > 0) this.position.setX(this.position.getX() - 1);
                break;
        }
    }

}
