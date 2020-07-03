package com.chess.engine.board;

public final class NullMove extends Move {

    /**
     * Nulls move
     */

    public NullMove() {
        super(null, 65);
    }

    /**
     * Overriden execute method that stops NullMove being initialised.
     *
     * @return
     */

    @Override
    public Board execute() {
        throw new RuntimeException("Cannot execute the null move!");
    }

    @Override
    public int getCurrentCoordinate() {
        return -1;
    }

}
