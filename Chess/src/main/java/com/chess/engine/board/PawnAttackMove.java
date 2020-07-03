package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class PawnAttackMove extends AttackMove {

    /**
     * Constructor for Pawn Attack move.
     *
     * @param board
     * @param movedPiece
     * @param destinationCoordinate
     * @param attackedPiece
     */

    public PawnAttackMove(final Board board,
                          final Piece movedPiece,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof com.chess.engine.board.PawnAttackMove && super.equals(other);
    }

    @Override
    public String toString() {
        return BoardUtils.getPositionAtCoordinate(this.movedPiece.getPiecePosition()).substring(0, 1) + "X" +
                BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }

}
