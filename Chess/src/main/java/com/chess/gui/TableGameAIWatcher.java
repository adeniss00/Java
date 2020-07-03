package com.chess.gui;

import java.util.Observable;
import java.util.Observer;

class TableGameAIWatcher implements Observer {

    @Override
    public void update(final Observable o, final Object arg) {
        if (Table.get().getGameSetup().isAIPlayer(Table.get().getGameBoard().currentPlayer()) &&
                !Table.get().getGameBoard().currentPlayer().isInCheckMate() &&
                !Table.get().getGameBoard().currentPlayer().isInStaleMate()) {
            //create AI Thread
            //execute AI work
            final AIThinkTank thinkTank = new AIThinkTank();
            thinkTank.execute();
        }
        if (Table.get().getGameBoard().currentPlayer().isInCheckMate()) {
            System.out.println("Game Over, " + Table.get().getGameBoard().currentPlayer() + "is in checkmate");
        }
        if (Table.get().getGameBoard().currentPlayer().isInStaleMate()) {
            System.out.println("Game Over, " + Table.get().getGameBoard().currentPlayer() + "is in stalemate");
        }
    }
}
