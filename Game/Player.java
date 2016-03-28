package Game;

import java.util.ArrayList;
import java.util.List;

import util.Color;
import Pieces.Piece;

/**
 * The Player class that will hold information
 * about the player during the game.
 *
 */
public class Player {

	Color player;
	int PawnsL, RooksL, BishopsL, KnightsL, QueenL, KingL;
	List<String> prevMoves;
	Piece KingPos;
	boolean inCheck;
	boolean enPassent;
	
	public Player(Color color){
		
		player = color;
		PawnsL = 8;
		RooksL = BishopsL = KnightsL = 2;
		QueenL = KingL = 1;
		inCheck = false;
		enPassent = false;
		
		prevMoves = new ArrayList<String>();
		
	}
	/**
	 * Gets the players color.
	 * @return The players color.
	 */
	public Color getColor(){
		
		return player;
	}
	/**
	 * Adds previous moves to the player.
	 * @param move The start-end pair for the move
	 */
	public void addMove(String move){
		
		prevMoves.add(move);
	}
	/**
	 * Sets the reference for the players King to check
	 * for check.
	 * @param king The king of the player
	 */
	public void setKingRef(Piece king){
		
		KingPos = king;
	}
	/**
	 * Gets the players king reference.
	 * @return The King reference Piece
	 */
	public Piece getKingRef(){
		
		return KingPos;
	}
	
	/**
	 * Gets the last turn.
	 * @return The String of the last move.
	 */
	public String getLastMove(){
		
		return prevMoves.get(prevMoves.size() - 1);
	}
	
	/**
	 * Removes the last turn
	 */
	public void remLastMove(){
		
		prevMoves.remove(prevMoves.size() -1);
	}
	
	/**
	 * Sets the player in check
	 */
	public void inCheck(){
		
		inCheck = true;
	}
	
	/**
	 * Sets the player not in check.
	 */
	public void notCheck(){
		
		inCheck = false;
	}
	
	/**
	 * Checks if the player is in check.
	 * @return true if the player is in check, false otherwise.
	 */
	public boolean isInCheck(){
		
		return inCheck;
	}
	
}

