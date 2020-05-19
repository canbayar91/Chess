package chess.engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import chess.gui.Checkboard;

/**
 * The Class Chess.
 */
public class Chess {

	/**
	 * The main method. This is the main class which starts the game
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				Checkboard board = new Checkboard();
				JFrame chess = new JFrame("Chess");
				chess.add(board.getGui());
				chess.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				chess.setLocationByPlatform(true);
				chess.pack();
				chess.setMinimumSize(chess.getSize());
				chess.setVisible(true);
				chess.setResizable(false);
			}
		};
		SwingUtilities.invokeLater(r);
	}

}
