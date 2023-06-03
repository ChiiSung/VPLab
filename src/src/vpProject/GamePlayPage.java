package vpProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePlayPage extends JPanel{
	StateBoard stateBoard;
	Board board;
	JPanel footer;
	static JButton playAgainBt, tryAgainBt;
	JButton exitBt;
	
	GamePlayPage(){
		initialize();
		footer = new JPanel();
		
		playAgainBt = new JButton("Play Again");
		playAgainBt.setIcon(null);
		playAgainBt.setVisible(false);
		playAgainBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				removeAll();
				repaint();
				revalidate();
				initialize();
			}		
		});
		footer.add(playAgainBt);
		
		tryAgainBt = new JButton("Try Again");
		tryAgainBt.setIcon(null);
		tryAgainBt.setVisible(false);
		tryAgainBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				removeAll();
				repaint();
				initialize();
			}		
		});
		footer.add(tryAgainBt);
		
		exitBt = new JButton("Exit");
		exitBt.setIcon(null);
		exitBt.setPreferredSize(new Dimension(100,40));
		exitBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				setVisible(false);
				removeAll();
				TicTacToe.frame.setSize(330,394);
				TicTacToe.main.setVisible(true);
				TicTacToe.frame.repaint();
			}		
		});
		footer.add(exitBt);

		add(footer);
	}
	
	public void initialize() {
		stateBoard = new StateBoard();
		board = new Board();
		add(stateBoard);
		add(board);
	}
}
