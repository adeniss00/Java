package com.chess.engine.board;


import com.chess.engine.pieces.Piece;

public abstract class Move {
	
	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;


	protected Move(final Board board,
				   final Piece movedPiece,
				   final int destinationCoordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}

	public int getDestinationCoordinate() {
		return destinationCoordinate;
	}

    public abstract Board execute();
}
