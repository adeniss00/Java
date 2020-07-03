package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public final class QueenSideCastleMove extends CastleMove {

    /**
     * Constructor for the QueenSideCastleMove
     *
     * @param board
     * @param movedPiece
     * @param destinationCoordinate
     * @param castleRook
     * @param castleRookStart
     * @param castleRookDestination
     */

    public QueenSideCastleMove(final Board board,
                               final Piece movedPiece,
                               final int destinationCoordinate,
                               final Rook castleRook,
                               final int castleRookStart,
                               final int castleRookDestination) {
        super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
    }


    @Override
    public boolean equals(final Object other){
        return this == other || other instanceof com.chess.engine.board.QueenSideCastleMove && super.equals(other);
    }

    /**
     * Overrides the toString to return the offical identifier for queen side castling.
     *
     * @return
     */
    @Override
    public String toString() {
        return "0-0-0";
    }
}
