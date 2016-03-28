package Game;



import util.Color;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

/**
 * The Board class that will manipulate the board
 * based on the players moves.
 * 
 * Internal Representation: 
 * Y axis in the files.
 * X axis is the rank.
 *
 */
public class Board {
	
	Square[][] board; 
	char[] files = { 'a','b','c','d','e','f','g','h' };
	public Player[] players;
	
	public Board(Player[] players){
		
		this.players = players;
		createBoard();
	}
	
	/**
	 * Creates the blank template of the board.
	 */
	public void createBoard(){
		
		board = new Square[8][8];
		
		/* Inits board from rank 8 - 1 */
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new Square(i, files[j]);
			}
		}
	}
	
	/**
	 * Adds the Pieces for the respected player.
	 * @param player The player that will put their pieces on the board
	 */
	public void addPieces(Player player){
		
		Color playerC = player.getColor();
		Piece tempP;
		if(playerC == Color.Black){
			for(int i = 0; i < 8; i++){
				
				tempP = new Pawn(Color.Black, this);
				board[6][i].setPiece(tempP);
			}
			
			tempP = new Rook(Color.Black, this);
			board[7][0].setPiece(tempP);
			tempP = new Rook(Color.Black, this);
			board[7][7].setPiece(tempP);
			
			tempP = new Knight(Color.Black, this);
			board[7][1].setPiece(tempP);			
			tempP = new Knight(Color.Black, this);
			board[7][6].setPiece(tempP);
			
			tempP = new Bishop(Color.Black, this);
			board[7][2].setPiece(tempP);			
			tempP = new Bishop(Color.Black, this);
			board[7][5].setPiece(tempP);
			
			tempP = new Queen(Color.Black, this);
			board[7][3].setPiece(tempP);			
			tempP = new King(Color.Black, this);
			player.setKingRef(tempP);
			board[7][4].setPiece(tempP);
		}
		else{
			for(int i = 0; i < 8; i++){
				
				tempP = new Pawn(Color.White, this);
				board[1][i].setPiece(tempP);
			}
			
			tempP = new Rook(Color.White, this);
			board[0][0].setPiece(tempP);
			tempP = new Rook(Color.White, this);
			board[0][7].setPiece(tempP);
			
			tempP = new Knight(Color.White, this);
			board[0][1].setPiece(tempP);			
			tempP = new Knight(Color.White, this);
			board[0][6].setPiece(tempP);
			
			tempP = new Bishop(Color.White, this);
			board[0][2].setPiece(tempP);			
			tempP = new Bishop(Color.White, this);
			board[0][5].setPiece(tempP);
			
			tempP = new Queen(Color.White, this);
			board[0][3].setPiece(tempP);			
			tempP = new King(Color.White, this);
			player.setKingRef(tempP);
			board[0][4].setPiece(tempP);
		}
	}
	
	/**
	 * Prints out the board on the terminal screen
	 */
	public void printBoard(){
		
		/* Prints out board + ranks */
		for(int i = 7; i > -1; i--){
			for(int j = 0; j < 8; j++){
				
				Piece tempP = board[i][j].getPiece();
				if(tempP == null){
					if(board[i][j].sqrColor == Color.White){
						System.out.print("   ");
					}
					else{
						System.out.print(" ##");
					}
				}
				
				if((tempP instanceof Pawn)){
					if(tempP.getColor() == Color.Black){
						System.out.print(" bp");
					}
					else {
						System.out.print(" wp");
					}
				}
				else if((tempP instanceof Rook)){
					if(tempP.getColor() == Color.Black){
						System.out.print(" bR");
					}
					else {
						System.out.print(" wR");
					}
				}
				else if((tempP instanceof Knight)){
					if(tempP.getColor() == Color.Black){
						System.out.print(" bN");
					}
					else {
						System.out.print(" wN");
					}
				}
				else if((tempP instanceof Bishop)){
					if(tempP.getColor() == Color.Black){
						System.out.print(" bB");
					}
					else {
						System.out.print(" wB");
					}
				}
				else if((tempP instanceof Queen)){
					if(tempP.getColor() == Color.Black){
						System.out.print(" bQ");
					}
					else {
						System.out.print(" wQ");
					}
				}
				else if((tempP instanceof King)){
					if(tempP.getColor() == Color.Black){
						System.out.print(" bK");
					}
					else {
						System.out.print(" wK");
					}
				}
			}
			
			System.out.println(" " + (i + 1));
		}
		
		/* Prints out files */
		for(int i = 0; i < 8; i++){
			System.out.print("  " + files[i]);
		}
		System.out.println();
	}
	
	/**
	 * Checks if spot is empty on the board
	 * @param x X coord
	 * @param y Y coord
	 * @return true if empty, false if not empty
	 */
	public boolean isSpotEmpty(int x, int y){
		
		if(x < 0 || x > 7)
			return true;
		
		if(y < 0 || y > 7)
			return true;
		
		if(board[x][y].getPiece() == null){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if coord is out of range.
	 * @param x The rank
	 * @param y The file
	 * @return If its within the board.
	 */
	public boolean outOfRange(int x, int y){
	
		if(x < 0 || x > 7)
			return true;
		if(y < 0 || y > 7)
			return true;
		
		return false;
	}
	
	/**
	 * Gets the square object.
	 * @param x The rank.
	 * @param y The file.
	 * @return
	 */
	public Square getSquare(int x, int y){
		return board[x][y];
	}
	
}
