package GameDevelopment;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
	public static int BOARD_SIZE = 3;

	public static enum Gamestatus {
		Incomplete, Xwins, Zwins, Tie;

	}

	boolean crossturn = true;
	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];

	public TicTacToe() {
		super.setTitle("TicTacToe");
		super.setSize(600, 600);
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans", 1, 150);
		for (int r = 0; r < BOARD_SIZE; r++) {
			for (int c = 0; c < BOARD_SIZE; c++) {
				JButton button = new JButton("");
				button.setFont(font);
				buttons[r][c] = button;
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton) e.getSource();
		makeMove(clicked);
		Gamestatus gs = this.getgameStatus();
		if (gs == Gamestatus.Incomplete)
			return;
		DeclareWinner(gs);
		int choice=JOptionPane.showConfirmDialog(this,"Would you like to restart the game ??");
		if(choice==JOptionPane.YES_OPTION) {
			for (int r = 0; r < BOARD_SIZE; r++) {
				for (int c = 0; c < BOARD_SIZE; c++) {
					buttons[r][c].setText("");
				}
			}
			crossturn=true;
		}else {
			super.dispose();
		}
	}

	private void makeMove(JButton clicked) {
		String check = clicked.getText();
		if (check.length() == 0) {
			if (crossturn) {
				clicked.setText("X");
				crossturn = false;
			} else {
				clicked.setText("O");
				crossturn = true;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Invalid Move");
			return;
		}

	}

	private Gamestatus getgameStatus() {
		String text1 = "", text2 = "";
		int r = 0, c = 0;
		while (r < BOARD_SIZE) {
			c = 0;
			while (c < BOARD_SIZE - 1) {
				text1 = buttons[r][c].getText();
				text2 = buttons[r][c + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0)
					break;
				c++;
			}
			if (c == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return Gamestatus.Xwins;
				} else if (text1.equals("O"))
					return Gamestatus.Zwins;
			}
			r++;
		}
		c = 0;
		while (c < BOARD_SIZE) {
			r = 0;
			while (r < BOARD_SIZE - 1) {
				text1 = buttons[r][c].getText();
				text2 = buttons[r + 1][c].getText();
				if (!text1.equals(text2) || text1.length() == 0)
					break;
				r++;
			}
			if (r == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return Gamestatus.Xwins;
				} else if (text1.equals("O"))
					return Gamestatus.Zwins;
			}
			c++;
		}
		r = 0;
		c = 0;
		while (r < BOARD_SIZE-1) {
			text1 = buttons[r][c].getText();
			text2 = buttons[r + 1][c + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0)
				break;
			r++;
			c++;
		}
		if (c == BOARD_SIZE - 1) {
			if (text1.equals("X")) {
				return Gamestatus.Xwins;
			} else if (text1.equals("O"))
				return Gamestatus.Zwins;
		}
		r = 0;
		c = BOARD_SIZE - 1;
		while (r < BOARD_SIZE-1) {
			text1 = buttons[r][c].getText();
			text2 = buttons[r + 1][c - 1].getText();
			if (!text1.equals(text2) || text1.length() == 0)
				break;
			r++;
			c--;
		}
		if (r == BOARD_SIZE - 1) {
			if (text1.equals("X")) {
				return Gamestatus.Xwins;
			} else if (text1.equals("O"))
				return Gamestatus.Zwins;
		}
		String txt = "";
		for (r = 0; r < BOARD_SIZE; r++) {
			for (c = 0; c < BOARD_SIZE; c++) {
				txt = buttons[r][c].getText();
				if (txt.length() == 0) {
					return Gamestatus.Incomplete;
				}
			}
		}
		return Gamestatus.Tie;
	}

	private void DeclareWinner(Gamestatus gs) {
		if (gs == Gamestatus.Xwins) {
			JOptionPane.showMessageDialog(this, "X wins");
		} else if (gs == Gamestatus.Zwins) {
			JOptionPane.showMessageDialog(this, "O wins");
		} else {
			JOptionPane.showMessageDialog(this, "Tie");
		}
	}

}
