import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class TicTacToe extends JPanel  {

	private static char[][] board = new char[3][3];
	private static JButton[][] buttons = new JButton[3][3];
	private static int win = 0;
	private static volatile int turns = 0;
	private static final long serialVersionUID = -5000340731995300905L;
	private static JFrame frame;
	private static JLabel topInfo;
	private static JPanel grid;
	private static JButton reset;
	private static int[] values = { 0, 0, 0 };

	public static void main(String[] args) {
		frame = new JFrame("Tic Tac Toe");
		frame.setSize(400, 400);
		frame.setLocation(200, 200);
		frame.setContentPane(new TicTacToe());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			while (!done()) {
				if (turns > 0) {
					int yo = turns % 2 + values[2];
					if (yo % 2 == 0)
						topInfo.setText("X turn");
					else
						topInfo.setText("O turn");
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					buttons[i][j].setEnabled(false);
				}
			}
			String first = "";
			if (win == 1) {
				first = ("X WINS!");
				values[0]++;
			} else if (win == 2) {
				first = ("O WINS!");
				values[1]++;
			} else {
				first = ("TIE!");
			}
			topInfo.setText(first + " | X Wins: " + values[0] + " | O Wins: " + values[1]);
			values[2]++;
			if (values[2] > 1)
				values[2] = 0;
			while (turns > 0) {}
		}
	}

	public TicTacToe() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		grid = new JPanel(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
				JButton button = new JButton("");
				button.addActionListener(new BPress(i, j));
				button.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
				buttons[i][j] = button;
				grid.add(button);
			}
		}
		topInfo = new JLabel("Tic Tac Toe");
		topInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(topInfo);
		add(grid);
		reset = new JButton("Reset");
		reset.addActionListener((e) -> 	reset());
		reset.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(reset);
	}

	public static boolean done() {
		for (int w = 1; w <= 2; w++) {
			char c = (w == 1) ? 'X' : 'O';
			for (int i = 0; i < 3; i++) {
				if (board[i][0] == c && board[i][1] == c && board[i][2] == c) {
					win = w;
					return true;
				}
				if (board[0][i] == c && board[1][i] == c && board[2][i] == c) {
					win = w;
					return true;
				}
			}
			if (board[0][0] == c && board[1][1] == c && board[2][2] == c) {
				win = w;
				return true;
			}
			if (board[0][2] == c && board[1][1] == c && board[2][0] == c) {
				win = w;
				return true;
			}
		}
		if (turns == 9) {
			win = 3;
			return true;
		}
		return false;
	}
	
	private class BPress extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private int row;
		private int col;

		public BPress(int r, int c) {
			row = r;
			col = c;
		}

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			int yo = turns % 2 + values[2];
			if (yo % 2 == 0)
				board[row][col] = 'X';
			else
				board[row][col] = 'O';
			button.setText(board[row][col] + "");
			button.setEnabled(false);
			turns++;
		}
	}

	public void reset() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
				buttons[i][j].setEnabled(true);
				buttons[i][j].setText("");
			}
		}
		if (values[2] == 0)
			topInfo.setText("X starts");
		else
			topInfo.setText("O starts");
		turns = 0;
	}

}
