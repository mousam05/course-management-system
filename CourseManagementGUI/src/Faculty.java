import java.awt.CardLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Faculty implements Serializable {
		private String dept, name, address, mobileNo, email;
		private JTextField txtName;
		private JTextField txtAddress;
		private JTextField txtDept;
		private JTextField txtEmailID;
		private JTextField txtMobNo;
		private JFrame fwindow;
		
		private void set(String n, String a, String d, String e, String m) throws Exception{
			if(n.isEmpty() || a.isEmpty() || d.isEmpty() || e.isEmpty() || m.isEmpty())
				throw new Exception();
			name = n;
			address = a;
			dept = d;
			email = e;
			mobileNo = m;
			
		}
		
		public String getName(){
			return name;
		}
		
		
		
		public void updateDetails(){
			txtName.setText(name);
			txtAddress.setText(address);
			txtDept.setText(dept);
			txtEmailID.setText(email);
			txtMobNo.setText(mobileNo);
		}
		
		
		public boolean isVisible(){
			return fwindow.isVisible();
		}
		/**
		 * @wbp.parser.entryPoint
		 */
		public void facHomeGUI(){
			fwindow = new JFrame("Faculty Home");
			fwindow.setResizable(false);
			fwindow.setLocation(-11, -27);
			fwindow.setVisible(true);
			fwindow.setSize(462, 350);
			fwindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			fwindow.getContentPane().setLayout(new CardLayout(0, 0));
			
			JPanel panel = new JPanel();
			fwindow.getContentPane().add(panel, "name_11592284295259");
			panel.setLayout(null);
			
			JLabel lblDept = new JLabel("Department");
			lblDept.setBounds(49, 56, 129, 16);
			panel.add(lblDept);
			
			JLabel lblName = new JLabel("Name");
			lblName.setBounds(49, 97, 66, 16);
			panel.add(lblName);
			
			JLabel lblAddress = new JLabel("Address");
			lblAddress.setBounds(49, 129, 164, 16);
			panel.add(lblAddress);
			
			JLabel lblEmailId = new JLabel("Email ID");
			lblEmailId.setBounds(49, 167, 66, 16);
			panel.add(lblEmailId);
			
			JLabel lblMobileNo = new JLabel("Mobile no.");
			lblMobileNo.setBounds(49, 205, 118, 16);
			panel.add(lblMobileNo);
			
			txtName = new JTextField();
			
			txtName.setBounds(216, 95, 172, 20);
			panel.add(txtName);
			txtName.setColumns(10);
			
			txtAddress = new JTextField();
			txtAddress.setBounds(216, 127, 172, 20);
			panel.add(txtAddress);
			txtAddress.setColumns(10);
			
			txtDept = new JTextField();
			txtDept.setBounds(216, 54, 172, 20);
			panel.add(txtDept);
			txtDept.setColumns(10);
			
			txtEmailID = new JTextField();
			txtEmailID.setBounds(216, 165, 172, 20);
			panel.add(txtEmailID);
			txtEmailID.setColumns(10);
			
			txtMobNo = new JTextField();
			txtMobNo.setBounds(216, 203, 172, 20);
			panel.add(txtMobNo);
			txtMobNo.setColumns(10);
			JButton btnSaveAndClose = new JButton("Save and close");
			btnSaveAndClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						set(txtName.getText(), txtAddress.getText(), txtDept.getText(), txtEmailID.getText(), txtMobNo.getText());
					}catch(Exception ex){
						JOptionPane.showMessageDialog(fwindow, "All fields must be non-empty.", "Error!", 0);
						return;
					}
					fwindow.dispose();
					
					
				}
			});
			btnSaveAndClose.setBounds(216, 240, 164, 26);
			panel.add(btnSaveAndClose);
			
			updateDetails();
		}
	}
	