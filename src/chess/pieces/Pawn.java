package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.common.Coordinate;
import chess.common.PieceColor;

/**
 * The Class Pawn.
 */
public class Pawn extends AbstractPiece {
	
	/** The is first move. */
	private boolean isFirstMove = true;
	
	/**
	 * Instantiates a new pawn.
	 *
	 * @param isCaptured the is captured
	 * @param coordinate the coordinate
	 * @param color the color
	 */
	public Pawn(boolean isCaptured, Coordinate coordinate, PieceColor color) {
		super();
		this.isCaptured = isCaptured;
		this.coordinate = coordinate;
		this.color = color;
	}

	@Override
	public List<Coordinate> allMoves() {
		List<Coordinate> coordinateList = new ArrayList<Coordinate>();
		Coordinate currentPos = getCoordinate();
		if (getColor() == PieceColor.WHITE && (currentPos.getPosY() - 1) >= 0) {
			coordinateList.add(new Coordinate(currentPos.getPosX(), currentPos.getPosY() - 1));
			if (isFirstMove) {
				coordinateList.add(new Coordinate(currentPos.getPosX(), currentPos.getPosY() - 2));
			}
		} else if (getColor() == PieceColor.BLACK && (currentPos.getPosY() + 1) <= 7) {
			coordinateList.add(new Coordinate(currentPos.getPosX(), currentPos.getPosY() + 1));
			if (isFirstMove) {
				coordinateList.add(new Coordinate(currentPos.getPosX(), currentPos.getPosY() + 2));
			}
		}
		
		return coordinateList;
	}

	/**
	 * Sets the first turn.
	 *
	 * @param isFirstMove the new first turn
	 */
	public void setFirstTurn(boolean isFirstMove) {
		this.isFirstMove = isFirstMove;
	}

}
