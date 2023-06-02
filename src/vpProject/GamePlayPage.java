package vpProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePlayPage extends JPanel{
	StateBoard stateBoard;
	Board board;
	JPanel footer;
	JButton playAgainBt, tryAgainBt, exitBt;
	
	GamePlayPage(){
		stateBoard = new StateBoard();
		board = new Board();
		add(stateBoard);
		add(board);
		
		footer = new JPanel();
		
		tryAgainBt = new JButton("Try Again");
		tryAgainBt.setIcon(null);
		footer.add(tryAgainBt);
		
		exitBt = new JButton("Exit");
		exitBt.setIcon(null);
		exitBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				TicTacToe.frame.setBounds(700,300,330,394);
				TicTacToe.main.setVisible(true);
				TicTacToe.frame.repaint();
			}		
		});
		footer.add(exitBt);
		
		add(footer);
	}
}
