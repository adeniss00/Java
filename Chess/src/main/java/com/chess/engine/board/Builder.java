package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Builder {

    Map<Integer, Piece> boardConfig;
    Alliance nextMoveMaker;
    Pawn enPassantPawn;

    public Builder() {
        this.boardConfig = new HashMap<>();
    }

    public Builder setPiece(final Piece piece) {
        this.boardConfig.put(piece.getPiecePosition(),  piece);
        return this;
    }

    public Builder setMoveMaker(final Alliance nextMoveMaker) {
        this.nextMoveMaker = nextMoveMaker;
        return this;
    }

    public Board build() {
        return new Board(this);

    }

    public void setEnPassantPawn(Pawn enPassantPawn) {
        this.enPassantPawn=enPassantPawn;
    }
}
