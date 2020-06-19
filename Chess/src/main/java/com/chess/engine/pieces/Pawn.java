package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.MajorMove;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Pawn extends Piece {
	
	private final static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9, 16};
	
	public Pawn(final Alliance pieceAlliance, 
			final int piecePosition) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
			
			final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset); //applies offset to piece position, multiplies by 1 or -1 depending on alliance
			
			if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				continue;
			}
			
			if(currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
				//TODO more work to do here (deal with promotions)!!!!
				legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
			} else if(currentCandidateOffset == 16 && this.isFirstMove() && 
					(BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) || //if on the second row and black
					(BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) { //if on the seventh row and whit
				final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
				if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
				   !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				}  //sets the exception for white on eighth column and black on first column
			} else if(currentCandidateOffset == 7 && //if it's not on one of those columns
				      !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance().isWhite() ||
				       (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance().isBlack())))) {
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						//TODO more to do here
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					}
				} //sets the exception for white on first column and black on eighth column
			} else if(currentCandidateOffset == 9 && //if it's not on one of those columns
					!((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance().isWhite() ||
					 (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance().isBlack())))) {
				if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						//TODO more to do here
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					}
				}
			}
			
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString() {
		return Piece.PieceType.PAWN.toString();
	}
	
}
