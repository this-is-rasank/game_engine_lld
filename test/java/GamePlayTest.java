import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class GamePlayTest {
    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @BeforeEach
    public void setUp(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    private void playgame(Board board, int[][] moves){
        int row, col;
        int next=0;
        while(!ruleEngine.getState(board).isOver()){
            Player computer = new Player("O");
            Player human = new Player("X");

            //Input from player
            row = moves[next][0];
            col = moves[next][1];
            next++;
            //player move
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);

            //computer move- if game is not complete
            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
    }

    @Test
    public void checkForRowWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{1,0},{1,1},{1,2}};

        playgame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkForColWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{1,0},{2,0}};
        Scanner scanner = new Scanner(System.in);
        playgame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkForDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{1,1},{2,2}};
        Scanner scanner = new Scanner(System.in);
        playgame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,2},{1,1},{2,0}};
        Scanner scanner = new Scanner(System.in);
        playgame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin(){
        //hello
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1,0},{1,1},{2,0}};
        Scanner scanner = new Scanner(System.in);
        playgame(board, moves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }
}
