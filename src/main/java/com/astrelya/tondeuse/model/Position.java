package com.astrelya.tondeuse.model;

import com.astrelya.tondeuse.model.enums.Orientation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Position {
    private int x;
    private int y;
    private Orientation orientation;
}
