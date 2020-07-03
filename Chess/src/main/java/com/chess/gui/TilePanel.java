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

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class TilePanel extends JPanel {

    private final BoardPanel boardPanel;
    private final int tileId;

    /**
     * Constructor for TilePanel. Adds Mouse Listener Event to Tile
     *
     * @param boardPanel
     * @param tileId
     */

    TilePanel(BoardPanel boardPanel, final int tileId) {
        super(new GridBagLayout());
        this.boardPanel = boardPanel;
        this.tileId = tileId;
        setPreferredSize(Table.TILE_PANEL_DIMENSION);
        assignTileColour();
        assignTilePieceIcon(boardPanel.table.chessBoard);

        addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent e) {

                if (isRightMouseButton(e)) { //Clears selection if right click
                    boardPanel.table.sourceTile = null;
                    boardPanel.table.destinationTile = null;
                    boardPanel.table.humanMovedPiece = null;
                } else if (isLeftMouseButton(e)) {
                    if (boardPanel.table.sourceTile == null) {
                        boardPanel.table.sourceTile = boardPanel.table.chessBoard.getTile(tileId); //Assigns sourceTile
                        boardPanel.table.humanMovedPiece = boardPanel.table.sourceTile.getPiece(); //Assigns piece
                        if (boardPanel.table.humanMovedPiece == null) {
                            boardPanel.table.sourceTile = null;
                        }
                    } else {
                        boardPanel.table.destinationTile = boardPanel.table.chessBoard.getTile(tileId); //Sets destinationTile
                        final Move move = MoveFactory.createMove(boardPanel.table.chessBoard, boardPanel.table.sourceTile.getTileCoordinate(), boardPanel.table.destinationTile.getTileCoordinate()); //Creates move by passing the board and two coordinates
                        final MoveTransition transition = boardPanel.table.chessBoard.currentPlayer().makeMove(move); //Makes move and passes status to transition
                        if (transition.getMoveStatus().isDone()) {
                            boardPanel.table.chessBoard = transition.getTransitionBoard(); //Creates new board
                            boardPanel.table.moveLog.addMove(move);
                        }
                        boardPanel.table.sourceTile = null;
                        boardPanel.table.destinationTile = null;
                        boardPanel.table.humanMovedPiece = null;
                    }
                    SwingUtilities.invokeLater(new Runnable() { //Changes visuals
                        public void run() {
                            boardPanel.table.gameHistoryPanel.redo(boardPanel.table.chessBoard, boardPanel.table.moveLog);
                            boardPanel.table.takenPiecesPanel.redo(boardPanel.table.moveLog);
                            if(boardPanel.table.gameSetup.isAIPlayer(boardPanel.table.chessBoard.currentPlayer())){
                                Table.get().moveMadeUpdate(Table.PlayerType.HUMAN);
                            }
                            boardPanel.drawBoard(boardPanel.table.chessBoard);
                        }
                    });
                }
            }

            public void mousePressed(final MouseEvent e) {

            }

            public void mouseReleased(final MouseEvent e) {

            }

            public void mouseEntered(final MouseEvent e) {

            }

            public void mouseExited(final MouseEvent e) {

            }
        });
        validate();
    }


    /**
     * Method that creates tile
     *
     * @param board
     */

    public void drawTile(final Board board) {
        assignTileColour();
        assignTilePieceIcon(board);
        highLightLegals(board);
        validate();
        repaint();
    }

    /**
     * Method that sets image of piece on tile
     *
     * @param board
     */

    private void assignTilePieceIcon(final Board board) {
        this.removeAll();
        if (board.getTile(this.tileId).isTileOccupied()) {
            try {
                final BufferedImage image = ImageIO.read(new File(Table.defaultPieceImagesPath + board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0, 1) +
                        board.getTile(this.tileId).getPiece().toString() + ".gif"));
                add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Method that highlights possible legal moves with a green dot.
     *
     * @param board
     */

    private void highLightLegals(final Board board) {
        if (boardPanel.table.highlightLegalMoves) {
            for (final Move move : pieceLegalMoves(board)) {
                if (move.getDestinationCoordinate() == this.tileId) {
                    try {
                        add(new JLabel(new ImageIcon(ImageIO.read(new File("art/misc/green_dot.png")))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Method that returns a collection on Moves that are legal moves for a selected piece.
     *
     * @param board
     * @return
     */

    private Collection<Move> pieceLegalMoves(final Board board) {
        if (boardPanel.table.humanMovedPiece != null && boardPanel.table.humanMovedPiece.getPieceAlliance() == board.currentPlayer().getAlliance()) {
            return boardPanel.table.humanMovedPiece.calculateLegalMoves(board);
        }
        return Collections.emptyList();
    }

    /**
     * Method that assigns the colour of the tile.
     */

    private void assignTileColour() {
        if (BoardUtils.EIGHTH_RANK[this.tileId] ||
                BoardUtils.SIXTH_RANK[this.tileId] ||
                BoardUtils.FOURTH_RANK[this.tileId] ||
                BoardUtils.SECOND_RANK[this.tileId]) {
            setBackground(this.tileId % 2 == 0 ? boardPanel.table.lightTileColour : boardPanel.table.darkTileColour);
        } else if (BoardUtils.SEVENTH_RANK[this.tileId] ||
                BoardUtils.FIFTH_RANK[this.tileId] ||
                BoardUtils.THIRD_RANK[this.tileId] ||
                BoardUtils.FIRST_RANK[this.tileId]) {
            setBackground(this.tileId % 2 != 0 ? boardPanel.table.lightTileColour : boardPanel.table.darkTileColour);
        }


    }

}
