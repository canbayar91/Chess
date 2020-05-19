/*
 * 
 */
package chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import chess.common.Coordinate;
import chess.common.PieceColor;
import chess.common.PieceType;
import chess.engine.MovementController;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * The Class Checkboard.
 */
public class Checkboard {

	/** The gui. */
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	
	/** The piece list. */
	private final List<Piece> pieceList;
	
	/** The chess board squares. */
	private final JButton[][] squares = new JButton[8][8];
	
	/** The chess piece ýmages. */
	private final Image[][] pieceImages = new Image[2][6];
	
	/** The chess board. */
	private JPanel chessboard;
	
	/** The selected piece. */
	private Coordinate selectedPiece;
	
	/** Boolean value decides if something is already selected. */
	private boolean isSelected;
	
	/** The turn, 0 in white's turn, 1 in black's. */
	private int turn;
	
	/** The is white king checked. */
	private boolean isWhiteKingChecked;
	
	/** The is black king checked. */
	private boolean isBlackKingChecked;

	/** The info panel. */
	private JPanel infoPanel;

	/** The white info button. */
	private JButton whiteInfoButton;

	/** The black info button. */
	private JButton blackInfoButton;
	
	/** The timer active. */
	private int timerActive;
	
	/** The timer. */
	private Timer timer;
	
	/** The counter1. */
	private int counter1;
	
	/** The counter2. */
	private int counter2;
	
	/** The delay. */
	private int delay;
	
	/** The is first timer. */
	private boolean isFirstTimer;
	
	/**
	 * Instantiates a new checkboard.
	 */
	public Checkboard() {
		// Common variables
		timerActive = JOptionPane.showConfirmDialog(null, "Would you like timer mode active?", "", JOptionPane.YES_NO_OPTION);
		pieceList = new ArrayList<Piece>();
		isSelected = false;
		isWhiteKingChecked = false;
		isBlackKingChecked = false;
		turn = 0;
		
		// Timer variables
		counter1 = 300;
		counter2 = 300;
		delay = 1000;
		isFirstTimer = true;
		
		// initialize GUI
		initializeGui();
	}

	/**
	 * Ýnitialize gui.
	 */
	public final void initializeGui() {
		
		// Create the images for the chess pieces
		createPieces();
		
		// Information panel of the game
		createInformationPanel();
		
		// Set up the main GUI
		chessboard = new JPanel(new GridLayout(0, 8));
		
		// Add chessboard to the main GUI
		gui.add(chessboard, BorderLayout.SOUTH);
		
		// Only execute if timer is active
		if (timerActive == JOptionPane.YES_OPTION) {
			whiteInfoButton.setText(counter1 + "");
			blackInfoButton.setText(counter2 + "");
			timer = new Timer(delay, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (counter1 == 0) {
						timer.stop();
						whiteInfoButton.setText(counter1 + "");
						whiteInfoButton.setBackground(Color.decode("#DD3E3E"));
						JOptionPane.showMessageDialog(null, "Black won the game.", "Time is up", JOptionPane.INFORMATION_MESSAGE);
						System.exit(-1);
					} else if (counter2 == 0) {
						timer.stop();
						blackInfoButton.setText(counter2 + "");
						blackInfoButton.setBackground(Color.decode("#DD3E3E"));
						JOptionPane.showMessageDialog(null, "White won the game.", "Time is up", JOptionPane.INFORMATION_MESSAGE);
						System.exit(-1);
					} else {
						if(isFirstTimer){
							whiteInfoButton.setText(counter1 + "");
							counter1--;
						} else {
							blackInfoButton.setText(counter2 + "");
							counter2--;
						}
					}
				}
			});
			
