package com.astrelya.tondeuse.model;

import com.astrelya.tondeuse.model.enums.Command;
import com.astrelya.tondeuse.model.enums.Orientation;
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
    private Coordinate lawn;

    public Mower(int x, int y, Orientation orientation, List<Command> commands, Coordinate lawn) {
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
            case D -> rotateRight();
            case G -> rotateLeft();
            case A -> advance();
        }
    }

    private void rotateRight() {
        this.position.setOrientation(this.position.getOrientation().rotateRight());
    }

    private void rotateLeft() {
        this.position.setOrientation(this.position.getOrientation().rotateLeft());
    }

    private void advance() {
        Position nextPosition = new Position(position.getX(), position.getY(), position.getOrientation());

        switch (position.getOrientation()) {
            case N -> nextPosition.setY(nextPosition.getY() + 1);
            case S -> nextPosition.setY(nextPosition.getY() - 1);
            case E -> nextPosition.setX(nextPosition.getX() + 1);
            case W -> nextPosition.setX(nextPosition.getX() - 1);
        }

        if(lawn.canMove(nextPosition)) {
            position.setX(nextPosition.getX());
            position.setY(nextPosition.getY());
        }
    }

}
