package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import vttp.batch5.TTTengine;

//NUS\coding\exam1\task02> javac -d classes --source-path src src/vttp/batch5/sdf/task02/*.java
//NUS\coding\exam1\task02> java -cp classes vttp/batch5/sdf/task02.Main TTT/figure1.txt

public class Main extends TTTengine {

    char[][] board;
    char currentPlayer;

    public Main(){
        board = new char[3][3];
        currentPlayer = 'X';   	//X (me) starts
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = '.';
            }
        }    
	}

	public void parseTTT(File TTTFile) throws IOException{
		ArrayList<char[]> boardTemp = new ArrayList<>();
		FileReader fr = new FileReader(TTTFile);
        BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null){			//read each line of the txt
			char[] lineData = line.toCharArray(); 		//parse each line into a char array
			boardTemp.add(lineData);
		}
		br.close();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = boardTemp.get(i)[j]; 		//populate the empty board
			}
		}
	}

    public void printBoard(Main newBoard){
		System.out.println("Board:");
        for(int i = 0; i < 3; i++){
            System.out.println(" " + board[i][0] + "  " + board[i][1] + "  " + board[i][2]);
        }
		System.out.println("--------------------------");
    }

    public void printUtil(Main newBoard){
        for(int[] move : newBoard.getPossibleMoves(board)){
            newBoard.makeMove(move[0], move[1], 'X', board);
            if(newBoard.checkWin('X', board)){
                System.out.printf("y=%d, x=%d, utility=1\n" ,move[0], move[1]);
            }else if(newBoard.checkWinO('O', board)){
                System.out.printf("y=%d, x=%d, utility=-1\n" ,move[0], move[1]);
            }
            else{
                System.out.printf("y=%d, x=%d, utility=0\n" ,move[0], move[1]);
            }
            newBoard.undoMove(move[0], move[1], board);
        }
    }


	public static void main(String[] args) throws IOException {

        if(args.length <= 0 ){
            System.err.println("File name missing, please provide a valid TTT configuration file");
            //anything other than system exit 1 e.g(System.exit(0)) means there is an error
            System.exit(1);
        }
		String inputFile = args[0];

		Path TTTPath = Paths.get(inputFile);
    	File TTTFile = TTTPath.toFile();

		Main newBoard = new Main();
		newBoard.parseTTT(TTTFile); 	//char[][] board global variable is populated
		System.out.println("Processing: " + TTTFile + "\n");
		newBoard.printBoard(newBoard);
        newBoard.printUtil(newBoard);


	}
}
