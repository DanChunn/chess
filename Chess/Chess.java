package Chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import util.Color;
import Game.Board;
import Game.Player;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

/**
 * The Chess Board Game that will handle the input
 * and maybe the state of the game.
 *
 */
public class Chess {

	/*
	 * The Board of the chess game
	 */
	Board board;
	/*
	 * The players of the chess game.
	 */
	Player[] players;
	/*
	 * The order of pieces being captured
	 */
	List<Piece> recentlyRem;
	
	boolean draw;
	
	/**
	 * The Constructor of the Chess game that creates the board
	 * and the players. 
	 */
	public Chess(){
		
		
		players = new Player[2];
		players[0] = new Player(Color.White);
		players[1] = new Player(Color.Black);
		
		board = new Board(players);
		board.addPieces(players[0]);
		board.addPieces(players[1]);
		recentlyRem = new ArrayList<Piece>();
		
		draw = false;
	}
	
	/**
	 * Prints the board out.
	 */
	public void printBoard(){
		
		System.out.println();
		board.printBoard();
		System.out.println();
	}
	
	/**
	 * Starts the game, receiving input from the terminal.
	 */
	public void start(){
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] start = new int[2];
		int[] end = new int[2];
		String line = "";
		String[] argv;
		
		int i = 0;
		int check = 0;
		int success = 0;
		King king;
		
		printBoard();
		
