package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public final class MajorMove extends Move{
    public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate){
        super(board,movedPiece,destinationCoordinate);
    }
}
