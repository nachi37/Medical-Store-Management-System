package Source;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import  Source.Home;


import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class login {

	private JFrame frame;
	private JTextField username;
	public String setName="";
	private JPasswordField password;
	
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	
	//declaration of connection variables
	
	
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/testdb";
	String user="root";
	String pass=""; 

	
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.setBounds(280, 150, 830, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(338, 27, 162, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" User name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(202, 103, 137, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(220, 195, 119, 25);
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		//System.out.println( username);
		username.setBounds(434, 103, 86, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(434, 195, 86, 20);
		frame.getContentPane().add(password);
		
		JButton btnLogin = new JButton(" Login");
		btnLogin.setForeground(Color.GREEN);
		
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
					setName=username.getText();
				
					
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
					String query ="select * from login where username=? and password=?";
					PreparedStatement pst=con.prepareStatement( query);
					pst.setString( 1, setName=username.getText());
					
					pst.setString( 2, password.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) {
						count=count+1;
					}
					if(count==1) {
						
						
						//for redirecting to Home page
						String str=username.getText();
						Home object=new Home(str);
						object.setVisible();
						
						
					}
					else if(count>1) {
						JOptionPane.showMessageDialog(null,"Duplicate username and password");
					}
					else {
						JOptionPane.showMessageDialog(null,"username and password is not correct,,try again!");
					}
					
				}
				
			
				catch(Exception p){
							p.printStackTrace();
				}
		}
		});
		btnLogin.setBounds(360, 300, 140, 37);
		frame.getContentPane().add(btnLogin);
		
		JLabel label = new JLabel("");
		Image img1= new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		
		label.setBounds(28, 103, 119, 116);
		frame.getContentPane().add(label);
	}
}

