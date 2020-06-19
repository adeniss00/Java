package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

//defines occupied tiles
public final class OccupiedTile extends Tile {
    //declares that there is a piece on an occupied tile
    private final Piece pieceOnTile;

    OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    @Override
    public String toString() {
        return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                getPiece().toString();
    }

    //declare the tile has a piece
    @Override
    public boolean isTileOccupied() {
        return true;
    }

    //declare the tile has a piece
    @Override
    public Piece getPiece() {
        return this.pieceOnTile;
    }


}
