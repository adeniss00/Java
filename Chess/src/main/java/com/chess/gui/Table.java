package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Table {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    protected static Board chessBoard;

    protected static Tile sourceTile;
    protected static Tile destinationTile;
    protected static Piece humanMovedPiece;
    protected static BoardDirection boardDirection;
    protected static boolean highlightLegalMoves;

    protected static Color lightTileColor = Color.decode("#FFFACD");
    protected static Color darkTileColor = Color.decode("#593E1A");
    protected static String defaultPieceImagesPath="art/pieces/plain/";

    protected final static  Dimension OUTER_FRAME_DIMENSION =new Dimension(600,600) ;
    protected final static  Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    protected final static  Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    public Table(){
        this.gameFrame=new JFrame("JChess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setVisible(true);
        this.chessBoard= Board.createStandardBoard();
        this.boardPanel=new BoardPanel();
        this.boardDirection=BoardDirection.NORMAL;
        this.highlightLegalMoves=false;
        this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
    }

    private JMenuBar createTableMenuBar() {
        final  JMenuBar tableMenuBar=new JMenuBar();
        tableMenuBar.add(createFileMenu());
        tableMenuBar.add(createPreferencesMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu=new JMenu("File");
        final JMenuItem openPGN=new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Open up that pgn file !");
            }
        });
        fileMenu.add(openPGN);
        final JMenuItem exitMenuItem=new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }
    private JMenu createPreferencesMenu(){
        final JMenu prefencesMenu=new JMenu("Preferences");
        final JMenuItem flipBoardMenuItem=new JMenuItem("Flip Board");
        flipBoardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardDirection=boardDirection.opposite();
                boardPanel.drawBoard(chessBoard);
            }
        });
        prefencesMenu.add(flipBoardMenuItem);
        prefencesMenu.addSeparator();
        final JCheckBoxMenuItem legalMoveHighLighterCheckBox= new JCheckBoxMenuItem("Highlight legal Move",false);
        legalMoveHighLighterCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightLegalMoves=legalMoveHighLighterCheckBox.isSelected();
            }
        });
        prefencesMenu.add(legalMoveHighLighterCheckBox);
        return prefencesMenu;
    }

    public boolean isHighlightLegalMoves() {
        return this.highlightLegalMoves;
    }
}
