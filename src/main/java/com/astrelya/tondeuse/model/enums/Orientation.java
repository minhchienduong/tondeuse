package com.astrelya.tondeuse.model.enums;

public enum Orientation {
    N, E, S, W;

    public Orientation rotateRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }

    public Orientation rotateLeft() {
        return switch (this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }
}
