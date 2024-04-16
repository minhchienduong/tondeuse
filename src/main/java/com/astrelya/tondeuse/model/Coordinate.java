package com.astrelya.tondeuse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate {
    protected int x;
    protected int y;

    public boolean canMove(Coordinate nextCoordinate) {
        return nextCoordinate.getX() >= 0 && nextCoordinate.getX() <= this.x &&
                nextCoordinate.getY() >= 0 && nextCoordinate.getY() <= this.y;
    }

}
