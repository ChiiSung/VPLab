package lab6;

import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
	

public class lab6 implements FocusListener{
	JTextField search;
	JFrame frame;
    ListSelectionModel listSelectionModel;
    
    int numberOfRow = 0,employeeNumber;
    
	
	lab6(){
		JPanel head = new JPanel();
		head.setLayout(new FlowLayout());
		
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
		
		JPanel tablePl = new JPanel();
		tablePl.setLayout(new GridLayout());
		
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				main.setVisible(false);
				addPl(main, frame, tablePl);
			}
			
		});
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				deletePl(main, tablePl);
			}
			
		});
		JButton edit = new JButton("Edit");
		edit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				main.setVisible(false);
				editPl(main, frame,tablePl);
			}
			
		});
		
		buildTable("", tablePl);
		
		search = new JTextField("Search emp_no",12);
		search.addFocusListener(this);
		search.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				refreshTable(tablePl, main);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
		});
		
		head.add(search);
		head.add(add);
		head.add(delete);
		head.add(edit);
		
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Lab6");
		
		main.add(head, BorderLayout.NORTH);
		main.add(tablePl,BorderLayout.CENTER);
		frame.add(main);
		
	}
	

	public void focusGained(FocusEvent e) {
		if(search.getText().contentEquals("Search emp_no") ) {
			search.setText("");
		}
		
	}

	public void focusLost(FocusEvent e) {
		if(search.getText().contentEquals("") ) {
			search.setText("Search emp_no");
		}
		
	}
	
	public void buildTable(String keyword,JPanel panel) {
		String[][] tableData = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeesdb","root","");
			
			String sql = "Select * From employees Where emp_no Like \"%" + (keyword.contentEquals("Search emp_no")? "":keyword)+"%\" ;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			int j = 0;
			while(rs.next()) {
				j++;
			}
			tableData = new String[j][6];
			rs = stmt.executeQuery(sql);
			
			int i = 0;
			while(rs.next()) {
				tableData[i][0] = String.valueOf(rs.getInt(1));
				tableData[i][1] = rs.getString(2);
				tableData[i][2] = rs.getString(3);
				tableData[i][3] = rs.getString(4); 
				tableData[i][4] = rs.getString(5);
				tableData[i][5] = rs.getString(6);
				i++;
			}
			con.close();
		}catch(SQLException e) {
				e.printStackTrace();
		}catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		String[] columnNames = { "Employee Number","Birth date","First name","Last Name","Gender","Hire date" };
        
        JTable display = new JTable(tableData, columnNames);
        listSelectionModel = display.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
        				numberOfRow = i;
                    }
                }

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeesdb","root","");
				
					String sql = "Select * From employees Where emp_no Like \"%" + (keyword.contentEquals("Search emp_no")? "":keyword) +"%\" ;";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					int i = 0;
					while(i <= numberOfRow) {
						rs.next();
						i++;
					}
					employeeNumber = rs.getInt(1);
					rs.close();
					con.close();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
        });
        display.setSelectionModel(listSelectionModel);
		display.setRowHeight(20);
        display.setVisible(true);
        display.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        display.hasFocus();
        display.setRowSelectionInterval(0, 0);
        JScrollPane tablePane = new JScrollPane(display);
        panel.add(tablePane);
	}
	
	public void addPl(JPanel main, JFrame frame, JPanel tablePl) {
		JTextField emp_no, birth_date, first_name, last_name, gender, hire_date;
		JPanel addpl = new JPanel();
		addpl.removeAll();
		
		addpl.setLayout(null);
		
		ImageIcon rt0 = new ImageIcon("return.png");
        Image rt1 = rt0.getImage();
        Image newrt1 = rt1.getScaledInstance(21, 21, java.awt.Image.SCALE_SMOOTH);
		ImageIcon rt = new ImageIcon(newrt1);
		JButton back = new JButton();
		back.setBounds(10, 10, 57, 21);
		back.setIcon(rt);
		back.setActionCommand("Return");
		back.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			main.setVisible(true);
			addpl.setVisible(false);
		  }
		});
		addpl.add(back);
		
		emp_no = new JTextField();
		emp_no.setBounds(219, 70, 96, 19);
		addpl.add(emp_no);
		emp_no.setColumns(10);
		
		birth_date = new JTextField();
		birth_date.setBounds(219, 110, 96, 19);
		addpl.add(birth_date);
		birth_date.setColumns(10);
		
		first_name = new JTextField();
		first_name.setBounds(219, 150, 96, 19);
		addpl.add(first_name);
		first_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Employee no :");
		lblNewLabel.setBounds(113, 73, 96, 13);
		addpl.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Birth date :");
		lblNewLabel_1.setBounds(113, 113, 96, 13);
		addpl.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("First name :");
		lblNewLabel_2.setBounds(113, 153, 96, 13);
		addpl.add(lblNewLabel_2);
		
		last_name = new JTextField();
		last_name.setBounds(219, 190, 96, 19);
		addpl.add(last_name);
		last_name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Last name :");
		lblNewLabel_3.setBounds(113, 193, 96, 13);
		addpl.add(lblNewLabel_3);
		
		gender = new JTextField();
		gender.setBounds(219, 230, 96, 19);
		addpl.add(gender);
		gender.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(388, 26, 2, 2);
		addpl.add(scrollPane);
		
		JLabel lblNewLabel_4 = new JLabel("Gender :");
		lblNewLabel_4.setBounds(113, 233, 96, 13);
		addpl.add(lblNewLabel_4);
		
		hire_date = new JTextField();
		hire_date.setBounds(219, 271, 96, 19);
		addpl.add(hire_date);
		hire_date.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Hire Date :");
		lblNewLabel_5.setBounds(113, 274, 96, 13);
		addpl.add(lblNewLabel_5);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeesdb","root","");
					
					String sql = "INSERT INTO employees(emp_no, birth_date, first_name, last_name, gender, hire_date) values('"
							+emp_no.getText()+"','"+birth_date.getText()+"','"+first_name.getText()+"','"+last_name.getText()+"','"+gender.getText()+"','"+hire_date.getText()+"')" ;
					Statement stmt = con.createStatement();
					stmt.executeUpdate(sql);
					
					con.close();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				main.setVisible(true);
				addpl.setVisible(false);
				refreshTable(tablePl, main);
			}
		});
		save.setBounds(203, 363, 85, 21);
		addpl.add(save);
		frame.add(addpl);
		
		refreshTable(tablePl, main);
	}
	
	public void deletePl(JPanel main, JPanel tablePl) {
		JPanel panel = new JPanel();
		int a = JOptionPane.showConfirmDialog(panel,"Delete employee with emp_no = "+ employeeNumber, "Delete Warning",JOptionPane.WARNING_MESSAGE); 
		if(a==JOptionPane.YES_OPTION){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeesdb","root","");
				
				String sql = "Delete From employees Where emp_no ="+ employeeNumber + ";";
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
				
				con.close();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}  
		refreshTable(tablePl, main);
		
	}
	
	public void editPl(JPanel main, JFrame frame, JPanel tablePl) {
		JTextField emp_no, last_name;
		JPanel editpl = new JPanel();
		editpl.removeAll();
		
		editpl.setLayout(null);
		
		ImageIcon rt0 = new ImageIcon("return.png");
        Image rt1 = rt0.getImage();
        Image newrt1 = rt1.getScaledInstance(21, 21, java.awt.Image.SCALE_SMOOTH);
		ImageIcon rt = new ImageIcon(newrt1);
		JButton back = new JButton();
		back.setBounds(10, 10, 57, 21);
		back.setIcon(rt);
		back.setActionCommand("Return");
		back.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			main.setVisible(true);
			editpl.setVisible(false);
		  }
		});
		editpl.add(back);
		
		emp_no = new JTextField();
		emp_no.setEditable(false);
		emp_no.setBounds(219, 70, 96, 19);
		emp_no.setText(String.valueOf(employeeNumber));
		editpl.add(emp_no);
		emp_no.setColumns(10);
		
		last_name = new JTextField();
		last_name.setBounds(219, 110, 96, 19);
		editpl.add(last_name);
		last_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Employee no :");
		lblNewLabel.setBounds(113, 73, 96, 13);
		editpl.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last name :");
		lblNewLabel_1.setBounds(113, 113, 96, 13);
		editpl.add(lblNewLabel_1);
		
		JButton save = new JButton("Change last name");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeesdb","root","");
					
					String sql = "Update employees Set last_name ='" + last_name.getText() + "' Where emp_no =" + employeeNumber +";";
					Statement stmt = con.createStatement();
					stmt.executeUpdate(sql);
					
					con.close();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				main.setVisible(true);
				editpl.setVisible(false);
				refreshTable(tablePl, main);
			}
		});
		save.setBounds(203, 363, 85, 21);
		editpl.add(save);
		frame.add(editpl);
		
	}
	
	public void refreshTable(JPanel tablePl, JPanel main) {
		tablePl.removeAll();
		tablePl.setVisible(false);
		main.add(tablePl,BorderLayout.CENTER);
		buildTable(search.getText(), tablePl);
		tablePl.setVisible(true);
	}
	
	
	public static void main(String arg[]) {
		new lab6();

	}
}
