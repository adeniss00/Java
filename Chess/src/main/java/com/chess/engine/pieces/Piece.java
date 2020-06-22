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
	
	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		this.pieceType=pieceType;
		//TODO more work here!!
		this.isFirstMove = false;
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
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	//takes a board and finds the legal moves for a piece on that board
	public abstract Collection<Move> calculateLegalMoves(final Board board);

}
