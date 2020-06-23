package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public final class KingSideCastleMove extends CastleMove {
    protected KingSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
