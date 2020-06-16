package com.chess.engine.board;

import com.chess.engine.pieces.BoardUtils;
import com.chess.engine.pieces.Piece;


import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;


public abstract class Tile{
    protected final int tileCoordinate;
    private static final Map<Integer,EmptyTile> EMPTY_TILES=createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        Map<Integer, EmptyTile> emptyTileMap = range(0, BoardUtils.NUM_TILES).boxed().collect(toMap(i -> i, EmptyTile::new));
        return emptyTileMap;
    }
    public static Tile createTile(final int tileCoordinate,final Piece piece){
        if(piece!=null){
            return new OccupiedTile(tileCoordinate,piece);
        }
        return EMPTY_TILES.get(tileCoordinate);
    }
    Tile(int tileCoordinate){
        this.tileCoordinate=tileCoordinate;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

}

