package tictactoe;
import java.util.Scanner;
import java.util.Random;
class TicTacToe {
	
    static char[][] board;

    public TicTacToe() {             // to created the constructor for board size
        board = new char[3][3];
        initBoard();
    }

    void initBoard() {        //this method created for to fill empty space
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void dispBoard() {        // to display the board
        System.out.println(" -------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println(" -------------");
        }
    }
    
    static void placeMark( int row, int col, char mark) {     //to used for this method to prevent the array index bound error 
    		if( row >= 0 && row<=2 && col>=0 && col<=2) {
    			board[row][col] = mark;
    		}
    		else {
    			System.out.println("Invalid position");
    		}
    	
    }
    static boolean checkColWin() {
    	for( int j=0; j<=2; j++) {
    		if(board[0][j] !=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
    			return true;
    		}
    	}
    	return false;
    	
    }
    static boolean checkRowWin() {
    	for( int i=0; i<=2; i++) {
    		if(board[i][0] !=' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
    			return true;
    		}
    	}
    	return false;
    	
    }
    static boolean checkDiagWin() {      // without creating the object to access the class to use the static keyword 
    		if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
    				|| board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
    			return true;
    		}
    
    	return false;
    	
    }
    static boolean checkDraw() {
    	for( int i=0; i<=2; i++) {
    		for( int j=0; j<=2; j++) {
    			if(board[i][j] == ' ') {
    	    		return false;
    	    	}
    			
    		}
    	}
    	return true;
    	
    }
}
abstract class player{   // parent class
	 String name;
	 char mark;
	 abstract void makeMove();
	 boolean isValidMove(int row, int col) {
			if(row>=0 && row<=2 && col>=0 && col<=2) {
				if(TicTacToe.board[row][col] == ' ') 
				{
				return true;
				}
			}
			return false;			
		}
	
}

 class HumanPlayer extends player {  //child class to inherit the parent class .

	 HumanPlayer( String name, char mark){
		 this.name = name;
		 this.mark = mark;
	 }
	 
	 void makeMove() {
		 Scanner scan = new Scanner(System.in);
		 int row, col;
		 do {
			System.out.println("Enter the row and column");
		    row = scan.nextInt();
		    col = scan.nextInt();
		 }while( !isValidMove(row, col) );
		 
		 TicTacToe.placeMark( row, col, mark);
	 }
	 
	
 }
 
 class AiPlayer extends player {

	 AiPlayer( String name, char mark){
		 this.name = name;
		 this.mark = mark;
	 }
	 
	 void makeMove() {
		 Scanner scan = new Scanner(System.in);
		 int row, col;
		 do {
			Random r = new Random();   //random() is built in class. its used for to fill random number in board
		    row = r.nextInt(3);
		    col = r.nextInt(3);
		 }while( !isValidMove(row, col) );
		 
		 TicTacToe.placeMark( row, col, mark);
	 }
	 
	
 }

public class LaunchGame {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        HumanPlayer p1 = new HumanPlayer("sibi",'x');
        AiPlayer p2 = new AiPlayer("AI BOT", 'o');
        player cp;   // cp denotes the current player. 
        cp = p1;
        while(true) {   // in this line while(true) is execute when the condition is true it exit the loop . with help of break keyword.
            System.out.println(cp.name + " turn" );
            cp.makeMove();
            TicTacToe.dispBoard();
            if( TicTacToe.checkColWin() || TicTacToe.checkRowWin()|| TicTacToe.checkDiagWin() || TicTacToe.checkDraw()) {
            	
            	System.out.println(cp.name + " has won");
            	break;
            }else if(TicTacToe.checkDraw()){
            	System.out.println("Game is Draw");
            	break;
            	
            }
            else {
            	if( cp == p1) {
            		cp = p2;
            	}else {
            		cp = p1;
            	}
            }
        }
        
    }
}
