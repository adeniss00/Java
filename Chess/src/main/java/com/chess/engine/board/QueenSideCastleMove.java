package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public final class QueenSideCastleMove extends CastleMove{
    protected QueenSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
