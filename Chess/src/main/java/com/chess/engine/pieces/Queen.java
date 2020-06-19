package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES ={-9,-8,-7,-1,1,7,8,9};

    public Queen( Alliance pieceAlliance,int piecePosition) {
        super(pieceAlliance, piecePosition);
    }
    @Override
    public Collection<Move> calculateLegalMoves(Board board){
        final List<Move> legalMoves=new ArrayList<>();
        for (final int candidateCoordinateOffset:CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)||isEighthColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)){
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
                break;
            }
        }
        return legalMoves;
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }
    private static boolean isFirstColumnExclusion(final int currentPosition ,final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition]&&((candidateOffset==-1)||(candidateOffset==-9)||(candidateOffset==7));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition ,final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition]&&((candidateOffset==1)||(candidateOffset==-7)||(candidateOffset==9));
    }
}
