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
public class Participant implements Serializable {
		private String name, address, mobileNo, orgName, email;
		private JTextField txtName;
		private JTextField txtAddress;
		private JTextField txtOrgName;
		private JTextField txtEmailID;
		private JTextField txtMobNo;
		private JFrame pwindow;
		
		private void set(String n, String a, String o, String e, String m) throws Exception{
			if(n.isEmpty() || a.isEmpty() || o.isEmpty() || e.isEmpty() || m.isEmpty())
				throw new Exception();
			name = n;
			address = a;
			orgName = o;
			email = e;
			mobileNo = m;
			
		}
		
		public String getName(){
			return name;
		}

		public void updateDetails(){
			txtName.setText(name);
			txtAddress.setText(address);
			txtOrgName.setText(orgName);
			txtEmailID.setText(email);
			txtMobNo.setText(mobileNo);
		}
		
		public boolean isVisible(){
			return pwindow.isVisible();
		}
		
		/**
		 * @wbp.parser.entryPoint
		 */
		public void partHomeGUI(){
			pwindow = new JFrame("Participant Home");
			pwindow.setResizable(false);
			pwindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			pwindow.setLocation(-11, -27);
			pwindow.setVisible(true);
			pwindow.setSize(464, 349);
			pwindow.getContentPane().setLayout(new CardLayout(0, 0));
			
			JPanel panel = new JPanel();
			pwindow.getContentPane().add(panel, "name_11592284295259");
			panel.setLayout(null);
			
			JLabel lblName = new JLabel("Name");
			lblName.setBounds(54, 56, 38, 16);
			panel.add(lblName);
			
			JLabel lblAddress = new JLabel("Address");
			lblAddress.setBounds(54, 93, 66, 16);
			panel.add(lblAddress);
			
			JLabel lblOrganizationName = new JLabel("Organization name");
			lblOrganizationName.setBounds(54, 129, 164, 16);
			panel.add(lblOrganizationName);
			
			JLabel lblEmailId = new JLabel("Email ID");
			lblEmailId.setBounds(54, 167, 66, 16);
			panel.add(lblEmailId);
			
			JLabel lblMobileNo = new JLabel("Mobile no.");
			lblMobileNo.setBounds(54, 205, 118, 16);
			panel.add(lblMobileNo);
			
			txtName = new JTextField();
			
			txtName.setBounds(214, 54, 172, 20);
			panel.add(txtName);
			txtName.setColumns(10);
			
			txtAddress = new JTextField();
			txtAddress.setBounds(214, 91, 172, 20);
			panel.add(txtAddress);
			txtAddress.setColumns(10);
			
			txtOrgName = new JTextField();
			txtOrgName.setBounds(214, 127, 172, 20);
			panel.add(txtOrgName);
			txtOrgName.setColumns(10);
			
			txtEmailID = new JTextField();
			txtEmailID.setBounds(214, 165, 172, 20);
			panel.add(txtEmailID);
			txtEmailID.setColumns(10);
			
			txtMobNo = new JTextField();
			txtMobNo.setBounds(214, 203, 172, 20);
			panel.add(txtMobNo);
			txtMobNo.setColumns(10);
			
			JButton btnSaveAndClose = new JButton("Save and close");
			btnSaveAndClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						set(txtName.getText(), txtAddress.getText(), txtOrgName.getText(), txtEmailID.getText(), txtMobNo.getText());
					}catch(Exception ex){
						JOptionPane.showMessageDialog(pwindow, "All fields must be non-empty.", "Error!", 0);
						return;
					}
					pwindow.dispose();
				}
			});
			btnSaveAndClose.setBounds(214, 251, 164, 26);
			panel.add(btnSaveAndClose);
			
			
			updateDetails();
		}
	}
	