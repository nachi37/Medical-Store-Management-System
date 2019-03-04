package Source;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GenerateSlip {

	private JFrame frame;
	public JTable table;
	private JLabel lblTime1;
	private JLabel lblCustomer;
	public String customer;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateSlip window = new GenerateSlip();
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
	
	
public void clock() {
		
		Thread clock=new Thread() {
			public void run() {
				try {
				//	while(true) {
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
			        lblTime1.setText("Time :"+Hour+":"+Minute+":"+Second);
					
			//	sleep(1000);}
			        }
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
	}

		 
 
	
     //    System.out.println("   "+customer);
	public GenerateSlip(){
		initialize();
	
	}
	
	//overload GenerateSlip
	 public GenerateSlip(String para) {
		initialize();
		 clock();
		 lblCustomer.setText("Customer: "+para);
	 }
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKumarMedicalStore = new JLabel(" Kumar Medical Store");
		lblKumarMedicalStore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKumarMedicalStore.setBounds(135, 22, 223, 33);
		frame.getContentPane().add(lblKumarMedicalStore);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 93, 348, 139);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			 "Name", "Price", "Quantity"
			}
		));
		scrollPane.setViewportView(table);
		
		lblTime1 = new JLabel(" Time");
		lblTime1.setBounds(203, 66, 181, 14);
		frame.getContentPane().add(lblTime1);
		
		 lblCustomer = new JLabel(" Customer");
		lblCustomer.setBounds(21, 66, 161, 14);
		frame.getContentPane().add(lblCustomer);
		
		
		
	}

	public void setVisible1() {
		// TODO Auto-generated method stub
		frame.setVisible( true);
		
	}
	
}
