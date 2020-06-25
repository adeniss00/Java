package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.chess.gui.Table.TILE_PANEL_DIMENSION;
import static com.chess.gui.Table.lightTileColor;
import static com.chess.gui.Table.darkTileColor;


public class TilePanel extends JPanel {

    private final int tileId;

    TilePanel(final BoardPanel boardPanel , final int tileId){
        super(new GridBagLayout());
        this.tileId=tileId;
        setPreferredSize(TILE_PANEL_DIMENSION);
        assignTileColor();
        validate();
    }
    private void assignTilePieceIcon(final Board board){
        this.removeAll();
        String pieceIconPath="";
        if (board.getTile(this.tileId).isTileOccupied()){
            try {
                final BufferedImage image= ImageIO.read(new File(pieceIconPath+board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0,1)+board.getTile(this.tileId).getPiece().toString()+".gif"));
                add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void assignTileColor() {
        if (BoardUtils.EIGHTH_RANK[this.tileId]||
                BoardUtils.SIXTH_RANK[this.tileId]||
                BoardUtils.FOURTH_RANK[this.tileId]||
                BoardUtils.SECOND_RANK[this.tileId]){
            setBackground(this.tileId%2==0 ? lightTileColor:darkTileColor);
        }
        else if (BoardUtils.SEVENTH_RANK[this.tileId]||
                BoardUtils.FIFTH_RANK[this.tileId]||
                BoardUtils.THIRD_RANK[this.tileId]||
                BoardUtils.FIRST_RANK[this.tileId]){
            setBackground(this.tileId%2!=0 ?lightTileColor: darkTileColor);
        }
    }
}
