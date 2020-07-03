package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

abstract class CastleMove extends Move {

    protected final Rook castleRook;
    protected final int castleRookStart;
    protected final int castleRookDestination;

    /**
     * Constructor for CastleMove
     *
     * @param board
     * @param movedPiece
     * @param destinationCoordinate
     * @param castleRook
     * @param castleRookStart
     * @param castleRookDestination
     */

    public CastleMove(final Board board,
                      final Piece movedPiece,
                      final int destinationCoordinate,
                      final Rook castleRook,
                      final int castleRookStart,
                      final int castleRookDestination) {
        super(board, movedPiece, destinationCoordinate);
        this.castleRook = castleRook;
        this.castleRookStart = castleRookStart;
        this.castleRookDestination = castleRookDestination;
    }

    /**
     * Getter method for castleRook variable
     *
     * @return
     */

    public Rook getCastleRook() {
        return this.castleRook;
    }

    /**
     * Returns true for Castling move.
     *
     * @return
     */

    @Override
    public boolean isCastlingMove() {
        return true;
    }

    /**
     * Overridden method for execute. Executes a move by creating a builder class and building a new board.
     *
     * @return
     */

    @Override
    public Board execute() {

        final Board.Builder builder = new Board.Builder(); //Creates new board
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) { //Cycles active pieces
            if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) { //If not moved or castleRook
                builder.setPiece(piece); //Adds to builder class
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) { //Cycles opponents pieces
            builder.setPiece(piece); //Adds to builder
        }
        builder.setPiece(this.movedPiece.movePiece(this)); //adds moved piece
        //TODO look into the first move on normal pieces
        builder.setPiece(new Rook(this.castleRook.getPieceAlliance(), this.castleRookDestination)); //creates new rook
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance()); //sets move maker
        return builder.build(); //creates board
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.castleRook.hashCode();
        result = prime * result + this.castleRookDestination;
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof com.chess.engine.board.CastleMove)) {
            return false;
        }
        final com.chess.engine.board.CastleMove otherCastleMove = (com.chess.engine.board.CastleMove) other;
        return super.equals(otherCastleMove) && this.castleRook.equals(otherCastleMove.getCastleRook());
    }
}
