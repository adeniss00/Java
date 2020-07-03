package com.chess.gui;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Enum to deal with flipping the board.
 */

public enum BoardDirection {

    NORMAL {
        @Override
        List<TilePanel> traverse(final List<TilePanel> boardTiles) {
            return boardTiles;
        }

        @Override
        BoardDirection opposite() {
            return FLIPPED;
        }

    },
    FLIPPED {
        @Override
        List<TilePanel> traverse(final List<TilePanel> boardTiles) {
            return Lists.reverse(boardTiles);
        }

        @Override
        BoardDirection opposite() {
            return NORMAL;
        }
    };

    abstract List<TilePanel> traverse(final List<TilePanel> boardTiles);

    abstract BoardDirection opposite();


}
