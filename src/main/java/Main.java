import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;
import gamestate.Player;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while(!ruleEngine.getState(board).isOver()){
            Player computer = new Player("O");
            Player human = new Player("X");
            System.out.println("Make your move: ");
            //print board after every move
            System.out.println("Board: ");
            System.out.println(board);
            //Input from player
            row = scanner.nextInt();
            col = scanner.nextInt();
            //player move
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);
            //computer move- if game is not complete
            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
        System.out.println("GameResult: "+ ruleEngine.getState(board));
        System.out.println(board);
    }
}
