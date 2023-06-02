package vpProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel{
	int numberOfBoard = TicTacToe.board + 3;
	JButton board[][];
	ImageIcon XIcon;
	ImageIcon OIcon;
	
	Board() {
		int lenght = TicTacToe.frame.getWidth()/numberOfBoard;
		XIcon = IconClass.createIcon("icon/x.png",lenght,lenght);
		OIcon = IconClass.createIcon("icon/circle.png",lenght,lenght);
		
		setLayout(new GridLayout(numberOfBoard,numberOfBoard,5,5));
		
		board = new JButton[numberOfBoard][numberOfBoard];
		for(int i = 0; i < numberOfBoard ; i++) {
			for(int j = 0 ; j < numberOfBoard ; j++) {
				board[i][j] = new JButton();
				board[i][j].setPreferredSize(new Dimension(lenght,lenght));
				board[i][j].setActionCommand(i + "/" + j);
				board[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String sp = e.getActionCommand();
						String p[] = sp.split("/");
						int i = Integer.valueOf(p[0]);
						int j = Integer.valueOf(p[1]);
						board[i][j].setEnabled(false);
						board[i][j].setIcon(XIcon);
//						System.out.println(sp);
						calWin();
					}
				});
				add(board[i][j]);
			}
		}
	}
	
	public void easyAi() {
		
	}
	
	public void hardAi() {
		
	}
	
	public void calWin() {
		int rowCount = 0;
		int colunmCount = 0;
		int xCount = 0; 
		int x = 0, y = 0; //x mean the horizon line is form, then y is vertical
		//check the board for which have become a line, for horizontal and vertical only
		for(int i=0; i < numberOfBoard; i++) {
			for(int j = 0; j < numberOfBoard; j++) {
				if(board[i][j].getIcon() == XIcon) {
					rowCount++;
				}
				if(board[j][i].getIcon() == XIcon) {
					colunmCount++;
				}
			}
			if(rowCount == numberOfBoard) {
				x++;
				break;
			}else if(colunmCount == numberOfBoard) {
				y++;
				break;
			}else {
				rowCount = colunmCount = 0;
			}
		}
		
		if(x == 1) {
			System.out.println("You win (H)");
		}else if(y == 1) {
			System.out.println("you win (V)");
		}
		
		//check the board for which have become a line for X shape
		for(int i = 0; i < numberOfBoard; i++) {
			if(board[i][i].getIcon() == XIcon) {
				xCount++;
			}
		}
		if(xCount == numberOfBoard) {
			System.out.println("You win ( \\ )");
		}else {
			xCount = 0;
		}
		
		for(int i = 0, j = numberOfBoard - 1; i < numberOfBoard; i++,j--) {
			if(board[i][j].getIcon() == XIcon) {
				xCount++;
			}
		}
		if(xCount == numberOfBoard) {
			System.out.println("You Win (/)");
		}	
	}
}
