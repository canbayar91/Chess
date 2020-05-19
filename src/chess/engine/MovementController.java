package chess.engine;

import java.util.ArrayList;
import java.util.List;

import chess.common.Coordinate;
import chess.common.PieceColor;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * The Class MovementController.
 */
public class MovementController {
	
	/**
	 * Gets the available squares.
	 *
	 * @param list the list
	 * @param piece the piece
	 * @param pieces the pieces
	 * @return the available squares
	 */
	public static List<Coordinate> getAvailableSquares(List<Coordinate> list, Piece piece, List<Piece> pieces) {
		if (piece instanceof Bishop) {
			return getAvailableSquaresForBishop(list, (Bishop) piece, pieces);
		} else if (piece instanceof King) {
			return getAvailableSquaresForKing(list, (King) piece, pieces);
		} else if (piece instanceof Knight) {
			return getAvailableSquaresForKnight(list, (Knight) piece, pieces);
		} else if (piece instanceof Pawn) {
			return getAvailableSquaresForPawn(list, (Pawn) piece, pieces);
		} else if (piece instanceof Queen) {
			return getAvailableSquaresForQueen(list, (Queen) piece, pieces);
		} else if (piece instanceof Rook) {
			return getAvailableSquaresForRook(list, (Rook) piece, pieces);
		} else {
			return list;
		}
	}
	
