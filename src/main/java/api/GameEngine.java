package api;

import boards.TicTacToeBoard;
import gamestate.*;

//Any change in the board
public class GameEngine {
    // when the game starts we should have a new board
    public Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move){
        if(board instanceof TicTacToeBoard){
            //board should have its own type of move, as chess and tictactoe have its own move
            board.move(move);
//            TicTacToeBoard board1 = (TicTacToeBoard) board;
//            board1.setCell(move.getCell(), player.symbol());
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
