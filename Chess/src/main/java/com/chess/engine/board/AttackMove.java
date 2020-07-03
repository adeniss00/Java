package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class AttackMove extends Move {

    final Piece attackedPiece;

    /**
     * Constructor for AttackMove that is used if target destination contains an enemy piece
     */

    public AttackMove(final Board board,
                      final Piece movedPiece,
                      final int destinationCoordinate,
                      final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }


    /**
     * Overrides the hashcode method.
     *
     * @return
     */

    @Override
    public int hashCode() {
        return this.attackedPiece.hashCode() + super.hashCode();
    }

    /**
     * Overrides the Equals method.
     *
     * @param other
     * @return
     */

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof com.chess.engine.board.AttackMove)) {
            return false;
        }
        final com.chess.engine.board.AttackMove otherAttackMove = (com.chess.engine.board.AttackMove) other;
        return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
    }


    @Override
    public boolean isAttack() {
        return true;
    }

    /**
     * Getter method for Attacked Piece
     *
     * @return
     */

    @Override
    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }
}