		while(true){
			
			
			
			if(i == 0){
				System.out.print("White's move (Format - a2 a3): ");
			}
			else{
				System.out.print("Black's move:(Format - a7 a6) : ");
			}
			
			try{
				line = in.readLine();
			}catch(IOException e){
				System.out.println("Error: No moved specified");
				continue;
			}
			
			argv = line.split(" ");
			if(argv.length == 0){
				System.out.println("\nError: No moved specified");
				continue;
			}
			else if(argv.length == 1){
				if(argv[0].equals("resign")){
					if(i == 0){
						System.exit(1);
					}
					else{
						System.exit(1);
					}
				}
				else if(draw){
					if(argv[0].equals("draw")){
						System.exit(1);
					}
					else{
						draw = false;
						System.out.println("\nError: Incorrect input.\n");
					}
				}
				else{
					System.out.println("\nError: Incorrect input.");
				}
				continue;
			}
			else if(argv.length < 2 || argv.length > 3){
				System.out.println("\nError: Incorrect input.");
				continue;
			}
			
			try{
				start = translatePos(argv[0]);
				end = translatePos(argv[1]);
			} catch(IllegalArgumentException e){
				System.out.println("\nError: Moves are in incorrect format. Moves consist of fileRank");
				continue;
			}
			
			try{
				success = attemptMove(start, end, players[i]);
			}
			catch(IllegalArgumentException e){
				System.out.println("\nIllegal Move, try again.\n");
				continue;
			}

			draw = false;
			
			if(argv.length == 3){
				if(argv[2].equals("draw?")){
					draw = true;
				}
			}
			
			players[i].addMove(argv[0] + " " + argv[1]);
			
			if(success == 31 || success == 32 || success == 33 || success == 34){
				try{
					promotePawn(end,argv.length,argv);
				} catch(IllegalArgumentException e){
					line = players[i].getLastMove();
					argv = line.split(" ");
					
					start = translatePos(argv[0]);
					end = translatePos(argv[1]);
					
					redoLastMove(end,start, players[i], success);
					System.out.println("\nIllegal Move, try again\n");
					continue;
				}
			}
			
			if(players[i].isInCheck()){
				/* If player is in check (and we reach this if-statement, there IS a way for the king to get out of check 
				 * redo the last move.*/
				king = (King)players[i].getKingRef();
				if(Check.scanCheck(king.getPosition().getRank(), king.getPosition().getFile(), king.getColor(), king.board)){
					line = players[i].getLastMove();
					argv = line.split(" ");
					
					start = translatePos(argv[0]);
					end = translatePos(argv[1]);
					
					redoLastMove(end,start, players[i], success);
					System.out.println("\nIllegal Move, try again\n");
					continue;
				}
				else{
					players[i].notCheck();
				}
				
			}
			else{
				/* Checks to make sure the last move did not indirectly
				 * cause the King to get into check by the player themselves.
				 */
				king = (King)players[i].getKingRef();
				if(Check.scanCheck(king.getPosition().getRank(), king.getPosition().getFile(), king.getColor(), king.board)){
					line = players[i].getLastMove();
					argv = line.split(" ");
					
					start = translatePos(argv[0]);
					end = translatePos(argv[1]);
					
					redoLastMove(end,start, players[i], success);
					System.out.println("\nIllegal Move, try again\n");
					continue;
				}
				else{
					;
				}
			}

			
			printBoard();
			
			resetEnPassent(i);
			
			i = (i + 1) % 2;
			
			king = (King)players[i].getKingRef();
			
			check = isCheckMate(king);
			
			if(check == 0)
				;
			else if(check == 11){
				System.out.println("\nCheck\n");
				players[i].inCheck();
			}
			else{
				System.out.println("\nCheckmate\n");
				if(i == 0){
					System.out.println("Black wins");
				}
				else{
					System.out.println("White wins");
				}
				
				System.exit(1);
			}
			
		}
		
		
	}
	
	/**
	 * Translates a file-rank move to a move that would
	 * work with a 2D matrix.
	 * @param move The move to be translated.
	 * @return The int array of the row and column.
	 */
	public int[] translatePos(String move){
		
		if(move.charAt(0) < 'a' || move.charAt(0) > 'h'){
			throw new IllegalArgumentException();
		}
		
		int[] fr = new int[2];
		switch(move.charAt(0)){
		case 'a':
			fr[1] = 0;
			break;
		case 'b':
			fr[1] = 1;
			break;
		case 'c':
			fr[1] = 2;
			break;
		case 'd':
			fr[1] = 3;
			break;
		case 'e':
			fr[1] = 4;
			break;
		case 'f':
			fr[1] = 5;
			break;
		case 'g':
			fr[1] = 6;
			break;
		case 'h':
			fr[1] = 7;
			break;
			
		}
		
		try{
			fr[0] = Integer.parseInt(move.substring(1));
			if(fr[0] < 1 || fr[0] > 8)
				throw new IllegalArgumentException();
			
			fr[0] = fr[0] - 1;
		}catch(NumberFormatException d){
			throw new IllegalArgumentException();
		}
		
		return fr;
	}
	
	/**
	 * Attempts to move the piece to its desired location.
	 * @param start The piece to be moved.
	 * @param end The end location of the piece.
	 * @return 1 if the move was legal and no piece was taken, 2 if a peice was taken and
	 * a bunch of other stuff based on castling/pawnpromotion/enpassent
	 * @throws IllegalArgumentException IllegalMove
	 */
	public int attemptMove(int[] start, int[] end, Player currP) throws IllegalArgumentException {
		
		Piece toMove;
		/* Checks to see if piece is of color */
		toMove = board.getSquare(start[0], start[1]).getPiece();
		
		if(toMove == null){
			throw new IllegalArgumentException();
		}
		
		if(toMove.getColor() != currP.getColor()){
			throw new IllegalArgumentException();
		}
		
		int legal = toMove.legalMove(start[0], start[1], end[0], end[1]);
		if(legal == 0 || legal == 3){
			throw new IllegalArgumentException();
		}
		else if(legal == 1){
			board.getSquare(start[0], start[1]).removePiece();
			board.getSquare(end[0], end[1]).setPiece(toMove);
			/* Deals with promotion */
			if(end[0] == 7 && currP.getColor() == Color.White){
				if(toMove instanceof Pawn){
					return 31;
				}
			}
			else if(end[0] == 0 && currP.getColor() == Color.Black){
				if(toMove instanceof Pawn){
					return 33;
				}
			}
			return 1;
		}
		else if(legal == 2){
			board.getSquare(start[0], start[1]).removePiece();
			recentlyRem.add(board.getSquare(end[0], end[1]).removePiece());
			board.getSquare(end[0], end[1]).setPiece(toMove);
			/* Deals with promotion */
			if(end[0] == 7 && currP.getColor() == Color.White){
				if(toMove instanceof Pawn){
					return 32;
				}
			}
			else if(end[0] == 0 && currP.getColor() == Color.Black){
				if(toMove instanceof Pawn){
					return 34;
				}
			}
			return 2;
		} /* Deals with castling */
		else if(legal == 11){
			board.getSquare(start[0], start[1]).removePiece();
			board.getSquare(end[0],end[1]).setPiece(toMove);
			toMove = board.getSquare(end[0],end[1]+1).removePiece();
			board.getSquare(end[0], end[1]-1).setPiece(toMove);
			return 11;
		}
		else if(legal == 12){
			board.getSquare(start[0], start[1]).removePiece();
			board.getSquare(end[0],end[1]).setPiece(toMove);
			toMove = board.getSquare(end[0],end[1]-2).removePiece();
			board.getSquare(end[0], end[1]+1).setPiece(toMove);
			return 12;
		} /* Deals with enpassent */
		else if(legal == 21){
			recentlyRem.add(board.getSquare(end[0] + 1, end[1]).removePiece());
			board.getSquare(start[0], start[1]).removePiece();
			board.getSquare(end[0], end[1]).setPiece(toMove);
			return 21;
		}
		else if(legal == 22){
			recentlyRem.add(board.getSquare(end[0] - 1, end[1]).removePiece());
			board.getSquare(start[0], start[1]).removePiece();
			board.getSquare(end[0], end[1]).setPiece(toMove);
			return 22;
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * Redo's a move that kept the King in Check.
	 * @param end The square where the piece was moved to.
	 * @param start The square where the piece should be placed again.
	 * @param currP The current Player.
	 */
	public void redoLastMove(int[] end, int[] start, Player currP, int wasPieceCaptured){
		
		Piece toMove, recRem;
		
		toMove = board.getSquare(end[0],end[1]).removePiece();
		
		if(toMove == null){
			throw new IllegalArgumentException();
		}
		
		if(toMove.getColor() != currP.getColor()){
			throw new IllegalArgumentException();
		}
		
		board.getSquare(start[0], start[1]).setPiece(toMove);
		
		if(wasPieceCaptured == 2){
			recRem = recentlyRem.remove(recentlyRem.size() - 1);
			board.getSquare(end[0], end[1]).setPiece(recRem);
		}
		else if(wasPieceCaptured == 11){
			Piece p = board.getSquare(end[0], end[1] - 1).removePiece();
			board.getSquare(end[0],end[1] + 1).setPiece(p);
		}
		else if(wasPieceCaptured == 12){
			Piece p = board.getSquare(end[0],end[1] + 1).removePiece();
			board.getSquare(end[0], end[1] - 2).setPiece(p);
		}
		else if(wasPieceCaptured == 21){
			Piece p = recentlyRem.remove(recentlyRem.size() - 1);
			board.getSquare(end[0] + 1, end[1]).setPiece(p);
		}
		else if(wasPieceCaptured == 22){
			Piece p = recentlyRem.remove(recentlyRem.size() - 1);
			board.getSquare(end[0] - 1, end[1]).setPiece(p);
		}
		else if(wasPieceCaptured == 31){
			/* Redos Promotion */
			Piece p = new Pawn(toMove.getColor(),board);
			((Pawn)p).incMoveCount();
			board.getSquare(start[0], start[1]).removePiece();
			board.getSquare(start[0], start[1]).setPiece(p);
		}
		else if(wasPieceCaptured == 32){
			/* Redos Promotion */
			Piece p = new Pawn(toMove.getColor(),board);
			((Pawn)p).incMoveCount();
			board.getSquare(start[0], start[1]).removePiece();
			board.getSquare(start[0], start[1]).setPiece(p);
			/* Returns piece back to its place */
			recRem = recentlyRem.remove(recentlyRem.size() - 1);
			board.getSquare(end[0], end[1]).setPiece(recRem);
		}
		
	}

	/**
	 * Promotes the pawn who gets to the end of the board.
	 * @param end The location of the pawn to be promoted
	 * @param argvlength The length of the move to see if they specified a piece
	 * @param argv The argument to tell what they want to promote the pawn to.
	 * @throws IllegalArgumentException If the promotion is neither N,R,Q, or B.
	 */
	public void promotePawn(int[] end, int argvlength, String[] argv) throws IllegalArgumentException{
		
		Piece p, q;
		if(argvlength == 3){
			if(argv[3].equals("N")){
				q = board.getSquare(end[0], end[1]).removePiece();
				p = new Knight(q.getColor(),board);
				board.getSquare(end[0], end[1]).setPiece(p);
			}else if(argv[3].equals("R")){
				q = board.getSquare(end[0], end[1]).removePiece();
				p = new Rook(q.getColor(),board);
				board.getSquare(end[0], end[1]).setPiece(p);
			}else if(argv[3].equals("B")){
				q = board.getSquare(end[0], end[1]).removePiece();
				p = new Bishop(q.getColor(),board);
				board.getSquare(end[0], end[1]).setPiece(p);
			}else if(argv[3].equals("Q")){
				q = board.getSquare(end[0], end[1]).removePiece();
				p = new Queen(q.getColor(),board);
				board.getSquare(end[0], end[1]).setPiece(p);
			}
			else{
				throw new IllegalArgumentException();
			}
		}
		else{
			q = board.getSquare(end[0], end[1]).removePiece();
			p = new Queen(q.getColor(),board);
			board.getSquare(end[0], end[1]).setPiece(p);
		}
	}
	
	/**
	 * Checks if the King is in Check/Checkmate.
	 * @param p The piece of the King
	 * @return 11 if the King is in check, 12 if checkmate, 0 if not.
	 */
	public int isCheckMate(Piece p){
		
		int Kx = p.getPosition().getRank();
		int Ky = p.getPosition().getFile();
		
		short[] safe = new short[8];
		Piece[] danger = new Piece[8];
		List<Piece> Knights = new ArrayList<Piece>();
		
		int key = Check.scanCheckKing(p, board, safe, danger, Knights);
		
		if(key != 0){
			for(int i = 0; i < 8; i++){
				if(safe[i] == 1){
					switch(i){
					case 0:
						if(Check.scanCheckKingSaved(Kx + 1, Ky - 1, p.getColor(), board))
							safe[i] = 0;
						break;
					case 1:
						if(Check.scanCheckKingSaved(Kx + 1, Ky, p.getColor(), board))
							safe[i] = 0;
						break;
					case 2:
						if(Check.scanCheckKingSaved(Kx + 1, Ky + 1, p.getColor(), board))
							safe[i] = 0;
						break;
					case 3:
						if(Check.scanCheckKingSaved(Kx, Ky - 1, p.getColor(), board))
							safe[i] = 0;
						break;
					case 4:
						if(Check.scanCheckKingSaved(Kx, Ky + 1, p.getColor(), board))
							safe[i] = 0;
						break;
					case 5:
						if(Check.scanCheckKingSaved(Kx - 1, Ky - 1, p.getColor(), board))
							safe[i] = 0;
						break;
					case 6:
						if(Check.scanCheckKingSaved(Kx - 1, Ky, p.getColor(), board))
							safe[i] = 0;
						break;
					case 7:
						if(Check.scanCheckKingSaved(Kx - 1, Ky + 1, p.getColor(), board))
							safe[i] = 0;
						break;
					}
				}
			}
		
			for(int i = 0; i < 8; i++){
				if(safe[i] == 1)
					return 11;
			}
			
			for(int j = 0; j < 8; j++){
				if(danger[j] != null){
					danger[j] = Check.canBlock(danger[j].getPosition(),danger[j].getColor(),p.getPosition(), danger[j], j);
				}
			}
			
			for(int j = 0; j < 8; j++){
				if(danger[j] != null)
					return 12;
			}
			
			if(Knights.size() > 1)
				return 12;
			else if(Knights.size() != 0){
				Piece k = Knights.get(0);
				
				if(Check.scanCheck(k.getPosition().getRank(),k.getPosition().getFile(),k.getColor(),k.board)){
					return 11;
				}
				else{
					return 12;
				}
			}
			
			return 11;
		}
		
		return 0;
	}
	
	
	/**
	 * Resets enPassent so pieces can not perform
	 * enPassent if they don't take advantange of it.
	 * @param player The index of the player to tell which row to clear.
	 */
	public void resetEnPassent(int player){
		
		Piece p;
		if(player == 0){
			for(int i = 0; i < 8; i++){
				if((p = board.getSquare(5, i).getPiece()) != null){
					if(p instanceof Pawn){
						((Pawn) p).remEnPassent();
					}
				}
			}
		}
		else{
			for(int i = 0; i < 8; i++){
				if((p = board.getSquare(4, i).getPiece()) != null){
					if(p instanceof Pawn){
						((Pawn) p).remEnPassent();
					}
				}
			}
		}
		
	}
	
	
	public static void main(String[] args){
		
		Chess game = new Chess();
		
		game.start();
	}
}
