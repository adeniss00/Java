package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

/**
 * Empty tile Class
 */

public final class EmptyTile extends Tile {

    EmptyTile(final int coordinate) {
        super(coordinate);
    }


    @Override
    public String toString() {
        return "-";
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
