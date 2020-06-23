package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class PawnMove extends Move {
    protected PawnMove( final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
