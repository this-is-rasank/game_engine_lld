package api;

import boards.TicTacToeBoard;
import gamestate.Board;
import gamestate.GameState;

//checks if the the board is following certain rules
public class RuleEngine {
    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";

            // row check
            boolean rowComplete=true;
            for(int i=0;i<3;i++){
                firstCharacter = board1.getSymbol(i,0);
                rowComplete = firstCharacter!=null;
                if(firstCharacter != null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getSymbol(i, j))) {
                            rowComplete = false;
                            //as we are checking to find continues block of same sign, as this condition don't satisfy
                            // we can come out of block without checking further.
                            break;
                        }
                    }
                }
                if(rowComplete)
                    // if in the above row we have already found the cross then don't check further rows
                    break;
            }

            if(rowComplete)
                return new GameState(true, firstCharacter);

            //col check
            boolean colComplete = true;
            for(int i=0;i<3;i++){
                firstCharacter = board1.getSymbol(0,i);
                colComplete = firstCharacter!=null;
                if(firstCharacter!=null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getSymbol(j, i))) {
                            colComplete = false;
                            break;
                        }
                    }
                }
                // if in the above col we have already found the cross then don't check further cols
                if(colComplete)
                    break;
            }
            if(colComplete)
                return new GameState(true, firstCharacter);


            //diagonal check
            firstCharacter = board1.getSymbol(0,0);
            boolean diagComplete = firstCharacter!=null;
            for(int i=0;i<3;i++){
                if(firstCharacter!= null && !firstCharacter.equals(board1.getSymbol(i,i))){
                    diagComplete = false;
                    break;
                }
            }
            if(diagComplete)
                return new GameState(true, firstCharacter);

            //reverse diagonal check
            firstCharacter = board1.getSymbol(0,2);
            boolean revDiagComplete = firstCharacter!=null;
            for(int i=0;i<3;i++){
                if(firstCharacter!=null && !firstCharacter.equals(board1.getSymbol(i,2-i))){
                    revDiagComplete = false;
                    break;
                }
            }
            if(revDiagComplete)
                return new GameState(true, firstCharacter);

            //Draw Condition: If all the cells are filled
            int countOfFilledCells = 0;
            for(int i=0;i<3;i++){
                for(int j=1;j<3;j++){
                    if(board1.getSymbol(i,j) != null)
                        countOfFilledCells++;
                }
            }
            if(countOfFilledCells == 9)
                return new GameState(true, "-");
            else
                //game is not over as all cells are not filled
                return new GameState(false, "-");
        }
        else{
            //not a tic-tac-toe game
            return new GameState(false, "-");
        }
    }
}
