package com.chess.engine.pieces;

/**
 * Enum for Piece Types
 */
public enum PieceType {

    PAWN("P", 100) {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public boolean isRook() {
            return false;
        }
    },
    KNIGHT("N", 300) {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public boolean isRook() {
            return false;
        }
    },
    BISHOP("B", 300) {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public boolean isRook() {
            return false;
        }
    },
    ROOK("R", 500) {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public boolean isRook() {
            return true;
        }
    },
    QUEEN("Q", 900) {
        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public boolean isRook() {
            return false;
        }
    },
    KING("K", 10000) {
        @Override
        public boolean isKing() {
            return true;
        }

        @Override
        public boolean isRook() {
            return false;
        }
    };


    private int pieceValue;
    private String pieceName;


    /**
     * Constructor for PieceType
     *
     * @param pieceName
     */

    PieceType(final String pieceName, final int pieceValue) {
        this.pieceName = pieceName;
        this.pieceValue = pieceValue;
    }

    /**
     * toString method that returns piece name
     *
     * @return
     */
    @Override
    public String toString() {
        return this.pieceName;
    }

    public int getPieceValue() {
        return this.pieceValue;
    }


    public abstract boolean isKing();

    public abstract boolean isRook();
}
