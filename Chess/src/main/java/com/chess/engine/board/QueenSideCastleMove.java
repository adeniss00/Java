package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public final class QueenSideCastleMove extends CastleMove{
    public QueenSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate,final Rook castleRook,final int castleRookStart,final int castleRookDestination) {
        super(board, movedPiece, destinationCoordinate,castleRookDestination,castleRook,castleRookStart);
    }
    @Override
    public String toString() {
        return "O-O-O";
    }
}
