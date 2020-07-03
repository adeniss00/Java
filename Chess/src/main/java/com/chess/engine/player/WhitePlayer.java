package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {

    /**
     * Constructor for White Player
     *
     * @param board
     * @param whiteStandardLegalMoves
     * @param blackStandardLegalMoves
     */

    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    /**
     * Returns white pieces
     *
     * @return
     */

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }


    /**
     * Returns Alliance
     *
     * @return
     */
    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    /**
     * Returns opponent
     *
     * @return
     */

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    /**
     * Calculates and returns a Collection of Moves that are classified as King Castles. A king castle is a special move in chess where both the
     * king and the castle can move on the same turn.
     * @param playerLegals
     * @param opponentLegals
     * @return
     */

    //Check BlackPlayer for annotation on below method.

    @Override
    public Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                 final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<Move>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            //Whites king side castle
            if (!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(62);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 61));
                    }
                }
            }
            if (!this.board.getTile(59).isTileOccupied() &&
                    !this.board.getTile(58).isTileOccupied() &&
                    !this.board.getTile(57).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() &&
                        Player.calculateAttacksOnTile(58, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(59, opponentLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
                }
            }
        }


        return ImmutableList.copyOf(kingCastles);
    }
}
