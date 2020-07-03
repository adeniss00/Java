package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import static com.chess.engine.board.Board.*;

public abstract class Move {

    protected final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;
    protected final boolean isFirstMove;

    protected static final Move NULL_MOVE = new NullMove();


    /**
     * Constructor for Move class
     *
     * @param board
     * @param movedPiece
     * @param destinationCoordinate
     */

    protected Move(final Board board,
                   final Piece movedPiece,
                   final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
        this.isFirstMove = movedPiece.isFirstMove();
    }

    protected Move(final Board board,
                   final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = null;
        this.isFirstMove = false;
    }

    /**
     * Method to return coordinate of moved piece
     *
     * @return
     */

    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }

    /**
     * Override method that creates a hashcode.
     *
     * @return
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.destinationCoordinate;
        result = prime * result + this.movedPiece.hashCode();
        result = prime * result + this.movedPiece.getPiecePosition();
        return result;
    }


    /**
     * Override method that compares two objects.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        final Move otherMove = (Move) other;
        return getCurrentCoordinate() == otherMove.getCurrentCoordinate() &&
                getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
                getMovedPiece().equals(otherMove.getMovedPiece());

    }

    public Board getBoard() {
        return this.board;
    }

    /**
     * Returns the destination coordinate
     *
     * @return
     */

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }


    /**
     * Getter Method for Moved Piece
     *
     * @return
     */
    public Piece getMovedPiece() {
        return this.movedPiece;
    }


    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }


    /**
     * Method used for making Moves. Creates total new board.
     *
     * @return
     */

    public Board execute() {
        final Builder builder = new Builder(); //Creates new builder object
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) { //Gets all active pieces
            if (!this.movedPiece.equals(piece)) { //If not the moved piece
                builder.setPiece(piece); //Adds to builder
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) { //Gets opponents pieces
            builder.setPiece(piece); //adds to builder
        }
        //Move the moved piece!
        builder.setPiece(this.movedPiece.movePiece(this)); //Adds moved piece to builder
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance()); //Sets next player
        return builder.build(); // builds new board
    }


    public static final class PawnEnPassantAttackMove extends PawnAttackMove {

        /**
         * Constructor for En Passant Move
         *
         * @param board
         * @param movedPiece
         * @param destinationCoordinate
         * @param attackedPiece
         */

        public PawnEnPassantAttackMove(final Board board,
                                       final Piece movedPiece,
                                       final int destinationCoordinate,
                                       final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }

        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof PawnEnPassantAttackMove && super.equals(other);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                if (!piece.equals(this.getAttackedPiece())) {
                    builder.setPiece(piece);
                }
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();

        }
    }


}




