package Source;

import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPasswordField;
public class Home {

	private JFrame frame;
	private JTextField id;
	private JTextField cost;
	private JTextField name;
	private JTextField expiry;
	private JTextField quantity;
	private JTextField location;
	private JTextField del_id;
	private JTable table;
	private JTable tableSearch;
	private JTextField textFieldSearch;
	private JLabel lblTime;
	
	private JLabel lblLoggedInAs;

	/**
	 * Launch the application.
	 */
	
	
	login object=new login();
	
	
	
	public void clock() {
		
		Thread clock=new Thread() {
			public void run() {
				try {
					while(true) {
					Calendar calendar=new GregorianCalendar();
			        int hour=calendar.get(Calendar.HOUR);
			        int min=calendar.get(Calendar.MINUTE);
			        int p=(calendar.get(Calendar.SECOND));
			        String Second="";
			        String Minute="";
			        String Hour="";
			        if(p  <10)
			        Second="0"+p;
			        else Second=""+p;
			        if(min<10)
			        	Minute="0"+min;
			        else Minute=""+min;
			        if(hour<10)
			        	Hour="0"+hour;
			        else
			        	Hour=""+hour;
			        	
			     //   String display=" "+hour+" : "+min+" : "+sec;
			        lblTime.setText("Time :"+Hour+":"+Minute+":"+Second);
					
				sleep(1000);}}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
	}
		
	public static void main(String[] args) {
		
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	
	}

	public Home(String para) {
		initialize();
	clock();
	
	lblLoggedInAs.setText("Logged in & as :"+para);
	
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	//Declaration of jdbc connecting variables
		
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/testdb";
		String user="root";
		String pass="";
		private JTable table_1;
		private JTextField textField;
		private JTextField txtEnterCustomerName;
		private JTextField newuser;
		private JPasswordField newpass;
		
		
		//for setvisibility in login.java
		
		public void setVisible() {
			frame.setVisible( true);
		}
		
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 21));
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(0, 0, 1366, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.RED);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		tabbedPane.setBounds(52, 107, 1248, 527);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setToolTipText("");
		panel_2.setBorder(new LineBorder(Color.GREEN, 3));
		panel_2.setForeground(Color.WHITE);
		panel_2.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab(" HOME", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblSearchById = new JLabel(" Search by:");
		lblSearchById.setBounds(55, 65, 108, 14);
		panel_2.add(lblSearchById);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 122, 527, 261);
		panel_2.add(scrollPane_1);
		
		JTable tableSearch = new JTable();
		scrollPane_1.setViewportView(tableSearch);
		tableSearch.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id ", "Name", "Quantity", "Location", "Cost", "Expiry Date","Type"
			}
		));
		
		JComboBox comboBoxSearch = new JComboBox();
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"id", "name", "type"}));
		comboBoxSearch.setBounds(177, 64, 91, 17);
		panel_2.add(comboBoxSearch);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
	
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
					String selection=(String)comboBoxSearch.getSelectedItem();
					String a= textFieldSearch.getText();
				String query="select * from medicine where "+selection+"=?   ";
				
					PreparedStatement pst=con.prepareStatement( query);
					
					//String key="select * from medicine where "+selection+" like %a% "  ;
					//PreparedStatement pst=con.prepareStatement( key);
				//	pst.setString( 1, key);
					
				 pst.setString( 1, textFieldSearch.getText());
	
					ResultSet rs=pst.executeQuery();
					
					tableSearch.setModel(DbUtils.resultSetToTableModel(rs));
					
				
				}
					catch(Exception ex) {
						ex.printStackTrace();
				}
				
			}
		});
		textFieldSearch.setBounds(348, 62, 86, 20);
		panel_2.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearchResults = new JLabel(" Search Results:");
		lblSearchResults.setBounds(55, 97, 101, 14);
		panel_2.add(lblSearchResults);
		
		JButton btnGeneratepayslip = new JButton(" GeneratePaySlip");
		btnGeneratepayslip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String custom=txtEnterCustomerName.getText();
					GenerateSlip obj=new GenerateSlip(custom);
					
					TableModel model1=table_1.getModel();
					int indexes[]=table_1.getSelectedRows();
					Object [] row=new Object[3];
					DefaultTableModel model2=(DefaultTableModel)obj.table.getModel();
					
					
					for(int i=0;i<indexes.length;i++) {
						
						
						row[0]=model1.getValueAt( indexes[i], 1);
						row[1]=model1.getValueAt( indexes[i], 2);
						row[2]=model1.getValueAt( indexes[i], 3);
						model2.addRow(row);
					}
					
				obj.setVisible1();
				 
				}
				catch(Exception e) {e.printStackTrace( );}
			}
		});
		btnGeneratepayslip.setBounds(782, 447, 157, 30);
		panel_2.add(btnGeneratepayslip);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(719, 122, 479, 255);
		panel_2.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Cost", "Quantity"
			}
		));
		DefaultTableModel model=(DefaultTableModel) table_1.getModel();
		scrollPane_2.setViewportView(table_1);
		
		JTextField textFieldSelect = new JTextField();
		
		
		
		
		textFieldSelect.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
	
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
				
	
			
				
					
					String key="select * from medicine where id=? ";
					PreparedStatement pst=con.prepareStatement( key);
					pst.setString( 1, key);
					
				 pst.setString( 1, textFieldSelect.getText());
	
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						
						String data1,data2,data3,data4;
						data1=rs.getString( "id");
						data2=rs.getString( "name");
						data3=rs.getString( "cost");
						data4=rs.getString( "quantity");
						model.addRow( new Object[] {data1,data2,data3,data4});
						
						
					}
					
				
				}
					catch(Exception ex) {
						ex.printStackTrace();
				}
				
			}
		});
		
		
		
		
		
		
		
		
		textFieldSelect.setBounds(934, 389, 108, 20);
		panel_2.add(textFieldSelect);
		textFieldSelect.setColumns(10);
		
		JLabel btnEnterToSelect = new JLabel(" Enter Id to Select : ");
		
	/*	btnEnterToSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			
				
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
				
					
					String query="select id,name,cost,quantity from medicine where id=?   ";
				
					PreparedStatement pst=con.prepareStatement( query);
					pst.setString( 1, textFieldSelect.getText());
	
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						
						String data1,data2,data3,data4;
						data1=rs.getString( "id");
						data2=rs.getString( "name");
						data3=rs.getString( "cost");
						data4=rs.getString( "quantity");
						model.addRow( new Object[] {data1,data2,data3,data4});
						
						
					}
					
				//	table_1.setModel(DbUtils.resultSetToTableModel(rs));
					GenerateSlip obj1=new GenerateSlip();
					obj1.table.setModel( DbUtils.resultSetToTableModel(rs));
					
					
				}
					catch(Exception ex) {
						ex.printStackTrace();
				}
				
				
				
				
			}
		});
		*/
		
		btnEnterToSelect.setBounds(729, 388, 157, 23);
		panel_2.add(btnEnterToSelect);
		
		txtEnterCustomerName = new JTextField();
		txtEnterCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtEnterCustomerName.setToolTipText(" ");
		txtEnterCustomerName.setBounds(934, 420, 108, 20);
		panel_2.add(txtEnterCustomerName);
		txtEnterCustomerName.setColumns(10);
		
		JLabel lblEnterCustomerName = new JLabel(" Enter  Customer Name :");
		lblEnterCustomerName.setBounds(728, 422, 158, 14);
		panel_2.add(lblEnterCustomerName);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblSearchById, scrollPane_1, tableSearch, comboBoxSearch, textFieldSearch, lblSearchResults, btnGeneratepayslip, scrollPane_2, table_1, textFieldSelect, btnEnterToSelect, txtEnterCustomerName}));
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBorder(new LineBorder(Color.GREEN, 3));
		tabbedPane.addTab("RemoveMedicine", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblDeleteTheMedicine = new JLabel(" Delete the Medicine By Id");
		lblDeleteTheMedicine.setBounds(78, 47, 232, 14);
		panel_3.add(lblDeleteTheMedicine);
		
		del_id = new JTextField();
		del_id.setBounds(260, 44, 86, 20);
		panel_3.add(del_id);
		del_id.setColumns(10);
		
		JButton btnDelete = new JButton(" Remove");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
					String query="Delete from medicine where id=? ";
					PreparedStatement pst=con.prepareStatement( query);
					
					pst.setString( 1, del_id.getText());
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Deleted Successfully");
				}
				catch(Exception exp) {
					System.out.println( "Error"+exp);
				
			}
				
				
				
				
				
			}
		});
		btnDelete.setBounds(376, 43, 89, 23);
		panel_3.add(btnDelete);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GREEN, 3, true));
		panel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("DisplayMedicine", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setBounds(210, 61, 770, 339);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setToolTipText("");
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Cost", "Expiry date", "Location", "Quantity", "Type"
			}
		));
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		JButton btnDisplay = new JButton(" Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
					
					String query="select * from medicine ";
					PreparedStatement pst=con.prepareStatement( query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
					catch(Exception ex) {
						ex.printStackTrace();
				
			}
				}
		});
		btnDisplay.setBounds(555, 11, 89, 23);
		panel.add(btnDisplay);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new LineBorder(Color.CYAN, 4));
		tabbedPane.addTab("Insert", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel(" Id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(197, 52, 46, 14);
		panel_1.add(lblId);
		
		JLabel lblName = new JLabel(" Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(199, 102, 66, 14);
		panel_1.add(lblName);
		
		JLabel lblCost = new JLabel(" Cost");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCost.setBounds(199, 147, 66, 14);
		panel_1.add(lblCost);
		
		JLabel lblExpiryDate = new JLabel(" Expiry date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExpiryDate.setBounds(197, 199, 103, 17);
		panel_1.add(lblExpiryDate);
		
		JLabel lblLocation = new JLabel(" Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLocation.setBounds(199, 310, 78, 14);
		panel_1.add(lblLocation);
		
		JLabel lblQuantity = new JLabel(" Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuantity.setBounds(197, 244, 103, 17);
		panel_1.add(lblQuantity);
		
		id = new JTextField();
		id.setBounds(356, 46, 86, 20);
		panel_1.add(id);
		id.setColumns(10);
		
		cost = new JTextField();
		cost.setColumns(10);
		cost.setBounds(356, 146, 86, 20);
		panel_1.add(cost);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(356, 101, 86, 20);
		panel_1.add(name);
		
		expiry = new JTextField();
		expiry.setText(" ");
		expiry.setColumns(10);
		expiry.setBounds(356, 199, 86, 20);
		panel_1.add(expiry);
		
		
		
		quantity = new JTextField();
		quantity.setText(" ");
		quantity.setColumns(10);
		quantity.setBounds(356, 244, 86, 20);
		panel_1.add(quantity);
		
		location = new JTextField();
		location.setColumns(10);
		location.setBounds(356, 304, 86, 20);
		panel_1.add(location);
		
		JLabel lblType = new JLabel(" Type");
		lblType.setBounds(213, 359, 46, 14);
		panel_1.add(lblType);
		
		JComboBox combotype = new JComboBox();
		combotype.setEditable(true);
		combotype.setModel(new DefaultComboBoxModel(new String[] {"fever", "pain", "cold"}));
		combotype.setBounds(316, 356, 120, 20);
		panel_1.add(combotype);
		
		
		JButton btnInsert = new JButton(" Insert");
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
					String query="insert into medicine"
							+"( id,name,quantity,location,cost,expiry,type )"
							+"values(?,?,?,?,?,?,?)";
					PreparedStatement pst=con.prepareStatement( query);
					pst.setString( 1, id.getText());
					pst.setString( 2, name.getText());
					pst.setString( 3, quantity.getText());
					pst.setString( 4, location.getText());
					pst.setString( 5, cost.getText());
					pst.setString( 6, expiry.getText());
					pst.setString( 7,(String)combotype.getSelectedItem());
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Insert Successfully");
				}
				catch(Exception e) {
					System.out.println( "Error"+e);
				
			}
			}});
		btnInsert.setBounds(599, 420, 135, 39);
		panel_1.add(btnInsert);
		
		
		JButton btnUpdate = new JButton(" Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnUpdate.setBounds(290, 420, 152, 39);
		panel_1.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
					String query="update medicine set name=?,quantity=?,location=?,cost=?,expiry=?,type=? where id=?";
					PreparedStatement pst=con.prepareStatement( query);
					
					pst.setString( 1, name.getText());
					pst.setString( 2, quantity.getText());
					pst.setString( 3, location.getText());
					pst.setString( 4, cost.getText());
					pst.setString( 5, expiry.getText());
					
					pst.setString( 6, (String)combotype.getSelectedItem());
					pst.setString( 7, id.getText());
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Updated Successfully");
				}
				catch(Exception e) {
					System.out.println( "Error"+e);
				
			}
				
				
			}
		});
		
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Create User", null, panel_4, null);
		panel_4.setLayout(null);
		
		newuser = new JTextField();
		newuser.setText(" ");
		newuser.setBounds(468, 114, 130, 25);
		panel_4.add(newuser);
		newuser.setColumns(10);
		
		newpass = new JPasswordField();
		newpass.setBounds(468, 174, 130, 24);
		panel_4.add(newpass);
		
		JButton btnCreate = new JButton(" Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					Class.forName(driver);
					Connection con=DriverManager.getConnection(url,user,pass);
					String query="insert into login"
							+"( username,password )"
							+"values(?,?)";
					PreparedStatement pst=con.prepareStatement( query);
					pst.setString( 1, (newuser.getText()).trim());
					pst.setString( 2, newpass.getText());
					 
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Created Successfully");
				}
				catch(Exception e) {
					System.out.println( "Error"+e);
				
			}
				
				
			}
		});
		btnCreate.setBounds(316, 298, 214, 35);
		panel_4.add(btnCreate);
		
		JLabel lblUserName = new JLabel(" User Name  :");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUserName.setBounds(189, 101, 188, 35);
		panel_4.add(lblUserName);
		
		JLabel lblPassword = new JLabel(" Password   :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(189, 173, 157, 25);
		panel_4.add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("KUMAR MEDICAL STORE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(501, 66, 374, 37);
		frame.getContentPane().add(lblNewLabel);
		
	    lblTime = new JLabel(" Time  :");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTime.setBounds(994, 31, 302, 37);
		frame.getContentPane().add(lblTime);
		
		 lblLoggedInAs = new JLabel();
		String loginName=object.setName;
		lblLoggedInAs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoggedInAs.setBounds(1004, 85, 254, 29);
		frame.getContentPane().add(lblLoggedInAs);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tabbedPane, panel_2, lblSearchById, scrollPane_1, tableSearch, comboBoxSearch, textFieldSearch, panel_3, lblDeleteTheMedicine, del_id, btnDelete, panel, scrollPane, table, btnDisplay, panel_1, lblId, lblName, lblCost, lblExpiryDate, lblLocation, lblQuantity, id, cost, name, expiry, btnUpdate, quantity, location, btnInsert}));
		
		lblLoggedInAs.setText("logger"+loginName);
		
		JLabel label = new JLabel(" ");
		
		Image img2= new ImageIcon(this.getClass().getResource("/medical.png")).getImage();
		label.setIcon(new ImageIcon(img2));
		
		label.setBounds(668, 11, 71, 57);
		frame.getContentPane().add(label);
}
}