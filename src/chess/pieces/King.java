package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.common.Coordinate;
import chess.common.PieceColor;

/**
 * The Class King.
 */
public class King extends AbstractPiece {
	
	/**
	 * Instantiates a new king.
	 *
	 * @param isCaptured the is captured
	 * @param coordinate the coordinate
	 * @param color the color
	 */
	public King(boolean isCaptured, Coordinate coordinate, PieceColor color) {
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
		if(currentPos.getPosX() + 1 <= 7){
			coordinateList.add(new Coordinate(currentPos.getPosX() + 1, currentPos.getPosY()));
		}
		
		if(currentPos.getPosY() + 1 <= 7){
			coordinateList.add(new Coordinate(currentPos.getPosX(), currentPos.getPosY() + 1));
		}
		
		if(currentPos.getPosY() - 1 >= 0){
			coordinateList.add(new Coordinate(currentPos.getPosX(), currentPos.getPosY() - 1));
		}
		
		if(currentPos.getPosX() - 1 >= 0){
			coordinateList.add(new Coordinate(currentPos.getPosX() - 1, currentPos.getPosY()));
		}
		
		if(currentPos.getPosX() + 1 <= 7 && currentPos.getPosY() + 1 <= 7){
			coordinateList.add(new Coordinate(currentPos.getPosX() + 1, currentPos.getPosY() + 1));
		}
		
		if(currentPos.getPosX() - 1 >= 0 && currentPos.getPosY() + 1 <= 7){
			coordinateList.add(new Coordinate(currentPos.getPosX() - 1, currentPos.getPosY() + 1));
		}
		
		if(currentPos.getPosX() + 1 <= 7 && currentPos.getPosY() - 1 >= 0){
			coordinateList.add(new Coordinate(currentPos.getPosX() + 1, currentPos.getPosY() - 1));
		}
		
		if(currentPos.getPosX() - 1 >= 0 && currentPos.getPosY() - 1 >= 0){
			coordinateList.add(new Coordinate(currentPos.getPosX() - 1, currentPos.getPosY() - 1));
		}
		
		return coordinateList;
	}

}
