package com.chess.engine.board;

import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

public class PawnJump extends Move {
    protected PawnJump(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public Board execute() {
        final Builder builder=new Builder();
        for (final Piece piece: this.board.CurrentPlayer().getActivePieces()) {
            if(!(this.movedPiece.equals(piece))){
                builder.setPiece(piece);
            }
        }
        for (final Piece piece:this.board.CurrentPlayer().getActivePieces()) {
            builder.setPiece(piece);
        }
        final Pawn movedPawn=(Pawn) this.movedPiece.movePiece(this);
        builder.setPiece(movedPawn);
        builder.setEnPassantPawn(movedPawn);
        builder.setMoveMaker(this.board.CurrentPlayer().getOpponent().getAlliance());
        return builder.build();
    }
}
