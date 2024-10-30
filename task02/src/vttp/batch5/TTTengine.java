package vttp.batch5;

import java.util.ArrayList;
import java.util.List;

public class TTTengine {


    public boolean checkWin(char player, char[][] board){
            for(int i = 0; i < 3; i++){
                if ((board[i][0] == player && board[i][1] == player && board[i][2] == player)|| //check for horinzontal wins
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)){ //check for vertical wins
                        return true;
                }
            }
            return  (board[0][0] == player && board[1][1] == player && board[2][2] == player) || //check for diagonal wins
                    (board[0][2] == player && board[1][1] == player && board[2][0] == player);
        }


        public boolean checkWinO(char player, char[][] board){
            for(int i = 0; i < 3; i++){
                if ((board[i][0] == player && board[i][1] == player && board[i][2] == '.')|| //check for horinzontal wins
                    (board[i][0] == player && board[i][1] == '.' && board[i][2] == player)||
                    (board[i][0] == '.' && board[i][1] == player && board[i][2] == player)){
                    return true;
                }else if((board[0][i] == player && board[1][i] == player && board[2][i] == '.')|| //check for vertical wins
                        (board[0][i] == player && board[1][i] == '.' && board[2][i] == player)||
                        (board[0][i] == '.' && board[1][i] == player && board[2][i] == player)){ 
                        return true;
                }
            }
            return  (board[0][0] == player && board[1][1] == player && board[2][2] == '.') || //check for diagonal wins
                    (board[0][0] == player && board[1][1] == '.' && board[2][2] == player)||
                    (board[0][0] == '.' && board[1][1] == player && board[2][2] == player)||
                    (board[0][2] == player && board[1][1] == player && board[2][0] == '.')||
                    (board[0][2] == player && board[1][1] == '.' && board[2][0] == player)||
                    (board[0][2] == '.' && board[1][1] == player && board[2][0] == player);
        }

        public List<int[]> getPossibleMoves(char[][] board){
            List<int[]> moves = new ArrayList<>();
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(board[i][j] == '.'){
                        int[] temp = new int[] {i, j};
                        moves.add(temp);
                    }
                }
            }
            return moves;
        }


        public void makeMove(int row, int column, char player, char[][] board){
            if(board[row][column] == '.'){
                board[row][column] = player;
            }
        }

        public void undoMove(int row, int column, char[][] board){
            board[row][column] = '.';
        }


    
}
