package Pieces;

import util.Color;
import Game.Board;

/**
 * The Knight Piece
 * 
 */
public class Knight extends Piece{

	public Knight(Color pColor, Board board) {
		super(pColor, Class.Knight, board);
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
	 */
	public int legalMove(int currX, int currY, int newX, int newY) {
		
		int dx;
		int dy;
		dx = newX-currX;
		dy = newY-currY;
		
		/*
		 * Checks if color is black.
		 */
		if(pColor == Color.Black){
			dx = -1 * dx;
		}
		
		/*
		 * Checks if legal.
		 */
		if((Math.abs(dx)==1 && Math.abs(dy)==2) || (Math.abs(dx)==2 && Math.abs(dy)==1)){
			/* If the space is empty */
			if(board.isSpotEmpty(newX, newY)){
				return 1;
				
			}else if(!(board.isSpotEmpty(newX,newY)) && !(board.getSquare(newX, newY).getPiece().pColor==pColor)){
				moveCount++;
				return 2;
			}
		}
		
		return 0;
	}

}
