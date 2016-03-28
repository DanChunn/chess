package Pieces;

import util.Color;
import Game.Board;

/**
 * The Queen Piece.
 *
 */
public class Queen extends Piece{

	public Queen(Color pColor, Board board) {
		super(pColor, Class.Queen, board);
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
		// TODO Auto-generated method stub
		int dx;
		int dy;
		dx = newX-currX;
		dy = newY-currY;
		
		/*
		 * Checks if color is black.
		 */
		
		/*
		 * The difference in change of rank and file
		 * must be 0 for it to be a diagonal path 
		 */
		if(Math.abs(dy)-Math.abs(dx)==0){
			
			//Move top right
			if( dx > 0 && dy > 0){
				for(int i = currX + 1, j = currY + 1; i < newX || j < newY; i++, j++){
					if(!(board.isSpotEmpty(i, j))){
						return 0;
					}
				}
			}
			
			//Move top left
			if( dx > 0 && dy < 0){
				for(int i = currX + 1, j = currY - 1; i < newX || j > newY; i++, j--){
					if(!(board.isSpotEmpty(i, j))){
						return 0;
					}
				}
			}
			
			//Move bottom right
			if( dx < 0 && dy > 0){
				for(int i = currX - 1 , j = currY + 1; i > newX || j < newY; i--, j++){
					if(!(board.isSpotEmpty(i, j))){
						return 0;
					}
				}
			}
			//Move bottom left
			if( dx < 0 && dy < 0 ){
				for(int i = currX - 1, j = currY - 1; i > newX || j > newY; i--, j--){
					if(!(board.isSpotEmpty(i, j))){
						return 0;
					}
				}
			}
			
			/*
			 * Checks if new spot is free
			 */
			if(board.isSpotEmpty(newX, newY)){
				moveCount++;
				return 1;
			}else if(!(board.getSquare(newX, newY).getPiece().pColor==pColor)){
				/*
				 * Checks if piece to be captured is the opposite 
				 * color.
				 */
				moveCount++;
				return 2;
			}	
		}
		
		/*
		 * Horizontal and Vertical Case
		 */
		if(Math.abs(dy) > 0 && dx == 0){
			/*
			 * Horizontal Case
			 */
			
			/*
			 * If moving to the right.
			 */
			if(dy > 0){
				for(int i = currY + 1 ; i < newY; i++){
					if(!(board.isSpotEmpty(currX, i))){
						return 0;
					}
				}
			}
			
			/*
			 * If moving to the left.
			 */
			if(dy < 0){
				for(int i = currY - 1; i > newY; i--){
					if(!(board.isSpotEmpty(currX, i))){
						return 0;
					}
				}
			}
			
			/*
			 * Checks if the new spot is empty
			 */
			if(board.isSpotEmpty(newX, newY)){
				moveCount++;
				return 1;
			}
			else if(!(board.getSquare(newX, newY).getPiece().getColor() == pColor)){
				/*
				 * Checks if piece to be captured is the opposite color.
				 */
				moveCount++;
				return 2;
			}

		}
		else if(Math.abs(dx) > 0 && dy == 0){
			/* 
			 * The Vertical Case.
			 */
			
			/*
			 * If moving upward.
			 */
			if(dx > 0){
				for(int i = currX + 1; i < newX; i++){
					if(!(board.isSpotEmpty(i, currY))){
						return 0;
					}
				}
			}
			/*
			 * If moving downward.
			 */
			if(dx < 0){
				for(int i = currX - 1; i > newX; i--){
					if(!(board.isSpotEmpty(i, currY))){
						return 0;
					}
				}
			}
			
			/*
			 * Checks if new spot is empty.
			 */
			if(board.isSpotEmpty(newX, newY)){
				moveCount++;
				return 1;
			}else if(!(board.getSquare(newX, newY).getPiece().pColor==pColor)){
				/* Checks if piece to be captured is the opposite color */
				moveCount++;
				return 2;
			}
		}
			
		return 0;
	}

}
