package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.common.Coordinate;
import chess.common.PieceColor;

/**
 * The Class Bishop.
 */
public class Bishop extends AbstractPiece {
	
	/**
	 * Instantiates a new bishop.
	 *
	 * @param isCaptured the is captured
	 * @param coordinate the coordinate
	 * @param color the color
	 */
	public Bishop(boolean isCaptured, Coordinate coordinate, PieceColor color) {
		super();
		this.isCaptured = isCaptured;
		this.coordinate = coordinate;
		this.color = color;
	}

	@Override
	public List<Coordinate> allMoves() {
		List<Coordinate> coordinateList = new ArrayList<Coordinate>();
		Coordinate currentPos = getCoordinate();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int x = Math.abs(currentPos.getPosX() - i);
				int y = Math.abs(currentPos.getPosY() - j);
				// Add all except the current position
				if(x == y && !(x == 0 & y == 0)) {
					coordinateList.add(new Coordinate(i, j));
				}
			}
		}
		return coordinateList;
	}

}
