package com.chess.gui;

import com.chess.engine.board.Move;
import com.chess.engine.player.ai.MiniMax;
import com.chess.engine.player.ai.MoveStrategy;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

class AIThinkTank extends SwingWorker<Move, String> {
    protected AIThinkTank() {

    }

    @Override
    protected Move doInBackground() throws Exception {
        final MoveStrategy miniMax = new MiniMax(4);
        final Move bestMove = miniMax.execute(Table.get().getGameBoard());

        return bestMove;
    }

    @Override
    public void done() {
        try {
            final Move bestMove = get();

            Table.get().updateComputerMove(bestMove);
            Table.get().updateGameBoard(Table.get().getGameBoard().currentPlayer().makeMove(bestMove).getTransitionBoard());
            Table.get().getMoveLog().addMove(bestMove);
            Table.get().getGameHistoryPanel().redo(Table.get().getGameBoard(), Table.get().getMoveLog());
            Table.get().getTakenPiecesPanel().redo(Table.get().getMoveLog());
            Table.get().getBoardPanel().drawBoard(Table.get().getGameBoard());
            Table.get().moveMadeUpdate(Table.PlayerType.COMPUTER);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
