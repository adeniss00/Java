package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

//defines empty tiles
public final class EmptyTile extends Tile {
    //declares that there is no piece on an empty tile
    EmptyTile(final int coordinate) {
        super(coordinate);
    }

    @Override
    public String toString() {
        return "-";
    }

    //declares that tile has no piece
    @Override
    public boolean isTileOccupied() {
        return false;
    }

    //declares that tile has no piece
    @Override
    public Piece getPiece() {
        return null;
    }

}
