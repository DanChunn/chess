package Pieces;

import util.Color;
import Game.Board;
import Game.Player;

/**
 * The King piece.
 *
 */
public class King extends Piece{

	public King(Color pColor, Board board) {
		super(pColor, Class.King, board);
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
	 *      11 if the move is castling to the right
	 *      12 if the move is catling to the left
	 */
	public int legalMove (int currX, int currY, int newX, int newY){
		
		boolean legal = true;
		int check = 0;
		
		int dx;
		int dy;
		dx = newX-currX;
		dy = newY-currY;
		
		
		/*
		 * Makes sure King only moves 1 space in any direction
		 */
		if(Math.abs(dx) <= 1 && Math.abs(dy) <= 1){
			/*
			if(scanCheck(newX,newY)){
				return 3;
			}
			*/
			
			/* Checks is space is empty or not */
			if(board.isSpotEmpty(newX, newY)){
				/*
				if(scanCheck(newX,newY)){
					return 3;
				}
				*/
				moveCount++;
				return 1;
			}else if(!(board.getSquare(newX, newY).getPiece().pColor==pColor)){
				/* If not make sure piece is opposite color */
				/*
				if(scanCheck(newX,newY)){
					return 3;
				}
				*/
				moveCount++;
				return 2;
			}
			
			
			
		}
		else if(Math.abs(dx) == 0 && Math.abs(dy) == 2 && moveCount == 0){
			
			Piece p;
			
			if(dy > 0){
				if(board.isSpotEmpty(newX, newY -1) && board.isSpotEmpty(newX,newY)){
					p = board.getSquare(newX, newY+1).getPiece();
					
					if(p == null)
						return 0;
					
					if(p instanceof Rook && p.getColor() == this.pColor){
						if(p.getMoveCount() == 0){
							return 11;
						}
					}
				}
				
			}else{
				if(board.isSpotEmpty(newX, newY + 1) && board.isSpotEmpty(newX,newY) && board.isSpotEmpty(newX,  newY - 1)){
					p = board.getSquare(newX, newY-2).getPiece();
					
					if(p == null)
						return 0;
					
					if(p instanceof Rook && p.getColor() == this.pColor){
						if(p.getMoveCount() == 0){
							return 12;
						}
					}
				}
			}
		}
	
		return 0;
	}

}
