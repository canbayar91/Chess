package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.common.Coordinate;
import chess.common.PieceColor;

/**
 * The Class Knight.
 */
public class Knight extends AbstractPiece {
	
	/**
	 * Instantiates a new knight.
	 *
	 * @param isCaptured the is captured
	 * @param coordinate the coordinate
	 * @param color the color
	 */
	public Knight(boolean isCaptured, Coordinate coordinate, PieceColor color) {
		super();
		this.isCaptured = isCaptured;
		this.coordinate = coordinate;
		this.color = color;
	}

	@Override
	public List<Coordinate> allMoves() {
		List<Coordinate> coordinateList = new ArrayList<Coordinate>();
		Coordinate currentPos = getCoordinate();

		// There are only eight conditions, each checked individually
		if (currentPos.getPosX() + 1 <= 7 && currentPos.getPosY() + 2 <= 7) {
			coordinateList.add(new Coordinate(currentPos.getPosX() + 1, currentPos.getPosY() + 2));
		}
		
		if (currentPos.getPosX() + 2 <= 7 && currentPos.getPosY() + 1 <= 7) {
			coordinateList.add(new Coordinate(currentPos.getPosX() + 2, currentPos.getPosY() + 1));
		}
		
		if (currentPos.getPosX() + 1 <= 7 && currentPos.getPosY() - 2 >= 0) {
			coordinateList.add(new Coordinate(currentPos.getPosX() + 1, currentPos.getPosY() - 2));
		}
		
		if (currentPos.getPosX() - 2 >= 0 && currentPos.getPosY() + 1 <= 7) {
			coordinateList.add(new Coordinate(currentPos.getPosX() - 2, currentPos.getPosY() + 1));
		}
		
		if (currentPos.getPosX() - 1 >= 0 && currentPos.getPosY() + 2 <= 7) {
			coordinateList.add(new Coordinate(currentPos.getPosX() - 1, currentPos.getPosY() + 2));
		}
		
		if (currentPos.getPosX() + 2 <= 7 && currentPos.getPosY() - 1 >= 0) {
			coordinateList.add(new Coordinate(currentPos.getPosX() + 2, currentPos.getPosY() - 1));
		}
		
		if (currentPos.getPosX() - 1 >= 0 && currentPos.getPosY() - 2 >= 0) {
			coordinateList.add(new Coordinate(currentPos.getPosX() - 1, currentPos.getPosY() - 2));
		}
		
		if (currentPos.getPosX() - 2 >= 0 && currentPos.getPosY() - 1 >= 0) {
			coordinateList.add(new Coordinate(currentPos.getPosX() - 2, currentPos.getPosY() - 1));
		}
		
		return coordinateList;
	}

}
