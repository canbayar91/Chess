package chess.common;

public enum PieceColor {

	/** The white. */
	WHITE(0), 
	
	/** The black. */
	BLACK(1);
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new piece color.
	 *
	 * @param id the id
	 */
	PieceColor(int id) {
		setId(id);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
