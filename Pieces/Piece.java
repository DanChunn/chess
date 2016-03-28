package Pieces;

import util.Color;
import Game.Board;
import Game.Square;

/**
 * The Abstract Piece class that will be used as a template
 * for all the other pieces.
 * 
 */
public abstract class Piece {
	
	/**
	 * An enum Class that will be used to indicate what type of
	 * piece the current piece is.
	 *
	 */
	public enum Class {
		Pawn, Rook, Knight, Bishop, King, Queen
	}
	
	/* The Color of the Piece. Using the Color enum Class from the
	 * Square Class, as pieces are also just White or Black.
	 */
	public Color pColor;
	/*
	 * The Class of the Piece, set in the constructor of each sub-class.
	 */
	public Class pClass;
	/*
	 * Indicates the amount of moves this peice made.
	 */
	int moveCount;
	/*
	 * The current Position the Piece is.
	 */
	public Square currPos;
	/*
	 * A Pointer to the board.
	 */
	public Board board;	
	
	/**
	 * Piece constructor
	 * @param pColor The Color of the Piece
	 * @param pClass The Class of the Piece
	 * @param board The board
	 */
	protected Piece(Color pColor, Class pClass, Board board){
		this.pColor = pColor;
		this.pClass = pClass;
		this.board = board;
	}
	
	/**
	 * The Abstract method that all pieces use to tell wether the current move
	 * is legal for that piece or not.
	 * @param curRank The current Rank of the piece.
	 * @param curFile The current File of the piece.
	 * @param newRank The new Rank of the piece.
	 * @param newFile The new File of the piece.
	 * @return 
	 * 		0 if the move is illegal
	 * 		1 if the move is legal, but might produce conflict if another piece is in the way
	 * 		2 if the move is legal, and also attempt a capture of a piece in the new file-rank.
	 */
	public abstract int legalMove(int curRank, int curFile, int newRank, int newFile);
	
	/**
	 * Gets the Color of the piece
	 * @return The Color of the piece
	 */
	public Color getColor(){
		
		return pColor;
	}
	
	/**
	 * Gets the Class of the piece
	 * @return The Class of the piece
	 */
	public Class getPieceClass(){
		
		return pClass;
	}
	
	/**
	 * Sets the current square the piece is
	 * on.
	 * @param sq The square location
	 */
	public void setPosition(Square sq){
		
		currPos = sq;
	}
	
	/**
	 * Gets the current square the piece is
	 * @return The square the piece is on.
	 */
	public Square getPosition(){
		
		return currPos;
	}
	
	/**
	 * Gets the moveCount
	 * @return
	 */
	public int getMoveCount(){
		
		return moveCount;
	}
	
}
