package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public final class KingSideCastleMove extends CastleMove {

    /**
     * Constructor for KingSideCastleMove
     *
     * @param board
     * @param movedPiece
     * @param destinationCoordinate
     * @param castleRook
     * @param castleRookStart
     * @param castleRookDestination
     */

    public KingSideCastleMove(final Board board,
                              final Piece movedPiece,
                              final int destinationCoordinate,
                              final Rook castleRook,
                              final int castleRookStart,
                              final int castleRookDestination) {
        super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
    }

    @Override
    public boolean equals(final Object other){
            return this == other || other instanceof com.chess.engine.board.KingSideCastleMove && super.equals(other);
    }

    /**
     * Overrides toString method to return the recognised code for a kingside castle move.
     *
     * @return
     */

    @Override
    public String toString() {
        return "0-0";
    }

}
