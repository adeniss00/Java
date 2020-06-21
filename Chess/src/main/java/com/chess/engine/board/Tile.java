package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import static java.util.stream.Collectors.toMap;

public abstract class Tile {
	//can only be used by it's subclasses, can only be set once at construction time
	protected final int tileCoordinate;

	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {


		Map<Integer, EmptyTile> emptyTileMap = IntStream.range(0, 64).boxed().collect(toMap(i -> i, EmptyTile::new));
		return emptyTileMap;
	}

	public static Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}

	protected Tile(final int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}

	public abstract boolean isTileOccupied();

	public abstract Piece getPiece();

}
