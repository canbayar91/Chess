package chess.common;

/**
 * The Enum PieceType.
 */
public enum PieceType {
	
	/** The king. */
	KING(0), 
	
	/** The queen. */
	QUEEN(1), 
	
	/** The rook. */
	ROOK(2),
	
	/** The knight. */
	KNIGHT(3),
	
	/** The bishop. */
	BISHOP(4), 
	
	/** The pawn. */
	PAWN(5);
	
	/** The id. */
	private int id;

	/**
	 * Instantiates a new piece type.
	 *
	 * @param id the id
	 */
	PieceType(int id) {
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
