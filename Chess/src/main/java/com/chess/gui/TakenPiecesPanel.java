package com.chess.gui;

import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.primitives.Ints;


public class TakenPiecesPanel extends JPanel {

    private final JPanel northPanel;
    private final JPanel southPanel;

    private static final Color PANEL_COLOUR = Color.decode("0x000080");
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 80);
    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);

    public TakenPiecesPanel() {
        super(new BorderLayout());
        this.setBackground(PANEL_COLOUR);
        this.setBorder(PANEL_BORDER);
        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOUR);
        this.southPanel.setBackground(PANEL_COLOUR);
        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.southPanel, BorderLayout.SOUTH);
        setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void redo(final MoveLog moveLog) {

        this.southPanel.removeAll();
        this.northPanel.removeAll();

        final List<Piece> whiteTakenPieces = new ArrayList<Piece>();
        final List<Piece> blackTakenPieces = new ArrayList<Piece>();


        for (final Move move : moveLog.getMoves()) {

            if (move.isAttack()) {
                final Piece takenPiece = move.getAttackedPiece();
                if (takenPiece.getPieceAlliance().isWhite()) {
                    whiteTakenPieces.add(takenPiece);
                } else if (takenPiece.getPieceAlliance().isBlack()) {
                    blackTakenPieces.add(takenPiece);
                } else {
                    throw new RuntimeException("Should not reach here");
                }
            }
        }

        Collections.sort(whiteTakenPieces, new Comparator<Piece>() {
            public int compare(Piece o1, Piece o2) {
                return Ints.compare(o1.getPieceValue(), o2.getPieceValue());
            }
        });

        Collections.sort(blackTakenPieces, new Comparator<Piece>() {
            public int compare(Piece o1, Piece o2) {
                return Ints.compare(o1.getPieceValue(), o2.getPieceValue());
            }
        });

        for (final Piece takenPiece : whiteTakenPieces) {
            try {
                final BufferedImage image = ImageIO.read(new File("art/pieces/plain/"
                        + takenPiece.getPieceAlliance().toString().substring(0, 1) + "" + takenPiece.toString()
                        + ".gif"));
                final ImageIcon ic = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(new ImageIcon(ic.getImage().getScaledInstance(
                        ic.getIconWidth() - 15, ic.getIconWidth() - 15, Image.SCALE_SMOOTH)));
                this.southPanel.add(imageLabel);
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }

        for (final Piece takenPiece : blackTakenPieces) {
            try {
                final BufferedImage image = ImageIO.read(new File("art/pieces/plain/"
                        + takenPiece.getPieceAlliance().toString().substring(0, 1) + "" + takenPiece.toString()
                        + ".gif"));
                final ImageIcon ic = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(new ImageIcon(ic.getImage().getScaledInstance(
                        ic.getIconWidth() - 15, ic.getIconWidth() - 15, Image.SCALE_SMOOTH)));
                this.northPanel.add(imageLabel);

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        validate();
    }

}
