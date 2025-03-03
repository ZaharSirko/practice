package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImp  implements TicTacToe {
    private final char[][] board = new char[3][3];
    private char lastMark = 'O';

    public TicTacToeImp() {
        for (char[] row : board){
            Arrays.fill(row, ' ');
        }
    }

    @Override
    public synchronized  void setMark(int x, int y, char mark) {
        if (board[x][y] == ' ') {
            board[x][y] = mark;
            lastMark = mark;
        } else {
            throw new IllegalArgumentException("You have already marked this cell");
        }
    }

    @Override
    public synchronized  char[][] table() {
        char[][] tableCopy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, tableCopy[i], 0, 3);
        }
        return tableCopy;
    }

    @Override
    public synchronized  char lastMark() {
        return lastMark;
    }



}
