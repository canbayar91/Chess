package chess.pieces;

import java.util.List;

import chess.common.Coordinate;
import chess.common.PieceColor;

/**
 * The Class AbstractPiece.
 */
public abstract class AbstractPiece implements Piece {

	/** The is captured. */
	protected boolean isCaptured = false;
	
	/** The coordinate. */
	protected Coordinate coordinate;
	
	/** The color. */
	protected PieceColor color;
	
	@Override
	public boolean checkMove(Coordinate start, Coordinate end) {
		List<Coordinate> coordinateList = allMoves();
		for (Coordinate coordinate : coordinateList) {
			if (coordinate.getPosX() == end.getPosX() && coordinate.getPosY() == end.getPosY()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isCaptured() {
		return isCaptured;
	}

	@Override
	public void setCaptured(boolean captured) {
		isCaptured = captured;
	}
	@Override
	public Coordinate getCoordinate() {
		return coordinate;
	}

	@Override
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public void setColor(PieceColor color) {
		this.color = color;
	}
	
}
