package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

abstract class CastleMove extends Move{

    protected CastleMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
