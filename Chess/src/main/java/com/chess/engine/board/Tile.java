package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {


    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    /**
     * Method that creates 64 tiles and returns an immutable copy as a map.
     */

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap();

        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    /**
     * If piece returns null will create a new occupied tile based on given coord and piece. Otherwise returns
     * empty tile coord.
     * @param tileCoordinate
     * @param piece
     * @return Tile
     */

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }


    /**
     * Tile constructor.
     *
     * @param tileCoordinate
     */


    protected Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    /**
     * Absract class methods
     */

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public int getTileCoordinate() {
        return this.tileCoordinate;
    }


}
