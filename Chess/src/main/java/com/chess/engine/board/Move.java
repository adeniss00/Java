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

	@Override
	public int hashCode() {
		final int prime=31;
		int result=1;
		result= prime*result+this.destinationCoordinate;
		result= prime*result+this.movedPiece.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object other) {
		if (this==other){
			return true;
		}
		if(!(other instanceof Move)){
			return false;
		}
		final Move otherMove=(Move) other ;
		return getDestinationCoordinate()==otherMove.getDestinationCoordinate()&&
				getMovedPiece().equals(otherMove.getMovedPiece());
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
	public boolean isCastlingMove(){
		return false ;
	}
	public boolean isAttack(){
		return false;
	}
	public Piece getAttackedPiece(){
		return null;
	}
	public Board execute() {
		final Builder builder = new Builder();
		for (final Piece piece :this.board.CurrentPlayer().getActivePieces()) {
			if(!this.movedPiece.equals(piece)){
				builder.setPiece(piece);
			}
		}
		for (Piece piece : this.board.CurrentPlayer().getOpponent().getActivePieces()) {
			builder.setPiece(piece);
		}

		builder.setPiece(this.movedPiece.movePiece(this));
		builder.setMoveMaker(this.board.CurrentPlayer().getOpponent().getAlliance());
		return builder.build();
	}
}
