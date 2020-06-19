package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES ={-8,-1,1,8};

    public Rook(Alliance pieceAlliance, final int piecePosition){
        super(pieceAlliance, piecePosition);
    }


    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
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

    private static boolean isFirstColumnExclusion(final int currentPosition ,final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition]&&((candidateOffset==-1));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition ,final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition]&&((candidateOffset==1));
    }
}