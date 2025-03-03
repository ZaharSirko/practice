package com.epam.rd.autocode.concurrenttictactoe;

public class SimplePlayerStrategy implements PlayerStrategy {
    @Override
    public Move computeMove(char mark, TicTacToe ticTacToe) {
        char[][] board = ticTacToe.table();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return new Move(i, j);
                }
            }
        }
        return null;
    }
}
