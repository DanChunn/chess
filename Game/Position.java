package Game;

/**
 * The logical rank/file of the square on the board.
 * As in 0-7. 
 *
 */
public class Position {

	protected int rank, file;
	
	/**
	 * Creates the Position
	 * @param rank The rank,
	 * @param file The file
	 */
	public Position(int rank, int file){
		
		this.rank = rank;
		this.file = file;
	}
	
	/**
	 * Gets the rank.
	 * @return the rank.
	 */
	public int getRank(){
		
		return rank;
	}
	/**
	 * Gets the file
	 * @return the file
	 */
	public int getFile(){
		
		return file;
	}
}
