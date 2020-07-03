package com.chess.gui;


import com.chess.engine.board.*;
import com.chess.engine.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Table extends Observable {

    private final JFrame gameFrame;
    protected final GameHistoryPanel gameHistoryPanel;
    protected final TakenPiecesPanel takenPiecesPanel;
    private final BoardPanel boardPanel;
    protected final MoveLog moveLog;
    protected final GameSetup gameSetup;
    protected Board chessBoard;

    protected Tile sourceTile;
    protected Tile destinationTile;
    protected Piece humanMovedPiece;
    protected BoardDirection boardDirection;

    private Move computerMove;

    protected boolean highlightLegalMoves;

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    protected final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    protected final static Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);

    protected static String defaultPieceImagesPath = "art/pieces/plain/";
    protected final Color lightTileColour = Color.decode("#FFFACD");
    protected final Color darkTileColour = Color.decode("#593E1A");

    private static final Table INSTANCE = new Table();

    /**
     * Constructor for table
     */
    private Table() {
        this.gameFrame = new JFrame("JChess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.chessBoard = Board.createStandardBoard();
        this.gameHistoryPanel = new GameHistoryPanel();
        this.takenPiecesPanel = new TakenPiecesPanel();
        this.boardPanel = new BoardPanel(this);
        this.moveLog = new MoveLog();
        this.addObserver(new TableGameAIWatcher());
        this.gameSetup = new GameSetup(this.gameFrame, true);
        this.boardDirection = BoardDirection.NORMAL;
        this.highlightLegalMoves = false;
        this.gameFrame.add(this.takenPiecesPanel, BorderLayout.WEST);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.add(this.gameHistoryPanel, BorderLayout.EAST);
        this.gameFrame.setVisible(true);
    }

    public static Table get() {
        return INSTANCE;
    }

    public void show(){
        Table.get().getMoveLog().clear();
        Table.get().getGameHistoryPanel().redo(chessBoard, Table.get().getMoveLog());
        Table.get().getTakenPiecesPanel().redo(Table.get().getMoveLog());
        Table.get().getBoardPanel().drawBoard(Table.get().getGameBoard());
    }

    protected GameSetup getGameSetup() {
        return this.gameSetup;
    }

    protected Board getGameBoard() {
        return this.chessBoard;
    }


    /**
     * Creates menu bar on GUI
     *
     * @return
     */

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        tableMenuBar.add(createPreferencesMenu());
        tableMenuBar.add(createOptionsMenu());
        return tableMenuBar;

    }

    /**
     * Creates the File option on the GUI
     *
     * @return
     */

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    /**
     * Creates the Preferences menu on the GUI
     *
     * @return
     */

    private JMenu createPreferencesMenu() {
        final JMenu preferencesMenu = new JMenu("Preferences");
        final JMenuItem flipBoardMenuItem = new JMenuItem("Flip Board");
        flipBoardMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                boardDirection = boardDirection.opposite();
                boardPanel.drawBoard(chessBoard);
            }
        });
        preferencesMenu.add(flipBoardMenuItem);
        preferencesMenu.addSeparator();
        final JCheckBoxMenuItem legalMoveHighlighterCheckbox = new JCheckBoxMenuItem("Highlight Legal Moves", false);
        legalMoveHighlighterCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                highlightLegalMoves = legalMoveHighlighterCheckbox.isSelected();

            }
        });

        preferencesMenu.add(legalMoveHighlighterCheckbox);
        return preferencesMenu;
    }

    private JMenu createOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");

        final JMenuItem setupGameMenuItem = new JMenuItem("Setup Game");
        setupGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table.get().getGameSetup().promptUser();
                Table.get().setupUpdate(Table.get().getGameSetup());
            }
        });

        optionsMenu.add(setupGameMenuItem);
        return optionsMenu;

    }

    private void setupUpdate(final GameSetup gameSetup) {
        setChanged();
        notifyObservers(gameSetup);

    }

    protected void updateGameBoard(final Board board) {
        this.chessBoard = board;
    }

    public void updateComputerMove(final Move move) {
        this.computerMove = move;
    }

    protected MoveLog getMoveLog(){
        return this.moveLog;
    }

    protected GameHistoryPanel getGameHistoryPanel(){
        return this.gameHistoryPanel;
    }

    protected TakenPiecesPanel getTakenPiecesPanel(){
        return this.takenPiecesPanel;
    }

    protected BoardPanel getBoardPanel(){
        return this.boardPanel;
    }

    protected void moveMadeUpdate(final PlayerType playerType){

        setChanged();
        notifyObservers(playerType);

    }


    enum PlayerType {
        HUMAN,
        COMPUTER
    }


}
