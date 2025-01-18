package api;

import boards.TicTacToeBoard;
import gamestate.*;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move suggestion;
            if(isStarting(board1, 4)){
                suggestion = getBasicMove(computer, board1);
            }else{
                suggestion = getSmartMove(computer, board1);
            }

            if (suggestion != null) return suggestion;
            //if no empty cell exist
            throw new IllegalStateException();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    private Move getSmartMove(Player player, TicTacToeBoard board) {
        RuleEngine ruleEngine = new RuleEngine();
        //player a attacking move that should be smart
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i,j), player);
                    board.move(move);
                    //if computer has won with the move or not
                    if(ruleEngine.getState(board).isOver())
                        return move;
                }
            }
        }
        return null;
    }

    private boolean isStarting(TicTacToeBoard board, int threshold) {
        int count=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count<threshold;
    }

    private Move getBasicMove(Player computer, TicTacToeBoard board1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        return null;
    }
}
