package com.chess.engine.board;

import static com.chess.engine.board.Move.NULL_MOVE;

public class MoveFactory {

    /**
     * Throws error when move factory is initialised
     */
    private MoveFactory() {
        throw new RuntimeException("Not Instantiable!");
    }

    /**
     * Method that identifies and returns the move in a list of legal moves.
     *
     * @param board
     * @param currentCoordinate
     * @param destinationCoordinate
     * @return
     */

    public static Move createMove(final Board board,
                                  final int currentCoordinate,
                                  final int destinationCoordinate) {
        for (final Move move : board.getAllLegalMoves()) { //Cycles all legal moves
            if (move.getCurrentCoordinate() == currentCoordinate && //Checks if move matches
                    move.getDestinationCoordinate() == destinationCoordinate) {
                return move; //returns move
            }
        }
        return NULL_MOVE;
    }
}