			timer.setInitialDelay(0);
			timer.start();
		}
		
		// Create the chess board squares
		createSquares();

		// Fill each square on the checkboard
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chessboard.add(squares[j][i]);
			}
		}
		
		// Sets up the game
		setupGame();
	}

	/**
	 * Creates the squares.
	 */
	private void createSquares() {
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int cols = 0; cols < squares.length; cols++) {
			for (int rows = 0; rows < squares[cols].length; rows++) {
				
				// Get button coordinates to use in action listener
				final int posX = rows;
				final int posY = cols;
				
				// Create an image button for each row
				final JButton button = new JButton();
				button.setMargin(buttonMargin);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// Adjust background
						reinitilizeColors();
						
						// Adjust icons and move the button, then update the data
						if(!isSelected) {
							selectedPiece = new Coordinate(posX, posY);
							ImageIcon icon = (ImageIcon) squares[selectedPiece.getPosX()][selectedPiece.getPosY()].getIcon();
							if (icon != null && decideTurn(selectedPiece, turn)) {
								isSelected = true;
								button.setBackground(Color.decode("#B8CFE5"));
								markAvailableSquares(new Coordinate(selectedPiece.getPosX(), selectedPiece.getPosY()));
							}
						} else {
							ImageIcon icon = (ImageIcon) squares[selectedPiece.getPosX()][selectedPiece.getPosY()].getIcon();
							if (icon != null) {
								Coordinate start = new Coordinate(selectedPiece.getPosX(), selectedPiece.getPosY());
								Coordinate end = new Coordinate(posX, posY);
								
								// Check if the place is available for move, if so move
								if (canMove(start, end)) {
									movePiece(start, end);
									
									// Controls the situation and forbids movement if check is continued
									boolean isDangerousPosition = false;
									if (isWhiteKingChecked || turn == 0) {
										if (isCheckSituation(PieceColor.WHITE)) {
											movePiece(end, start);
											Coordinate kingPosition = findKing(PieceColor.WHITE).getCoordinate();
											squares[kingPosition.getPosX()][kingPosition.getPosY()].setBackground(Color.decode("#DD3E3E"));
											isDangerousPosition = true;
										} else {
											isWhiteKingChecked = false;
										}
									} else if (isBlackKingChecked || turn == 1) {
										if (isCheckSituation(PieceColor.BLACK)) {
											movePiece(end, start);
											Coordinate kingPosition = findKing(PieceColor.BLACK).getCoordinate();
											squares[kingPosition.getPosX()][kingPosition.getPosY()].setBackground(Color.decode("#DD3E3E"));
											isDangerousPosition = true;
										} else {
											isBlackKingChecked = false;
										}
									}
									
									if (!isBlackKingChecked && !isWhiteKingChecked && !isDangerousPosition) {
										squares[selectedPiece.getPosX()][selectedPiece.getPosY()].setIcon(null);
										button.setIcon(icon);
										
										// Change turn and control the king
										Coordinate kingPosition;
										if (turn == 0) {
											kingPosition = findKing(PieceColor.BLACK).getCoordinate();
											if (isCheck(findPiece(end), kingPosition)) {
												isBlackKingChecked = true;
												squares[kingPosition.getPosX()][kingPosition.getPosY()].setBackground(Color.decode("#DD3E3E"));
											}
											turn = 1;
											isFirstTimer = false;
											if (timerActive != JOptionPane.YES_OPTION) {
												blackInfoButton.setBackground(Color.decode("#5CA5EB"));
												whiteInfoButton.setBackground(Color.WHITE);
											}
										} else {
											kingPosition = findKing(PieceColor.WHITE).getCoordinate();
											if (isCheck(findPiece(end), kingPosition)) {
												isWhiteKingChecked = true;
												squares[kingPosition.getPosX()][kingPosition.getPosY()].setBackground(Color.decode("#DD3E3E"));
											}
											turn = 0;
											isFirstTimer = true;
											if (timerActive != JOptionPane.YES_OPTION) {
												whiteInfoButton.setBackground(Color.decode("#5CA5EB"));
												blackInfoButton.setBackground(Color.BLACK);
											}
										}
										
										// Control if the move ended with check mate
										if (isWhiteKingChecked) {
											isCheckMate(PieceColor.WHITE, end);
										} else if (isBlackKingChecked) {
											isCheckMate(PieceColor.BLACK, end);
										}
									}
									
								}
							}
							isSelected = false;
						}
					}
				});
				
				// Define if the square is black or white
				if ((rows % 2 == 1 && cols % 2 == 1) || (rows % 2 == 0 && cols % 2 == 0)) {
					button.setBackground(Color.WHITE);
				} else {
					button.setBackground(Color.BLACK);
				}
				
				// Set the button
				squares[rows][cols] = button;
			}
		}
	}
	
	/**
	 * Decide turn.
	 *
	 * @param coordinate the coordinate
	 * @param turn the turn
	 * @return true, if successful
	 */
	private boolean decideTurn(Coordinate coordinate, int turn) {
		Piece piece = findPiece(coordinate);
		if (turn == 0 && piece.getColor() == PieceColor.WHITE) {
			return true;
		} else if (turn == 1 && piece.getColor() == PieceColor.BLACK) {
			return true;
		}
		return false;
	}
	
	/**
	 * Can move.
	 *
	 * @param start the start
	 * @param end the end
	 * @return true, if successful
	 */
	private boolean canMove(Coordinate start, Coordinate end) {
		boolean canMove = false;
		Piece piece = findPiece(start);
		List<Coordinate> list = piece.allMoves();
		list = MovementController.getAvailableSquares(list, piece, pieceList);
		for(Coordinate current : list) {
			if (current.getPosX() == end.getPosX() && current.getPosY() == end.getPosY()) {
				canMove = true;
				break;
			}
		}
		return canMove;
	}
	
	/**
	 * Move piece.
	 *
	 * @param start the start
	 * @param end the end
	 */
	private void movePiece(Coordinate start, Coordinate end) {
		// Remove if there already exists a piece
		Piece existingPiece = findPiece(end);
		if (existingPiece != null) {
			int index = pieceList.indexOf(existingPiece);
			pieceList.remove(index);
			existingPiece.setCaptured(true);
		}
		
		// Update the piece
		Piece piece = findPiece(start);
		piece.setCoordinate(end);
		
		// Pawn is able to move 2 square in the first move, so we execute this extra method
		if (piece instanceof Pawn) {
			((Pawn) piece).setFirstTurn(false);
		}
	}
	
	/**
	 * Mark available squares.
	 *
	 * @param coordinate the coordinate
	 */
	private void markAvailableSquares(Coordinate coordinate) {
		Piece piece = findPiece(coordinate);
		List<Coordinate> list = piece.allMoves();
		list = MovementController.getAvailableSquares(list, piece, pieceList);
		for(Coordinate current : list) {
			squares[current.getPosX()][current.getPosY()].setBackground(Color.decode("#A4F098"));
		}
	}
	
	/**
	 * Find piece.
	 *
	 * @param coordinate the coordinate
	 * @return the piece
	 */
	private Piece findPiece(Coordinate coordinate) {
		for (Piece piece : pieceList) {
			if (piece.getCoordinate().getPosX() == coordinate.getPosX() && piece.getCoordinate().getPosY() == coordinate.getPosY()) {
				return piece;
			}
		}
		return null;
	}
	
	/**
	 * Find king.
	 *
	 * @param color the color
	 * @return the king
	 */
	private King findKing(PieceColor color) {
		for (Piece piece : pieceList) {
			if (piece instanceof King && piece.getColor() == color){
				return (King) piece;
			}
		}
		return null;
	}
	
	/**
	 * Checks if is check.
	 *
	 * @param piece the piece
	 * @param kingPos the king pos
	 * @return true, if is check
	 */
	private boolean isCheck(Piece piece, Coordinate kingPos) {
		List<Coordinate> allMoves = MovementController.getAvailableSquares(piece.allMoves(), piece, pieceList);
		for (Coordinate coordinate : allMoves) {
			if (coordinate.getPosX() == kingPos.getPosX() && coordinate.getPosY() == kingPos.getPosY()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if there appears or continues a check situation, to decide move is available or not.
	 *
	 * @param color the color
	 * @return true, if is still check
	 */
	private boolean isCheckSituation(PieceColor color) {
		King king = findKing(color);
		for (Piece enemy : pieceList) {
			if (enemy.getColor() != color) {
				if (isCheck(enemy, king.getCoordinate())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if is check mate.
	 *
	 * @param color the color
	 * @param end 
	 */
	private void isCheckMate(PieceColor color, Coordinate end) {
		boolean isCheckMate = true;
		// Checks all possible moves to decide if game is over
		for (Piece piece : pieceList) {
			if (piece.getColor() == color) {
				// Keeps the piece's original position
				Coordinate originalPosition = piece.getCoordinate();
				List<Coordinate> allMoves = MovementController.getAvailableSquares(piece.allMoves(), piece, pieceList);
				// Controls each possible location
				for (Coordinate move : allMoves) {
					piece.setCoordinate(move);
					// Check if anything can take the attacker piece (except the king)
					if (move.getPosX() == end.getPosX() && move.getPosY() == end.getPosY() && !(piece instanceof King)) {
						isCheckMate = false;
						break;
					}
					// Checks if the king is still under danger
					boolean dangerContinues = isCheckSituation(color);
					// If an enemy threatens the king, no need to check the others
					if (!dangerContinues) {
						isCheckMate = false;
						break;
					}
				}
				piece.setCoordinate(originalPosition);
			}
			
			// No need to search forward if one possible outcome is already found
			if (!isCheckMate) {
				break;
			}
		}
		
		// If check mate, finish the game
		if (isCheckMate) {
			String winner;
			if (color == PieceColor.WHITE) {
				winner = "Black";
			} else {
				winner = "White";
			}
			
			JOptionPane.showMessageDialog(null, winner + " won the game.", "Checkmate", JOptionPane.INFORMATION_MESSAGE);
			System.exit(-1);
		}
	}

	/**
	 * Reinitilize colors.
	 */
	private void reinitilizeColors() {
		for (int row = 0; row < squares.length; row++) {
			for (int column = 0; column < squares[row].length; column++) {
				// Define if the square is black or white
				if ((column % 2 == 1 && row % 2 == 1) || (column % 2 == 0 && row % 2 == 0)) {
					squares[row][column].setBackground(Color.WHITE);
				} else {
					squares[row][column].setBackground(Color.BLACK);
				}
			}
		}
	}
	
	/**
	 * Creates the information panel.
	 */
	private void createInformationPanel() {
		// Create panel
		infoPanel = new JPanel(new GridLayout(0, 2));
		
		// White part
		whiteInfoButton = new JButton("White's Turn");
		whiteInfoButton.setBackground(Color.WHITE);
		whiteInfoButton.setPreferredSize(new Dimension(300, 100));
		whiteInfoButton.setFont(new Font("Verdana", Font.BOLD, 32));
		whiteInfoButton.setForeground(Color.BLACK);
		if (timerActive != JOptionPane.YES_OPTION) {
			whiteInfoButton.setBackground(Color.decode("#5CA5EB"));
		}
		infoPanel.add(whiteInfoButton, BorderLayout.WEST);
		
		// Black part
		blackInfoButton = new JButton("Black's Turn");
		blackInfoButton.setBackground(Color.BLACK);
		blackInfoButton.setPreferredSize(new Dimension(300, 100));
		blackInfoButton.setFont(new Font("Verdana", Font.BOLD, 32));
		blackInfoButton.setForeground(Color.WHITE);
		infoPanel.add(blackInfoButton, BorderLayout.EAST);
		
		gui.add(infoPanel, BorderLayout.NORTH);
	}

	/**
	 * Creates the images for pieces by taking them from the internet.
	 */
	private final void createPieces() {
		// Tries the connection, exits in failure
		try {
			URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bufferedImage = ImageIO.read(url);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 6; j++) {
					pieceImages[i][j] = bufferedImage.getSubimage(j * 64, i * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes the icons of the initial chess board piece places.
	 */
	private final void setupGame() {
		// Pre-defined first row
		PieceType[] startingRow = { PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN, 
			PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK };
		
		// Set up the black pieces in 1st row
		for (int i = 0; i < startingRow.length; i++) {
			squares[i][0].setIcon(new ImageIcon(pieceImages[0][startingRow[i].getId()]));
			Piece piece = createPiece(startingRow[i], PieceColor.BLACK, new Coordinate(i, 0));
			pieceList.add(piece);
		}
		
		// Set up the black pieces in 2nd row
		for (int i = 0; i < startingRow.length; i++) {
			squares[i][1].setIcon(new ImageIcon(pieceImages[0][PieceType.PAWN.getId()]));
			Piece piece = createPiece(PieceType.PAWN, PieceColor.BLACK, new Coordinate(i, 1));
			pieceList.add(piece);
		}
		
		// Set up the white pieces in 7th row
		for (int i = 0; i < startingRow.length; i++) {
			squares[i][6].setIcon(new ImageIcon(pieceImages[1][PieceType.PAWN.getId()]));
			Piece piece = createPiece(PieceType.PAWN, PieceColor.WHITE, new Coordinate(i, 6));
			pieceList.add(piece);
		}
		
		// Set up the white pieces in 8th row
		for (int i = 0; i < startingRow.length; i++) {
			squares[i][7].setIcon(new ImageIcon(pieceImages[1][startingRow[i].getId()]));
			Piece piece = createPiece(startingRow[i], PieceColor.WHITE, new Coordinate(i, 7));
			pieceList.add(piece);
		}
	}
	
	/**
	 * Creates the piece with the given type.
	 *
	 * @param type the type
	 * @param color the color
	 * @param coordinate the coordinate
	 * @return the piece
	 */
	private Piece createPiece(PieceType type, PieceColor color, Coordinate coordinate){
		Piece piece = null;
		if (type == PieceType.BISHOP) {
			piece = new Bishop(false, coordinate, color);
		} else if (type == PieceType.KING) {
			piece = new King(false, coordinate, color);
		} else if (type == PieceType.KNIGHT) {
			piece = new Knight(false, coordinate, color);
		} else if (type == PieceType.PAWN) {
			piece = new Pawn(false, coordinate, color);
		} else if (type == PieceType.QUEEN) {
			piece = new Queen(false, coordinate, color);
		} else if (type == PieceType.ROOK) {
			piece = new Rook(false, coordinate, color);
		}
		return piece;
	}
	
	/**
	 * Gets the gui to be used in the engine.
	 *
	 * @return the gui
	 */
	public final JComponent getGui() {
		return gui;
	}

}