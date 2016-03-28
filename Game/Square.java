package Game;

import util.Color;
import Pieces.Piece;

/**
 * This class represents the squares that will
 * be part of the board. Each square knows its
 * file-rank, and also the piece that is currently
 * on it.
 *
 */
public class Square {

	
	Position fileRank;
	Piece currPiece;
	Color sqrColor;
	
	/**
	 * Creates a new Square with specified file and
	 * rank, without any piece on it.
	 * @param rank The rank of the square on the board
	 * @param file The file of the square on the board
	 */
	public Square(int rank, char file){
		
		/* Translates the file to an integer and 
		 * and creates a new Position class */
		createPosition(rank, file);
		setColor();
		currPiece = null;
	}
	
	/**
	 * Creates the Position class that will tell
	 * what the file and rank of the Square is.
	 * @param rank The rank of the square on the board.
	 * @param file The file of the square on the board.
	 */
	private void createPosition(int rank, char file){
		
		int ifile = 0;
		/* Translates char file to integer file */
		switch(file){
		
		case 'a':
			ifile = 0;
			break;
		case 'b':
			ifile = 1;
			break;
		case 'c':
			ifile = 2;
			break;
		case 'd':
			ifile = 3;
			break;
		case 'e':
			ifile = 4;
			break;
		case 'f':
			ifile = 5;
			break;
		case 'g':
			ifile = 6;
			break;
		case 'h':
			ifile = 7;
			break;
			
		}
		/* Creates new Position */
		fileRank = new Position(rank, ifile);
	}
	
	/**
	 * Sets Color of square during init of square
	 * based on location on Board.
	 */
	private void setColor(){
		
		int rank = fileRank.getRank();
		int file = fileRank.getFile();
		
		if((rank + file) % 2 == 0)
			sqrColor = Color.White;
		else{
			sqrColor = Color.Black;
		}
	}
	
	/**
	 * Sets the Piece on the square.
	 * @param newPiece The piece that will be placed on this square
	 */
	public void setPiece(Piece newPiece){
		
		currPiece = newPiece;
		newPiece.setPosition(this);
	}
	
	/**
	 * Gets the current Piece on the square.
	 * @return The current Piece on the square.
	 */
	public Piece getPiece(){
		
		return currPiece;
	}
	
	/**
	 * Removes the Piece from the current square
	 * @return The reference to the Piece that was just removed
	 */
	public Piece removePiece(){
		
		Piece temp = currPiece;
		currPiece = null;
		
		temp.setPosition(null);
		return temp;
	}
	
	/**
	 * Gest the Rank of the square
	 * @return The rank.
	 */
	public int getRank(){
		
		return fileRank.getRank();
	}
	
	/**
	 * Gest the file of the square
	 * @return the file.
	 */
	public int getFile(){
		
		return fileRank.getFile();
	}
}
