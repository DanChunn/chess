package Pieces;



import util.Color;
import Game.Board;

/**
 * The Pawn Class, represents a Pawn 
 * during gameplay.
 *
 */
public class Pawn extends Piece{
	
	boolean enPassent;
	/**
	 * Constructor for Pawn.
	 * @param pColor The Color of the Pawn
	 * @param board The board of the game.
	 */
	public Pawn(Color pColor,  Board board){
		
		super(pColor, Class.Pawn, board);
		enPassent = false;
	}
	
	
	/**
	 * Checks if the move is legal.
	 * @param currX The current Rank of the piece.
	 * @param currY The current File of the piece.
	 * @param newX The desired Rank of the piece.
	 * @param newY The desired File of the piece.
	 * @return
	 * 		0 if the move is illegal
	 * 		1 if the move is legal, no piece to be captured
	 * 		2 if the move is legal, with piece to be captured
	 * 		21 if the move is enpassent, and the player is black
	 * 		22 if the move is enpassent, and the player is white
	 */
	public int legalMove (int currX, int currY, int newX, int newY){
		
		int dx;
		int dy;
		dx = newX-currX;
		dy = newY-currY;
		
		boolean isBlack = false;
		
		if(pColor == Color.Black){
			dx= -1 * dx;
			isBlack = true;
		}
		
		
		if(dy == 0 && dx == 2 && moveCount == 0){
			if(board.isSpotEmpty(newX,newY)){
				if(!isBlack){
					if(board.isSpotEmpty(newX - 1, newY)){
						setEnPassent();
						moveCount++;
						return 1;
					}
				}else{
					
					if(board.isSpotEmpty(newX + 1, newY)){
						setEnPassent();
						moveCount++;
						return 1;
					}	
				}
			}
		}
		else if(dy == 0 && dx == 1){
			if(board.isSpotEmpty(newX,newY)){
				moveCount++;
				return 1;
			}
		}
		else if((dy == 1 || dy == -1) && dx == 1 ){
			if(!(board.isSpotEmpty(newX,newY))){
				if(board.getSquare(newX, newY).getPiece().getColor() != this.pColor){
					moveCount++;
					return 2;
				}
			}
			else{
				Piece p;
				if(pColor == Color.Black){
					if(!(board.isSpotEmpty(newX + 1, newY))){
						p = board.getSquare(newX + 1, newY).getPiece();
						
						if(p instanceof Pawn){
							if(((Pawn)p).isEnPassent()){
								return 21;
							}
							else{
								return 0;
							}
						}
					}
				}
				else{
					if(!(board.isSpotEmpty(newX - 1, newY))){
						p = board.getSquare(newX - 1, newY).getPiece();
						
						if(p instanceof Pawn){
							if(((Pawn)p).isEnPassent()){
								return 22;
							}
							else{
								return 0;
							}
						}
					}
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * Incerements moveCount, used for when having to
	 * redo a move that caused the pawn to be promoted.
	 */
	public void incMoveCount(){
		
		moveCount++;
	}
	/**
	 * This pawn can be captued by enpassent
	 */
	public void setEnPassent(){
		
		enPassent = true;
	}
	/**
	 * This pawn can not be captured by enpassent
	 */
	public void remEnPassent(){
		
		enPassent = false;
	}
	/**
	 * Can this pawn be captured by enpassent.
	 * @return true if can, false otherwise
	 */
	public boolean isEnPassent(){
		
		return enPassent;
	}
}
