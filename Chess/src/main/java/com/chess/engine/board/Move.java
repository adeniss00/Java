package com.chess.engine.board;


import com.chess.engine.pieces.Piece;

public abstract class Move {
	
	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;
	public static final Move NULL_MOVE=new NullMove();

	protected Move(final Board board,
				   final Piece movedPiece,
				   final int destinationCoordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}
	public Piece getMovedPiece() {
		return this.movedPiece;
	}
	public int getDestinationCoordinate() {
		return this.destinationCoordinate;
	}
	public int getCurrentCoordinate(){
		return this.getMovedPiece().getPiecePosition();
	}
	public Board execute() {
		final Builder builder = new Builder();
		for (final Piece piece :this.board.currentPlayer().getActivePieces()) {
			if(!this.movedPiece.equals(piece)){
				builder.setPiece(piece);
			}
		}
		for (Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
			builder.setPiece(piece);
		}

		builder.setPiece(this.movedPiece.movePiece(this));
		builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		return builder.build();
	}
}
