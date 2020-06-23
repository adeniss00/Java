package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;



public abstract class Piece {

	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	private final int cachedHashCode;

	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		this.pieceType=pieceType;
		//TODO more work here!!
		this.isFirstMove = false;
		this.cachedHashCode=computeHashCode();
	}

	private int computeHashCode() {
		int result =pieceType.hashCode();
		result=31*result+pieceAlliance.hashCode();
		result=31*result+piecePosition;
		result=31*result+(isFirstMove ? 1 : 0);
		return result;
	}

	@Override
	public boolean equals(final Object other) {
		if (this==other){
			return true;
		}
		if (!(other instanceof Piece)){
			return false;
		}
		final Piece otherPiece = (Piece) other;
		return piecePosition==otherPiece.getPiecePosition()
				&& pieceType==otherPiece.getPieceType()
				&&pieceAlliance==otherPiece.getPieceAlliance()
				&&isFirstMove==otherPiece.isFirstMove();
	}

	@Override
	public int hashCode() {
		return this.cachedHashCode;
	}

	public PieceType getPieceType(){
		return this.pieceType;
	}
	public int getPiecePosition() {
		return this.piecePosition;
	}

	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	public abstract Piece movePiece(Move move);
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	//takes a board and finds the legal moves for a piece on that board
	public abstract Collection<Move> calculateLegalMoves(final Board board);

}
