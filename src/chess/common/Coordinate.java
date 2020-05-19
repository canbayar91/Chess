package chess.common;

/**
 * The Class Coordinate.
 */
public class Coordinate {

	/** The pos x. */
	int posX;
	
	/** The pos y. */
	int posY;
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param posX the pos x
	 * @param posY the pos y
	 */
	public Coordinate(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * Gets the pos x.
	 *
	 * @return the pos x
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Sets the pos x.
	 *
	 * @param posX the new pos x
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Gets the pos y.
	 *
	 * @return the pos y
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Sets the pos y.
	 *
	 * @param posY the new pos y
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
