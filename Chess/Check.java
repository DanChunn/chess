package Chess;

import java.util.List;

import util.Color;
import Game.Board;
import Game.Square;
import Pieces.King;
import Pieces.Piece;
import Pieces.Piece.Class;

/**
 * Provides all the functions that deal with checking for
 * Check/Checkmate
 *
 */
public class Check {

	
	/**
	 * General scanCheck to check if the square can be captured
	 * by the opposing player.
	 * @param x The Rank of the Square.
	 * @param y The File of the Square.
	 * @param color The color of the player at risk of check.
	 * @param board The playing board.
	 * @return true if the spot can be captured by the opposing player, false otherwise
	 */
	public static boolean scanCheck(int x, int y, Color color, Board board){
		
		//DIAGONAL - THREAT OF QUEENS, BISHOPS, KING, AND PAWNS
		
		Piece p;
		
		if(board.outOfRange(x, y))
			return true;
		
		//Checks for Threats on Top-Right Diagonal
		if(board.isSpotEmpty(x + 1, y + 1)){
			toprightloop:
				for(int i = x+1, j = y+1; i < 8 && j < 8; i++, j++){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
					
							if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
								return true;
							}
							break toprightloop;
						}
				}
		}
		else{
			p = board.getSquare(x + 1, y + 1).getPiece();
			
			if(p.pColor == color){
				;
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.White){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}
		
		//Checks for Threats on Top-Left Diagonal
		if(board.isSpotEmpty(x + 1, y - 1)){
			topleftloop:
				for(int i = x+1, j = y-1; i < 8 && j > 0; i++, j--){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
							
							if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor != color){
								return true;
							}
							break topleftloop;
						}
				}
				
		}
		else{
			p = board.getSquare(x + 1, y - 1).getPiece();
			
			if(p.pColor == color){
				;
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.White){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}

		//Checks for Threats on Bottom-Right Diagonal
		if(board.isSpotEmpty(x - 1, y + 1)){
			bottomrightloop:
			for(int i = x-1, j = y+1; i > 0 && j < 8; i--, j++){
					if(!(board.isSpotEmpty(i, j))){
						p = board.getSquare(i, j).getPiece();
					
						if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
							return true;
						}
						
						break bottomrightloop;
					}
						
			}
			
		}
		else{
			p = board.getSquare(x - 1, y + 1).getPiece();
			
			if(p.pColor == color){
				;
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.Black){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}

		//Checks for Threats on Bottom-Left Diagonal
		if(board.isSpotEmpty(x - 1, y - 1)){
			bottomleftloop:
			for(int i = x-1, j = y-1; i > 0 && j > 0; i--, j--){
					if(!(board.isSpotEmpty(i, j))){
						p = board.getSquare(i, j).getPiece();
						
						if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
							return true;
						}
						
						break bottomleftloop;
					}
				
			}
		}
		else{
			p = board.getSquare(x - 1, y - 1).getPiece();
			
			if(p.pColor == color){
				;
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.Black){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}
		
		//HORIZONTAL VERTAL - THREAT OF QUEENS, KING, ROOK

		//right
		for(int i = y+1; i < 8; i++){
			if(!board.isSpotEmpty(x, i)){
				p = board.getSquare(x, i).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == y + 1){
						if(p.pClass == Class.King && p.pColor!=color)
							return true;
					}
				}
				
				break;
			}
		}
		
		//left
		for(int i = y-1; i > 0; i--){
			if(!board.isSpotEmpty(x, i)){
				p = board.getSquare(x, i).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == y - 1){
						if(p.pClass == Class.King && p.pColor!=color)
							return true;
					}
				}
				break;
			}
		}
		
		//top
		for(int i = x+1; i < 8; i++){
			if(!board.isSpotEmpty(i, y)){
				p = board.getSquare(i, y).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == x + 1){
						if(p.pClass == Class.King && p.pColor!=color)
							return true;
					}
				}
				break;
			}
		}
		
		//bottom
		for(int i = x -1; i > 0; i--){
			if(!board.isSpotEmpty(i, y)){
				p = board.getSquare(i, y).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == x - 1){
						if(p.pClass == Class.King && p.pColor!=color)
							return true;
					}
				}
				break;
			}
		}
		
		//L's - THREAT OF KNIGHTS
		
		if(!(board.isSpotEmpty(x+2, y+1))){
			p = board.getSquare(x+2, y+1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x+2, y-1))){
			p = board.getSquare(x+2, y-1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x-2, y+1))){
			p = board.getSquare(x-2, y+1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x-2, y-1))){
			p = board.getSquare(x-2, y-1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x+1, y+2))){
			p = board.getSquare(x+1, y+2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		if(!(board.isSpotEmpty(x-1, y+2))){
			p = board.getSquare(x-1, y+2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		if(!(board.isSpotEmpty(x+1, y-2))){
			p = board.getSquare(x+1, y-2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x-1, y-2))){
			p = board.getSquare(x-1, y-2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * The scanCheck that is used to check around the King
	 * to see if there are any squares it can move to to get
	 * out of check.
	 * @param x The Rank of the Square.
	 * @param y The File of the Square.
	 * @param color The color of the player at risk of check.
	 * @param board The playing board.
	 * @return true if the king is safe from being captured, false otherwise
	 */
	public static boolean scanCheckKingSaved(int x, int y, Color color, Board board){
		
		//DIAGONAL - THREAT OF QUEENS, BISHOPS, KING, AND PAWNS
		
		Piece p;
		
		if(board.outOfRange(x, y))
			return true;
		
		//Checks for Threats on Top-Right Diagonal
		if(board.isSpotEmpty(x + 1, y + 1)){
			toprightloop:
				for(int i = x+1, j = y+1; i < 8 && j < 8; i++, j++){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
					
							if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
								return true;
							}
							break toprightloop;
						}
				}
		}
		else{
			p = board.getSquare(x + 1, y + 1).getPiece();
			
			if(p.pColor == color){
				if(p.pClass == Class.King){
					for(int i = x+2, j = y+2; i < 8 && j < 8; i++, j++){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
					
							if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
								return true;
							}
							break;
						}
					}
				}
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.White){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}
		
		//Checks for Threats on Top-Left Diagonal
		if(board.isSpotEmpty(x + 1, y - 1)){
			topleftloop:
				for(int i = x+1, j = y-1; i < 8 && j > 0; i++, j--){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
							
							if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor != color){
								return true;
							}
							break topleftloop;
						}
				}
				
		}
		else{
			p = board.getSquare(x + 1, y - 1).getPiece();
			
			if(p.pColor == color){
				for(int i = x+2, j = y-2; i < 8 && j > 0; i++, j--){
					if(!(board.isSpotEmpty(i, j))){
						p = board.getSquare(i, j).getPiece();
						
						if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor != color){
							return true;
						}
						break;
					}
				}
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.White){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}

		//Checks for Threats on Bottom-Right Diagonal
		if(board.isSpotEmpty(x - 1, y + 1)){
			bottomrightloop:
			for(int i = x-1, j = y+1; i > 0 && j < 8; i--, j++){
					if(!(board.isSpotEmpty(i, j))){
						p = board.getSquare(i, j).getPiece();
					
						if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
							return true;
						}
						
						break bottomrightloop;
					}
						
			}
			
		}
		else{
			p = board.getSquare(x - 1, y + 1).getPiece();
			
			if(p.pColor == color){
				for(int i = x-2, j = y+2; i > 0 && j < 8; i--, j++){
					if(!(board.isSpotEmpty(i, j))){
						p = board.getSquare(i, j).getPiece();
					
						if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
							return true;
						}
						
						break;
					}
						
			}
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.Black){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}

		//Checks for Threats on Bottom-Left Diagonal
		if(board.isSpotEmpty(x - 1, y - 1)){
			bottomleftloop:
			for(int i = x-1, j = y-1; i > 0 && j > 0; i--, j--){
					if(!(board.isSpotEmpty(i, j))){
						p = board.getSquare(i, j).getPiece();
						
						if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
							return true;
						}
						
						break bottomleftloop;
					}
				
			}
		}
		else{
			p = board.getSquare(x - 1, y - 1).getPiece();
			
			if(p.pColor == color){
				for(int i = x-2, j = y-2; i > 0 && j > 0; i--, j--){
					if(!(board.isSpotEmpty(i, j))){
						p = board.getSquare(i, j).getPiece();
						
						if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
							return true;
						}
						
						break;
					}
				
				}
			}
			else{
				if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
					return true;
				}
				else{
					if(color == Color.Black){
						if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
					else{
						if(p.pClass == Class.King && p.pColor != color){
							return true;
						}
					}
				}
			}
		}
		
		//HORIZONTAL VERTAL - THREAT OF QUEENS, KING, ROOK

		//right
		for(int i = y+1; i < 8; i++){
			if(!board.isSpotEmpty(x, i)){
				p = board.getSquare(x, i).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == y + 1){
						if(p.pClass == Class.King && p.pColor!=color){
							return true;
						}else{
							if(p.pClass == Class.King && p.pColor == color){
								continue;
							}
						}
					}
				}
				
				break;
			}
		}
		
		//left
		for(int i = y-1; i > 0; i--){
			if(!board.isSpotEmpty(x, i)){
				p = board.getSquare(x, i).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == y - 1){
						if(p.pClass == Class.King && p.pColor!=color){
							return true;
						}else{
						if(p.pClass == Class.King && p.pColor == color){
							continue;
						}
					}
					}
				}
				break;
			}
		}
		
		//top
		for(int i = x+1; i < 8; i++){
			if(!board.isSpotEmpty(i, y)){
				p = board.getSquare(i, y).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == x + 1){
						if(p.pClass == Class.King && p.pColor!=color){
							return true;
						}else{
							if(p.pClass == Class.King && p.pColor == color){
							continue;
						}
					}
					}
				}
				break;
			}
		}
		
		//bottom
		for(int i = x -1; i > 0; i--){
			if(!board.isSpotEmpty(i, y)){
				p = board.getSquare(i, y).getPiece();
				
				if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
					return true;
				}
				else{
					if(i == x - 1){
						if(p.pClass == Class.King && p.pColor!=color){
							return true;
						}else{
							if(p.pClass == Class.King && p.pColor == color){
							continue;
						}
					}
					}
				}
				break;
			}
		}
		
		//L's - THREAT OF KNIGHTS
		
		if(!(board.isSpotEmpty(x+2, y+1))){
			p = board.getSquare(x+2, y+1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x+2, y-1))){
			p = board.getSquare(x+2, y-1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x-2, y+1))){
			p = board.getSquare(x-2, y+1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x-2, y-1))){
			p = board.getSquare(x-2, y-1).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x+1, y+2))){
			p = board.getSquare(x+1, y+2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		if(!(board.isSpotEmpty(x-1, y+2))){
			p = board.getSquare(x-1, y+2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		if(!(board.isSpotEmpty(x+1, y-2))){
			p = board.getSquare(x+1, y-2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		if(!(board.isSpotEmpty(x-1, y-2))){
			p = board.getSquare(x-1, y-2).getPiece();
			
			if(p == null){
				;
			}
			else if(p.pClass==Class.Knight && p.pColor!=color){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Used to squares between the King and the adversary to see if any of the
	 * players pieces can block/capture the threat to get the King out of check.
	 * @param x The Rank of the Square.
	 * @param y The File of the Square.
	 * @param color The color of the player at risk of check.
	 * @param board The playing board.
	 * @return true if the threat can be blocked, false otherwise
	 */
		public static boolean scanCheckBlocked(int x, int y, Color color, Board board){
			
			//DIAGONAL - THREAT OF QUEENS, BISHOPS, KING, AND PAWNS
			
			Piece p;
			
			if(board.outOfRange(x, y))
				return true;
			
			//Checks for Threats on Top-Right Diagonal
			if(board.isSpotEmpty(x + 1, y + 1)){
				toprightloop:
					for(int i = x+1, j = y+1; i < 8 && j < 8; i++, j++){
							if(!(board.isSpotEmpty(i, j))){
								p = board.getSquare(i, j).getPiece();
						
								if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
									return true;
								}
								break toprightloop;
							}
					}
			}
			else{
				p = board.getSquare(x + 1, y + 1).getPiece();
				
				if(p.pColor == color){
					;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
						return true;
					}
					else{
						if(color == Color.White){
							if(p.pClass == Class.Pawn && p.pColor != color){
								return true;
							}
						}
						else{

						}
					}
				}
			}
			
			//Checks for Threats on Top-Left Diagonal
			if(board.isSpotEmpty(x + 1, y - 1)){
				topleftloop:
					for(int i = x+1, j = y-1; i < 8 && j > 0; i++, j--){
							if(!(board.isSpotEmpty(i, j))){
								p = board.getSquare(i, j).getPiece();
								
								if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor != color){
									return true;
								}
								break topleftloop;
							}
					}
					
			}
			else{
				p = board.getSquare(x + 1, y - 1).getPiece();
				
				if(p.pColor == color){
					;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
						return true;
					}
					else{
						if(color == Color.White){
							if(p.pClass == Class.Pawn && p.pColor != color){
								return true;
							}
						}
						else{

						}
					}
				}
			}

			//Checks for Threats on Bottom-Right Diagonal
			if(board.isSpotEmpty(x - 1, y + 1)){
				bottomrightloop:
				for(int i = x-1, j = y+1; i > 0 && j < 8; i--, j++){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
						
							if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
								return true;
							}
							
							break bottomrightloop;
						}
							
				}
				
			}
			else{
				p = board.getSquare(x - 1, y + 1).getPiece();
				
				if(p.pColor == color){
					;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
						return true;
					}
					else{
						if(color == Color.Black){
							if(p.pClass == Class.Pawn  && p.pColor != color){
								return true;
							}
						}
						else{

						}
					}
				}
			}

			//Checks for Threats on Bottom-Left Diagonal
			if(board.isSpotEmpty(x - 1, y - 1)){
				bottomleftloop:
				for(int i = x-1, j = y-1; i > 0 && j > 0; i--, j--){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
							
							if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=color){
								return true;
							}
							
							break bottomleftloop;
						}
					
				}
			}
			else{
				p = board.getSquare(x - 1, y - 1).getPiece();
				
				if(p.pColor == color){
					;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != color){
						return true;
					}
					else{
						if(color == Color.Black){
							if(p.pClass == Class.Pawn  && p.pColor != color){
								return true;
							}
						}
						else{

						}
					}
				}
			}
			
			//HORIZONTAL VERTAL - THREAT OF QUEENS, KING, ROOK

			//right
			for(int i = y+1; i < 8; i++){
				if(!board.isSpotEmpty(x, i)){
					p = board.getSquare(x, i).getPiece();
					
					if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
						return true;
					}
					else{

					}
					
					break;
				}
			}
			
			//left
			for(int i = y-1; i > 0; i--){
				if(!board.isSpotEmpty(x, i)){
					p = board.getSquare(x, i).getPiece();
					
					if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
						return true;
					}
					else{

					}
					break;
				}
			}
			
			//top
			for(int i = x+1; i < 8; i++){
				if(!board.isSpotEmpty(i, y)){
					p = board.getSquare(i, y).getPiece();
					
					if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
						return true;
					}
					else{

					}
					break;
				}
			}
			
			//bottom
			for(int i = x -1; i > 0; i--){
				if(!board.isSpotEmpty(i, y)){
					p = board.getSquare(i, y).getPiece();
					
					if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=color){
						return true;
					}
					else{

					}
					break;
				}
			}
			
			//L's - THREAT OF KNIGHTS
			
			if(!(board.isSpotEmpty(x+2, y+1))){
				p = board.getSquare(x+2, y+1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			
			if(!(board.isSpotEmpty(x+2, y-1))){
				p = board.getSquare(x+2, y-1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			
			if(!(board.isSpotEmpty(x-2, y+1))){
				p = board.getSquare(x-2, y+1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			
			if(!(board.isSpotEmpty(x-2, y-1))){
				p = board.getSquare(x-2, y-1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			
			if(!(board.isSpotEmpty(x+1, y+2))){
				p = board.getSquare(x+1, y+2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			if(!(board.isSpotEmpty(x-1, y+2))){
				p = board.getSquare(x-1, y+2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			if(!(board.isSpotEmpty(x+1, y-2))){
				p = board.getSquare(x+1, y-2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			
			if(!(board.isSpotEmpty(x-1, y-2))){
				p = board.getSquare(x-1, y-2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=color){
					return true;
				}
			}
			
			return false;
		}
		
		/**
		 * Used to check the direct surroundings of the King and check what safe spots the King
		 * can move to if in check, and checks what threats the King has that can cause it to 
		 * be in check. 
		 * @param q The King piece
		 * @param board The board.
		 * @param safe An array that represents the 9 spots around the King to check if the king is safe if they moved there.
		 * @param danger An piece array that holds the threatening piece based on what direction it is based on where the king is.
		 * @param knights A list to check if there are any knights that are threatening the King.
		 * @return How many threats the King has.
		 */
		public static int scanCheckKing(Piece q, Board board, short[] safe, Piece[] danger, List<Piece> knights){
			
			//DIAGONAL - THREAT OF QUEENS, BISHOPS, KING, AND PAWNS
			
			Piece p;
			int x = q.getPosition().getRank();
			int y = q.getPosition().getFile();
			int key = 0;
			
			int i, j;
			//Checks for Threats on Top-Right Diagonal
			if(board.isSpotEmpty(x + 1, y + 1)){
				toprightloop:
					for(i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++){
							if(!(board.isSpotEmpty(i, j))){
								p = board.getSquare(i, j).getPiece();
						
								if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != q.pColor){
									danger[2] = p;
									safe[2] = 0;
									key++;
								}
								else{
									safe[2] = 1;
								}
								break toprightloop;
							}
					}
			}
			else{
				p = board.getSquare(x + 1, y + 1).getPiece();
				
				if(p.pColor == q.pColor){
					safe[2] = 0;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != q.pColor){
						danger[2] = p;
						safe[2] = 0;
						key++;
					}
					else{
						if(q.pColor == Color.White){
							if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != q.pColor){
								danger[2] = p;
								safe[2] = 0;
								key++;
							}
						}
						else{
							if(p.pClass == Class.King && p.pColor != q.pColor){
								danger[2] = p;
								safe[2] = 0;
								key++;
							}
						}
					}
				}
				
				i = x; j = y;
			}
			
			if(i == 8 || j == 8)
				safe[2] = 1;
			
			//Checks for Threats on Top-Left Diagonal
			if(board.isSpotEmpty(x + 1, y - 1)){
				topleftloop:
					for(i = x + 1, j = y - 1; i < 8 && j > -1; i++, j--){
							if(!(board.isSpotEmpty(i, j))){
								p = board.getSquare(i, j).getPiece();
								
								if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor != q.pColor){
									danger[0] = p;
									safe[0] = 0;
									key++;
								}
								else{
									safe[0] = 1;
								}
								break topleftloop;
							}
					}
					
			}
			else{
				p = board.getSquare(x + 1, y - 1).getPiece();
				
				if(p.pColor == q.pColor){
					safe[0] = 0;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor !=q.pColor){
						danger[0] = p;
						safe[0] = 0;
						key++;
					}
					else{
						if(q.pColor == Color.White){
							if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != q.pColor){
								danger[0] = p;
								safe[0] = 0;
								key++;
							}
						}
						else{
							if(p.pClass == Class.King && p.pColor !=q.pColor){
								danger[0] = p;
								safe[0] = 0;
								key++;
							}
						}
					}
				}
				i = x; j = y;
			}

			if(i == 8 || j == -1)
				safe[0] = 1;
			
			//Checks for Threats on Bottom-Right Diagonal
			if(board.isSpotEmpty(x - 1, y + 1)){
				bottomrightloop:
				for(i = x - 1, j = y + 1; i > -1 && j < 8; i--, j++){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
						
							if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=q.pColor){
								danger[7] = p;
								safe[7] = 0;
								key++;
							}
							else{
								safe[7] = 1;
							}
							
							break bottomrightloop;
						}
							
				}
				
			}
			else{
				p = board.getSquare(x - 1, y + 1).getPiece();
				
				if(p.pColor == q.pColor){
					safe[5] = 0;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != q.pColor){
						danger[7] = p;
						safe[7] = 0;
						key++;
					}
					else{
						if(q.pColor == Color.Black){
							if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != q.pColor){
								danger[7] = p;
								safe[7] = 0;
								key++;
							}
						}
						else{
							if(p.pClass == Class.King && p.pColor != q.pColor){
								danger[7] = p;
								safe[7] = 0;
								key++;
							}
						}
					}
				}
				i = x; j = y;
			}

			if(i == -1 || j == 8)
				safe[5] = 1;
			
			//Checks for Threats on Bottom-Left Diagonal
			if(board.isSpotEmpty(x - 1, y - 1)){
				bottomleftloop:
				for(i = x - 1, j = y - 1; i > -1 && j > -1; i--, j--){
						if(!(board.isSpotEmpty(i, j))){
							p = board.getSquare(i, j).getPiece();
							
							if((p.pClass==Class.Bishop || p.pClass==Class.Queen) && p.pColor!=q.pColor){
								danger[5] = p;
								safe[5] = 0;
								key++;
							}
							else{
								safe[5] = 1;
							}
							break bottomleftloop;
						}
					
				}
			}
			else{
				p = board.getSquare(x - 1, y - 1).getPiece();
				
				if(p.pColor == q.pColor){
					safe[5] = 0;
				}
				else{
					if((p.pClass == Class.Bishop || p.pClass == Class.Queen ) && p.pColor != q.pColor){
						danger[5] = p;
						safe[5] = 0;
						key++;
					}
					else{
						if(q.pColor == Color.Black){
							if(p.pClass == Class.Pawn || p.pClass == Class.King && p.pColor != q.pColor){
								danger[5] = p;
								safe[5] = 0;
								key++;
							}
						}
						else{
							if(p.pClass == Class.King && p.pColor != q.pColor){
								danger[5] = p;
								safe[5] = 0;
								key++;
							}
						}
					}
				}
				i = x; j = y;
			}
			
			if(i == -1 || j == -1)
				safe[5] = 1;
			
			//HORIZONTAL VERTAL - THREAT OF QUEENS, KING, ROOK

			//right
			for(i = y + 1; i < 8; i++){
				if(!board.isSpotEmpty(x, i)){
					p = board.getSquare(x, i).getPiece();
					
					if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=q.pColor){
						danger[4] = p;
						safe[4] = 0;
						key++;
					}
					else{
						
						if(p.pColor != q.pColor){
							if(i == y + 1){
								if(p.pClass == Class.King && p.pColor!=q.pColor){
									danger[4] = p;
									safe[4] = 0;
									key++;
								}
							}							
						}
						else{
							if(i == y + 1)
								safe[4] = 0;
							else
								safe[4] = 1;
						}
					}
					
					break;
				}
			}
			
			if(i == 8)
				safe[4] = 1;
			
			//left
			for(i = y - 1; i > -1; i--){
				if(!board.isSpotEmpty(x, i)){
					p = board.getSquare(x, i).getPiece();
					
					if((p.pClass==Class.Queen ||  p.pClass==Class.Rook) && p.pColor!=q.pColor){
						danger[3] = p;
						safe[3] = 0;
						key++;
					}
					else{
						if(p.pColor != q.pColor){
							if(i == y - 1){
								if(p.pClass == Class.King && p.pColor!=q.pColor){
									danger[3] = p;
									safe[3] = 0;
									key++;
								}
							}							
						}
						else{
							if(i == y - 1)
								safe[3] = 0;
							else
								safe[3] = 1;
						}
					}
					break;
				}
			}
			
			if(i == -1)
				safe[3] = 1;
			
			//top
			for(i = x +1; i < 8; i++){
				if(!board.isSpotEmpty(i, y)){
					p = board.getSquare(i, y).getPiece();
					
					if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=q.pColor){
						danger[1] = p;
						safe[1] = 0;
						key++;
					}
					else{
						if(p.pColor != q.pColor){
							if(i == x + 1){
								if(p.pClass == Class.King && p.pColor!=q.pColor){
									danger[1] = p;
									safe[1] = 0;
									key++;
								}
							}							
						}
						else{
							if(i == x + 1)
								safe[1] = 0;
							else
								safe[1] = 1;
						}
					}
					break;
				}
			}
			
			if(i == 8)
				safe[1] = 1;
			
			//bottom
			for(i = x - 1; i > -1; i--){
				if(!board.isSpotEmpty(i, y)){
					p = board.getSquare(i, y).getPiece();
					
					if((p.pClass==Class.Queen || p.pClass==Class.Rook) && p.pColor!=q.pColor){
						danger[6] = p;
						safe[6] = 0;
						key++;
					}
					else{
						if(p.pColor != q.pColor){
							if(i == x - 1){
								if(p.pClass == Class.King && p.pColor!=q.pColor){
									danger[6] = p;
									safe[6] = 0;
									key++;
								}
							}							
						}
						else{
							if(i == x - 1)
								safe[6] = 0;
							else
								safe[6] = 1;
						}
					}
					break;
				}
			}
			
			if(i == -1)
				safe[6] = 1;
			
			//L's - THREAT OF KNIGHTS
			
			if(!(board.isSpotEmpty(x+2, y+1))){
				p = board.getSquare(x+2, y+1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}
			
			if(!(board.isSpotEmpty(x+2, y-1))){
				p = board.getSquare(x+2, y-1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}
			
			if(!(board.isSpotEmpty(x-2, y+1))){
				p = board.getSquare(x-2, y+1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}
			
			if(!(board.isSpotEmpty(x-2, y-1))){
				p = board.getSquare(x-2, y-1).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}
			
			if(!(board.isSpotEmpty(x+1, y+2))){
				p = board.getSquare(x+1, y+2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}
			if(!(board.isSpotEmpty(x-1, y+2))){
				p = board.getSquare(x-1, y+2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}
			if(!(board.isSpotEmpty(x+1, y-2))){
				p = board.getSquare(x+1, y-2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}
			
			if(!(board.isSpotEmpty(x-1, y-2))){
				p = board.getSquare(x-1, y-2).getPiece();
				
				if(p == null){
					;
				}
				else if(p.pClass==Class.Knight && p.pColor!=q.pColor){
					knights.add(p);
					key++;
				}
			}	
			
			return key;
		}
		
		/**
		 * Based on the direction of the threat, it will check each square between the
		 * King and the threat to see if the King can stay in its spot, and have another piece
		 * take out or block the threat from keeping the King in check.
		 * @param start The position of the threat.
		 * @param pColor The color of the threat.
		 * @param end The position of the King
		 * @param p The king piece.
		 * @param type The direction the threat is from the King.
		 * @return Returns null if the threat can be blocked, or the theatening piece if it can not.
		 */
		public static Piece canBlock(Square start, Color pColor, Square end, Piece p, int type){
			
			if(Check.scanCheck(start.getRank(),start.getFile(),pColor,p.board)){
				return null;
			}
			
			int sRank = start.getRank();
			int sFile = start.getFile();
			int eRank = end.getRank();
			int eFile = end.getFile();
			
			switch(type){
			case 0:
				for(int i = sRank, j = sFile; i > eRank && j < sFile; i--,j++){
					if(Check.scanCheckBlocked(i, j, pColor, p.board))
						return null;
				}
				break;
			case 1:
				for(int i = sRank; i > eRank; i--){
					if(Check.scanCheckBlocked(i, sFile, pColor, p.board))
						return null;
				}
				break;
			case 2:
				for(int i = sRank, j = sFile; i > eRank && j > sFile; i--,j--){
					if(Check.scanCheckBlocked(i, j, pColor, p.board))
						return null;
				}
				break;
			case 3:
				for(int j = sFile;  j < eFile;j++){
					if(Check.scanCheckBlocked(sRank, j, pColor, p.board))
						return null;
				}
				break;
			case 4:
				for(int j = sFile; j > eFile; j--){
					if(Check.scanCheckBlocked(sRank, j, pColor, p.board))
						return null;
				}
				break;
			case 5:
				for(int i = sRank, j = sFile; i < eRank && j < eFile; i++,j++){
					if(Check.scanCheckBlocked(i, j, pColor, p.board))
						return null;
				}
				break;
			case 6:
				for(int i = sRank; i < eRank; i++){
					if(Check.scanCheckBlocked(i, sFile, pColor, p.board))
						return null;
				}
				break;
			case 7:
				for(int i = sRank, j = sFile; i < eRank && j > eFile; i++,j--){
					if(Check.scanCheckBlocked(i, j, pColor, p.board))
						return null;
				}
				break;
			}
			
			
			
			return p;
		}
}
