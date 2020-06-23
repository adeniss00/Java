package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class NullMove extends Move {
    protected NullMove() {
        super(null,null,-1);
    }

    @Override
    public Board execute() {
        throw new RuntimeException("cannot execute the null move");
    }
}
