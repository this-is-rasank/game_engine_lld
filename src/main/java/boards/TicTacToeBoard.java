package boards;
import gamestate.Board;
import gamestate.Cell;
import gamestate.Move;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];

    public String getSymbol(int row, int col) { return cells[row][col]; }

    public void setCell(Cell cell, String symbol) { cells[cell.getRow()][cell.getCol()] = symbol; }

    @Override
    public void move(Move move) { setCell(move.getCell(), move.getPlayer().symbol()); }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                result.append(cells[i][j] == null ? "-" : cells[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }
}
