package com.chess.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    public static Color lightTileColor = Color.decode("#FFFACD");
    public static Color darkTileColor = Color.decode("#593E1A");
    protected final static  Dimension OUTER_FRAME_DIMENSION =new Dimension(600,600) ;
    protected final static  Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    protected final static  Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    public Table(){
        this.gameFrame=new JFrame("JChess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setVisible(true);
        this.boardPanel=new BoardPanel();
        this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
    }

    private JMenuBar createTableMenuBar() {
        final  JMenuBar tableMenuBar=new JMenuBar();
        tableMenuBar.add(createFileMenu());
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

}
