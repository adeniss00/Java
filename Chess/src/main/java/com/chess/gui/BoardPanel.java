package com.chess.gui;

import com.chess.engine.board.BoardUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.chess.gui.Table.BOARD_PANEL_DIMENSION;


public class BoardPanel extends JPanel {
    final List<TilePanel> boardTiles;
    BoardPanel(){
        super(new GridLayout(8,8));
        this.boardTiles =new ArrayList<>();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final TilePanel tilePanel=new TilePanel(this,i);
            this.boardTiles.add(tilePanel);
            add(tilePanel);
        }
        setPreferredSize(BOARD_PANEL_DIMENSION);
        validate();
    }
}
