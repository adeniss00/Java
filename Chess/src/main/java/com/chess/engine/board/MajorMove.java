package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

/**
 * Constructor for MajorMove that is used if target destination has no 'enemy' piece
 */

public final class MajorMove extends Move {

    public MajorMove(final Board board,
                     final Piece movedPiece,
                     final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof com.chess.engine.board.MajorMove && super.equals(other);
    }

    @Override
    public String toString() {
        return movedPiece.getPieceType().toString() + BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }

}
