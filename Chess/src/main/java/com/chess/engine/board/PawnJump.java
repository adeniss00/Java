package com.chess.engine.board;

import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

public final class PawnJump extends Move {

    /**
     * Constructor for Pawn jump.
     *
     * @param board
     * @param movedPiece
     * @param destinationCoordinate
     */

    public PawnJump(final Board board,
                    final Piece movedPiece,
                    final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    /**
     * Overridden method used to execute a move. Creates new builder and passes in moved Pawn.
     *
     * @return
     */

    @Override
    public Board execute() {
        final Board.Builder builder = new Board.Builder(); //Creates new builder object
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this); //Sets selected pawn as variable
        builder.setPiece(movedPawn); //Sets pawn as piece to be moved
        builder.setEnPassantPawn(movedPawn);
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance()); //sets move maker
        return builder.build(); //builds new board
    }

    @Override
    public String toString() {
        return BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }

}
