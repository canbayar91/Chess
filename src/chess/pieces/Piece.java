package chess.pieces;

import java.util.List;

import chess.common.Coordinate;
import chess.common.PieceColor;

/**
 * The Ýnterface Piece.
 */
public interface Piece {
	
	/**
	 * Checks whether the piece can move from location start to end.
	 *
	 * @param start the start
	 * @param end the end
	 * @return true, if successful
	 */
	boolean checkMove(Coordinate start, Coordinate end);
	
	/**
	 * Return all moves that the piece can make.
	 *
	 * @return the list
	 */
	List<Coordinate> allMoves();
	
	/**
	 * Returns whether the piece is still on play or not.
	 *
	 * @return true, if is captured
	 */
	boolean isCaptured();
	
	/**
	 * Sets the captured.
	 *
	 * @param captured the new captured
	 */
	void setCaptured(boolean captured);
	
	/**
	 * Gets the coordinate.
	 *
	 * @return the coordinate
	 */
	Coordinate getCoordinate();
	
	/**
	 * Sets the coordinate.
	 *
	 * @param coordinate the new coordinate
	 */
	void setCoordinate(Coordinate coordinate);
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	PieceColor getColor();
	
	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	void setColor(PieceColor color);
	
}
