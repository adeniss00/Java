package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

}
