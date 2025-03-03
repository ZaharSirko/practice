package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImp implements Player {
    private final TicTacToe ticTacToe;
    private final char mark;
    private final PlayerStrategy strategy;

    public PlayerImp(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }


    @Override
    public void run() {
       while (!Thread.currentThread().isInterrupted()) {
           synchronized (ticTacToe) {
               if (gameOver()) return;

               if (ticTacToe.lastMark() == mark){
                   try {
                      ticTacToe.wait();
                   }catch (InterruptedException e){
                       Thread.currentThread().interrupt();
                       return;
                   }
               }

               if (gameOver()) return;

               Move move = strategy.computeMove(mark, ticTacToe);
               ticTacToe.setMark(move.row,move.column,mark);
               ticTacToe.notifyAll();
           }
       }

    }

    private boolean gameOver() {
        char[][] board = ticTacToe.table();
        return checkWinner(board, 'X') || checkWinner(board, 'O') || boardFull(board);
    }

    private boolean checkWinner(char[][] board, char mark) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) ||
                    (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)) {
                return true;
            }
        }
        return (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
                (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark);
    }

    private boolean boardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
