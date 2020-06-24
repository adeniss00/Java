package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

abstract class CastleMove extends Move{


    protected final Rook castleRook;
    protected final int castleRookStart;
    protected final int castleRookDestination;
    protected CastleMove(Board board, Piece movedPiece, int destinationCoordinate,final int castleRookDestination,final Rook castleRook,final int castleRookStart) {
        super(board, movedPiece, destinationCoordinate);
        this.castleRook=castleRook;
        this.castleRookStart=castleRookStart;
        this.castleRookDestination=castleRookDestination;
    }
    public Rook getCastleRook() {
        return castleRook;
    }
    @Override
    public boolean isCastlingMove(){
        return true;
    }
    @Override
    public Board execute(){
        final Builder builder = new Builder();
        for (final Piece piece: this.board.currentPlayer().getActivePieces()) {
            if(!(this.movedPiece.equals(piece) && !this.castleRook.equals(piece))){
                builder.setPiece(piece);
            }
        }
        for (final Piece piece:this.board.currentPlayer().getActivePieces()) {
            builder.setPiece(piece);
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setPiece(new Rook(this.castleRook.getPieceAlliance(),this.castleRookDestination));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        return builder.build();
    }

}
