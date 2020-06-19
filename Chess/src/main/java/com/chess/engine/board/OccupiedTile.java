package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public final class OccupiedTile extends Tile{
    private final Piece pieceOnTile;
    OccupiedTile(int tileCoordinate, Piece pieceOnTile){
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }
    @Override
    public boolean isTileOccupied(){
        return true;
    }
    @Override
    public Piece getPiece(){

        return this.pieceOnTile;
    }

    @Override
    public String toString() {
        if (getPiece().getPieceAlliance().isBlack()){
            return getPiece().toString().toLowerCase();
        }
        return getPiece().toString();
    }
}
