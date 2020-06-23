package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class PawnJump extends Move {
    protected PawnJump(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
