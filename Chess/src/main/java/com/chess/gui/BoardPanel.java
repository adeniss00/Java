package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates tiles on GUI
 */

public class BoardPanel extends JPanel {

    protected final Table table;
    final List<TilePanel> boardTiles;


    /**
     * Constructor for BoardPanel
     */
    BoardPanel(Table table) {
        super(new GridLayout(8, 8));
        this.table = table;
        this.boardTiles = new ArrayList<TilePanel>();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final TilePanel tilePanel = new TilePanel(this,i);
            this.boardTiles.add(tilePanel);
            add(tilePanel);
        }
        setPreferredSize(Table.BOARD_PANEL_DIMENSION);
        validate();
    }

    /**
     * Method that visualises the chess board. First removes old board and then creates new one.
     *
     * @param board
     */

    public void drawBoard(final Board board) {
        removeAll();
        for (final TilePanel tilePanel : table.boardDirection.traverse(boardTiles)) {
            tilePanel.drawTile(board);
            add(tilePanel);
        }
        validate();
        repaint();
    }


}
