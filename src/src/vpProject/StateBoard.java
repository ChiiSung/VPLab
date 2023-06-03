package vpProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StateBoard extends JPanel implements ActionListener{
	JPanel statusPl, boardPl, buttonPl;
	JLabel boardLb, turnLb, timerLb, boardCountLb, playerLb, botLb;
	ImageIcon timerIcon = IconClass.createIcon("icon/clock.png",25,25);
	ImageIcon boardIcon = IconClass.createIcon("icon/boardinfo.png",25,25);
	ImageIcon playerIcon = IconClass.createIcon("icon/human.png",25,25);
	ImageIcon botPlayerIcon = IconClass.createIcon("icon/ai.png",25,25);
	int timer = 0, board = 0, player = 0, botPlayer = 0;
	Timer t;
	
	StateBoard(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		turnLb = new JLabel();
		turnLb.setFont(new Font("TimesRoman", Font.BOLD, 36));
		turnLb.setHorizontalTextPosition(JLabel.CENTER);
		turnLb.setAlignmentX(Component.CENTER_ALIGNMENT);
		turnPanel(1);
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap((TicTacToe.frame.getWidth()-timerIcon.getIconWidth()*4)/4);
		
		boardPl = new JPanel();
		boardPl.setLayout(flowLayout);
//		boardPl.setBorder(BorderFactory.createLineBorder(Color.black));
		boardPl.setAlignmentX(Component.CENTER_ALIGNMENT);
		setBoard();
		
		statusPl = new JPanel();
		statusPl.setLayout(new BoxLayout(statusPl,BoxLayout.Y_AXIS));
		statusPl.add(turnLb);
		statusPl.add(boardPl);
		
		add(statusPl);
		
		t = new Timer(1000,this);
		t.start();
	}
	
	public void turnPanel(int status) {
		switch(status) {
		case 1:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("You Turn");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 1 Turn");
			}
			break;
		case 2:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("Bot Turn");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 2 Turn");
			}
			break;
		case 3:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("You Won!");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 1 Won");
			}
			
			break;
		case 4:
			turnLb.setText("Draw!");
			break;
		case 5:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("You Lost!");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 2 Win");
			}
			
			break;
		default :
			System.out.println("Error in switch case of turnPanel");
			break;
		}
	}
	
	public void setBoard() {
		timerLb = new JLabel();
		timerLb.setIcon(timerIcon);
		timerLb.setVisible(TicTacToe.matchTimer);
		timerLb.setText(String.valueOf(timer));
		
		boardLb = new JLabel();
		boardLb.setIcon(boardIcon);
		boardLb.setVisible(TicTacToe.boardInfo);
		boardLb.setText(String.valueOf(board));
		
		playerLb = new JLabel();
		playerLb.setIcon(playerIcon);
		playerLb.setVisible(TicTacToe.playerCounter);
		playerLb.setText(String.valueOf(player));
		
		botLb = new JLabel();
		botLb.setIcon(botPlayerIcon);
		botLb.setVisible(TicTacToe.playerCounter);
		botLb.setText(String.valueOf(botPlayer));

		boardPl.add(timerLb);
		boardPl.add(boardLb);
		boardPl.add(playerLb);
		boardPl.add(botLb);
	}
	
	public void addBoard() {
		board++;
		boardLb.setText(String.valueOf(board));
	}
	
	public void numPlWin() {
		player++;
		playerLb.setText(String.valueOf(player));
	}
	
	public void numAiWin() {
		botPlayer++;
		botLb.setText(String.valueOf(botPlayer));
	}
	
	public void actionPerformed(ActionEvent arg0) {
		timer++;
		timerLb.setText(String.valueOf(timer));
	}
	
	
	
}