	/**
	 * Gets the available squares for bishop.
	 *
	 * @param list the list
	 * @param bishop the bishop
	 * @param pieces the pieces
	 * @return the available squares for bishop
	 */
	public static List<Coordinate> getAvailableSquaresForBishop(List<Coordinate> list, Bishop bishop, List<Piece> pieces) {
		PieceColor pieceColor = bishop.getColor();
		Coordinate pieceCoordinate = bishop.getCoordinate();
		List<Coordinate> tempList = new ArrayList<Coordinate>();
		
		// To the right and bottom
		for (int i = pieceCoordinate.getPosX() + 1, j = pieceCoordinate.getPosY() + 1; i <= 7 && j <= 7; i++, j++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		// To the right and top
		for (int i = pieceCoordinate.getPosX() + 1, j = pieceCoordinate.getPosY() - 1; i <= 7 && j >= 0; i++, j--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		// To the left and bottom
		for (int i = pieceCoordinate.getPosX() - 1, j = pieceCoordinate.getPosY() + 1; i >= 0 && j <= 7; i--, j++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		// To the left and top
		for (int i = pieceCoordinate.getPosX() - 1, j = pieceCoordinate.getPosY() - 1; i >= 0 && j >= 0; i--, j--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		return tempList;
	}
	
	/**
	 * Gets the available squares for king.
	 *
	 * @param list the list
	 * @param king the king
	 * @param pieces the pieces
	 * @return the available squares for king
	 */
	public static List<Coordinate> getAvailableSquaresForKing(List<Coordinate> list, King king, List<Piece> pieces) {
		PieceColor color = king.getColor();
		List<Coordinate> tempList = new ArrayList<Coordinate>();
		for (Coordinate coordinate : list) {
			boolean skip = false;
			for (Piece piece : pieces) {
				Coordinate current = piece.getCoordinate();
				if (color == piece.getColor() && current.getPosX() == coordinate.getPosX()
						&& current.getPosY() == coordinate.getPosY()) {
					skip = true;
				}
			}
			if (!skip) {
				tempList.add(coordinate);
			}
		}
		return tempList;
	}
	
	/**
	 * Gets the available squares for knight.
	 *
	 * @param list the list
	 * @param knight the knight
	 * @param pieces the pieces
	 * @return the available squares for knight
	 */
	public static List<Coordinate> getAvailableSquaresForKnight(List<Coordinate> list, Knight knight, List<Piece> pieces) {
		PieceColor color = knight.getColor();
		List<Coordinate> tempList = new ArrayList<Coordinate>();
		for (Coordinate coordinate : list) {
			boolean skip = false;
			for (Piece piece : pieces) {
				Coordinate current = piece.getCoordinate();
				if (color == piece.getColor() && current.getPosX() == coordinate.getPosX()
						&& current.getPosY() == coordinate.getPosY()) {
					skip = true;
				}
			}
			if (!skip) {
				tempList.add(coordinate);
			}
		}
		return tempList;
	}
	
	/**
	 * Gets the available squares for pawn.
	 *
	 * @param list the list
	 * @param pawn the pawn
	 * @param pieces the pieces
	 * @return the available squares for pawn
	 */
	public static List<Coordinate> getAvailableSquaresForPawn(List<Coordinate> list, Pawn pawn, List<Piece> pieces) {
		List<Coordinate> tempList = new ArrayList<Coordinate>();
		for (Coordinate coordinate : list) {
			boolean skip = false;
			for (int i = 0; i < pieces.size(); i++) {
				Piece piece = pieces.get(i);
				Coordinate current = piece.getCoordinate();
				if (current.getPosX() == coordinate.getPosX() && current.getPosY() == coordinate.getPosY()) {
					skip = true;
				}
			}
			if (!skip) {
				tempList.add(coordinate);
			} else {
				break;
			}
		}
		
		Coordinate coordinate = pawn.getCoordinate();
		if (pawn.getColor() == PieceColor.BLACK) {
			for (Piece piece : pieces) {
				Coordinate current = piece.getCoordinate();
				if ((current.getPosX() == coordinate.getPosX() + 1) && (current.getPosY() == coordinate.getPosY() + 1)
						&& piece.getColor() == PieceColor.WHITE) {
					tempList.add(current);
				} else if ((current.getPosX() == coordinate.getPosX() - 1) && (current.getPosY() == coordinate.getPosY() + 1)
						&& piece.getColor() == PieceColor.WHITE) {
					tempList.add(current);
				}
			}
		} else {
			for (Piece piece : pieces) {
				Coordinate current = piece.getCoordinate();
				if ((current.getPosX() == coordinate.getPosX() + 1) && (current.getPosY() == coordinate.getPosY() - 1)
						&& piece.getColor() == PieceColor.BLACK) {
					tempList.add(current);
				} else if ((current.getPosX() == coordinate.getPosX() - 1) && (current.getPosY() == coordinate.getPosY() - 1)
						&& piece.getColor() == PieceColor.BLACK) {
					tempList.add(current);
				}
			}
		}
		
		return tempList;
	}
	
	/**
	 * Gets the available squares for queen.
	 *
	 * @param list the list
	 * @param queen the queen
	 * @param pieces the pieces
	 * @return the available squares for queen
	 */
	public static List<Coordinate> getAvailableSquaresForQueen(List<Coordinate> list, Queen queen, List<Piece> pieces) {
		PieceColor pieceColor = queen.getColor();
		Coordinate pieceCoordinate = queen.getCoordinate();
		List<Coordinate> tempList = new ArrayList<Coordinate>();
		
		// To the right
		for (int i = pieceCoordinate.getPosX() + 1; i <= 7; i++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == pieceCoordinate.getPosY()) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, pieceCoordinate.getPosY()));
			} else {
				break;
			}
		}
		
		// To the left
		for (int i = pieceCoordinate.getPosX() - 1; i >= 0; i--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == pieceCoordinate.getPosY()) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, pieceCoordinate.getPosY()));
			} else {
				break;
			}
		}
		
		// To the bottom
		for (int i = pieceCoordinate.getPosY() + 1; i <= 7; i++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == pieceCoordinate.getPosX() && piece.getCoordinate().getPosY() == i) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(pieceCoordinate.getPosX(), i));
			} else {
				break;
			}
		}
		
		// To the top
		for (int i = pieceCoordinate.getPosY() - 1; i >= 0; i--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == pieceCoordinate.getPosX() && piece.getCoordinate().getPosY() == i) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(pieceCoordinate.getPosX(), i));
			} else {
				break;
			}
		}
		
		// To the right and bottom
		for (int i = pieceCoordinate.getPosX() + 1, j = pieceCoordinate.getPosY() + 1; i <= 7 && j <= 7; i++, j++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		// To the right and top
		for (int i = pieceCoordinate.getPosX() + 1, j = pieceCoordinate.getPosY() - 1; i <= 7 && j >= 0; i++, j--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		// To the left and bottom
		for (int i = pieceCoordinate.getPosX() - 1, j = pieceCoordinate.getPosY() + 1; i >= 0 && j <= 7; i--, j++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		// To the left and top
		for (int i = pieceCoordinate.getPosX() - 1, j = pieceCoordinate.getPosY() - 1; i >= 0 && j >= 0; i--, j--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == j) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		
		return tempList;
	}
	
	/**
	 * Gets the available squares for rook.
	 *
	 * @param list the list
	 * @param rook the rook
	 * @param pieces the pieces
	 * @return the available squares for rook
	 */
	public static List<Coordinate> getAvailableSquaresForRook(List<Coordinate> list, Rook rook, List<Piece> pieces) {
		PieceColor pieceColor = rook.getColor();
		Coordinate pieceCoordinate = rook.getCoordinate();
		List<Coordinate> tempList = new ArrayList<Coordinate>();
		
		// To the right
		for (int i = pieceCoordinate.getPosX() + 1; i <= 7; i++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == pieceCoordinate.getPosY()) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, pieceCoordinate.getPosY()));
			} else {
				break;
			}
		}
		
		// To the left
		for (int i = pieceCoordinate.getPosX() - 1; i >= 0; i--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == i && piece.getCoordinate().getPosY() == pieceCoordinate.getPosY()) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(i, pieceCoordinate.getPosY()));
			} else {
				break;
			}
		}
		
		// To the bottom
		for (int i = pieceCoordinate.getPosY() + 1; i <= 7; i++) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == pieceCoordinate.getPosX() && piece.getCoordinate().getPosY() == i) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(pieceCoordinate.getPosX(), i));
			} else {
				break;
			}
		}
		
		// To the top
		for (int i = pieceCoordinate.getPosY() - 1; i >= 0; i--) {
			boolean isOccupied = false;
			for (Piece piece : pieces) {
				if (piece.getCoordinate().getPosX() == pieceCoordinate.getPosX() && piece.getCoordinate().getPosY() == i) {
					isOccupied = true;
					if (piece.getColor() != pieceColor) {
						tempList.add(piece.getCoordinate());
					} 
					break;
				}
			}
			if (!isOccupied) {
				tempList.add(new Coordinate(pieceCoordinate.getPosX(), i));
			} else {
				break;
			}
		}
		
		return tempList;
	}

}
