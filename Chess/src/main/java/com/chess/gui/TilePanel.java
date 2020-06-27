package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.MoveFactory;
import com.chess.engine.player.MoveTransition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static com.chess.gui.Table.*;
import static javax.swing.SwingUtilities.*;

public class TilePanel extends JPanel {

    private final int tileId;

    TilePanel(final BoardPanel boardPanel , final int tileId){
        super(new GridBagLayout());
        this.tileId = tileId;
        setPreferredSize(TILE_PANEL_DIMENSION);
        assignTileColor();
        assignTilePieceIcon(chessBoard);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (isRightMouseButton(e)){
                    sourceTile=null;
                    destinationTile=null;
                    humanMovedPiece=null;
                    }

                    else if (isLeftMouseButton(e)){
                        if(sourceTile==null) {
                        sourceTile=chessBoard.getTile(tileId);
                        humanMovedPiece=sourceTile.getPiece();
                        if(humanMovedPiece==null){
                            sourceTile=null;
                        }
                        }
                        else {
                            destinationTile=chessBoard.getTile(tileId);
                            final Move move = MoveFactory.createMove(chessBoard,sourceTile.getTileCoordinate(),destinationTile.getTileCoordinate());
                            final MoveTransition transition= chessBoard.CurrentPlayer().makeMove(move);
                            if(transition.getMoveStatus().isDone()){
                                chessBoard=transition.getTransitionBoard();
                            }
                            sourceTile=null;
                            destinationTile=null;
                            humanMovedPiece=null;
                        }
                        SwingUtilities.invokeLater(() -> boardPanel.drawBoard(chessBoard));
                    }

            }

            @Override
            public void mousePressed(final MouseEvent e) {

            }

            @Override
            public void mouseReleased(final MouseEvent e) {

            }

            @Override
            public void mouseEntered(final MouseEvent e) {

            }

            @Override
            public void mouseExited(final MouseEvent e) {

            }
        });
        validate();
    }
    public void drawTile(final Board board){
        assignTileColor();
        assignTilePieceIcon(board);
        highlightsLegal(board);
        validate();
        repaint();
    }
    private void assignTilePieceIcon(final Board board){
        this.removeAll();
        if (board.getTile(this.tileId).isTileOccupied()){
            try {
                final BufferedImage image= ImageIO.read(new File(defaultPieceImagesPath+board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0,1)+board.getTile(this.tileId).getPiece().toString()+".gif"));
                add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void highlightsLegal(final Board board){
        if(highlightLegalMoves){
            for (final Move move:pieceLegalMoves(board)) {
                if(move.getDestinationCoordinate()==this.tileId){
                    try {
                        add(new JLabel(new ImageIcon(ImageIO.read(new File("art/misc/green_dot.png")))));}
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
   private Collection<Move> pieceLegalMoves(final Board board){
       if(humanMovedPiece != null && humanMovedPiece.getPieceAlliance() == board.CurrentPlayer().getAlliance()) {
           return humanMovedPiece.calculateLegalMoves(board);
       }
       return Collections.emptyList();
   }
    private void assignTileColor() {
        if (BoardUtils.EIGHTH_RANK[this.tileId] || BoardUtils.SIXTH_RANK[this.tileId] || BoardUtils.FOURTH_RANK[this.tileId] || BoardUtils.SECOND_RANK[this.tileId]) {
            setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
        } else if(BoardUtils.SEVENTH_RANK[this.tileId] || BoardUtils.FIFTH_RANK[this.tileId] || BoardUtils.THIRD_RANK[this.tileId]  || BoardUtils.FIRST_RANK[this.tileId]) {
            setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
        }
    }
}
