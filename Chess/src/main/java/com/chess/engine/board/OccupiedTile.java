package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

/**
 * Occupied Tile Class
 */

public final class OccupiedTile extends Tile {

    private final Piece pieceOnTile;

    /**
     * Occupied Tile constructor
     *
     * @param tileCoordinate
     * @param pieceOnTile
     */

    OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    /**
     * toString method that returns lowercase if Alliance is black
     *
     * @return
     */

    @Override
    public String toString() {
        return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                getPiece().toString();
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.pieceOnTile;
    }
}
