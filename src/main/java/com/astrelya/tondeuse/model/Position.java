package com.astrelya.tondeuse.model;

import com.astrelya.tondeuse.model.enums.Orientation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position extends Coordinate {
    private Orientation orientation;
    public  Position(int x, int y, Orientation orientation) {
        super(x, y);
        this.orientation = orientation;
    }
}
