package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public final class PawnMove extends Move {

    /**
     * Constructor for PawnMove class.
     *
     * @param board
     * @param movedPiece
     * @param destinationCoordinate
     */

    public PawnMove(final Board board,
                    final Piece movedPiece,
                    final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof com.chess.engine.board.PawnMove && super.equals(other);
    }

    @Override
    public String toString() {
        return BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }

}
