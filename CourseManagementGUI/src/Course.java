import java.awt.CardLayout;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Course implements Serializable {
		
		
		private String name;
		private double fee;
		private Date startDate;
		private int duration; //in days
		private Faculty coord = new Faculty(); //course coordinator
		private Faculty[] inst = new Faculty[5]; //instructors
		private int instcount =0, partscount=0;
		private Participant[] parts = new Participant[5]; //participants
		private JTextField txtName, txtFee, txtStartDate, txtDuration;
		private JList<String> listInst;
		private JList<String> listPart;
		private JButton btnCoord;
		private JFrame cwindow = new JFrame();
		private JButton btnViewI, btnRemoveI, btnAddI, btnViewP, btnRemoveP, btnAddP;
		
		public static class MyException extends Exception{
			private String detail = new String();
			
			public MyException(String m){
				super();
				detail = m;
			}
			
			public String getDetail(){
				return detail;
			}
		}
		
		public void setBasic(String n, String f, String d, String du) throws Exception{
			
						
			name = n;
			if(name == "") throw new MyException("Name must be non-empty.");
			
			fee = Double.parseDouble(f);
			if(fee<0) throw new MyException("Fee must be positive.");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date tempDate = df.parse(d);
			
			Date oldDate = startDate;
			startDate = tempDate;
			if(CM.getYearCount(tempDate) == 2 || !CM.isRecent(tempDate)){
				startDate = oldDate;
				throw new MyException("Date is older than 5 years, or 10 courses are present in the last 12 months.");
			}
			
			
			duration = Integer.parseInt(du);
			if(duration < 0 || duration > 14) 	throw new MyException("Duration is negative or greater than 14 days.");
			
		}
		
		public void setCoord(){
			coord.facHomeGUI();
		}
		
		public String getName(){
			return name;
		}
	
		public Date getStartDate(){
			return startDate;
		}
		public boolean isVisible(){
			return coord.isVisible() || cwindow.isVisible();
		}
		public void updateDetails(){
			btnCoord.setText(coord.getName());
			String[] inames = new String[instcount], pnames = new String[partscount];
			for(int i=0; i<instcount; i++){
				inames[i] = new String();
				inames[i] = inst[i].getName();
			}
			listInst.setListData(inames);
			for(int i=0; i<partscount; i++){
				pnames[i] = new String();
				pnames[i] = parts[i].getName();
			}
			listPart.setListData(pnames);
			btnAddI.setEnabled(instcount<5);
			btnRemoveI.setEnabled(instcount>0);
			btnViewI.setEnabled(instcount>0);
			btnAddP.setEnabled(partscount<5);
			btnRemoveP.setEnabled(partscount>0);
			btnViewP.setEnabled(partscount>0);
			
		}
		/**
		 * @wbp.parser.entryPoint
		 */
		public void courseHomeGUI(){
			cwindow = new JFrame("Course Home");
			cwindow.setResizable(false);
			cwindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			cwindow.setVisible(true);
			cwindow.setSize(500, 581);
			cwindow.getContentPane().setLayout(new CardLayout(0, 0));
			
			JPanel panel = new JPanel();
			cwindow.getContentPane().add(panel, "name_13957142725827");
			panel.setLayout(null);
			
			JLabel lblName = new JLabel("Name");
			lblName.setBounds(85, 12, 38, 16);
			panel.add(lblName);
			
			JLabel lblFee = new JLabel("Fee");
			lblFee.setBounds(85, 40, 66, 16);
			panel.add(lblFee);
			
			JLabel lblStartDate = new JLabel("Start date");
			lblStartDate.setBounds(85, 68, 66, 16);
			panel.add(lblStartDate);
			
			JLabel lblDuration = new JLabel("Duration");
			lblDuration.setBounds(85, 96, 66, 16);
			panel.add(lblDuration);
			
			JLabel lblCoordinator = new JLabel("Co-ordinator");
			lblCoordinator.setBounds(85, 126, 131, 16);
			panel.add(lblCoordinator);
			
			txtName = new JTextField();
			
			txtName.setBounds(195, 10, 220, 20);
			panel.add(txtName);
			txtName.setColumns(10);
			
			txtFee = new JTextField();
			
			txtFee.setBounds(195, 38, 220, 20);
			panel.add(txtFee);
			txtFee.setColumns(10);
			
			txtStartDate = new JTextField();
		
			txtStartDate.setBounds(195, 66, 220, 20);
			panel.add(txtStartDate);
			txtStartDate.setColumns(10);
			
			txtDuration = new JTextField();
			
			txtDuration.setBounds(195, 94, 220, 20);
			panel.add(txtDuration);
			txtDuration.setColumns(10);
			
			btnCoord = new JButton("coord");
			btnCoord.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					coord.facHomeGUI();
				}
			});
			btnCoord.setBounds(205, 121, 194, 26);
			panel.add(btnCoord);
			
			JLabel lblInstructors = new JLabel("Instructors");
			lblInstructors.setBounds(85, 155, 147, 16);
			panel.add(lblInstructors);
			
			JLabel lblParticipants = new JLabel("Participants");
			lblParticipants.setBounds(79, 316, 131, 16);
			panel.add(lblParticipants);
			
			btnAddI = new JButton("Add");
			btnAddI.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inst[instcount] = new Faculty();
					inst[instcount].facHomeGUI();
					instcount++;
				}
			});
			btnAddI.setBounds(122, 264, 110, 26);
			panel.add(btnAddI);
			
			btnRemoveI = new JButton("Remove");
			btnRemoveI.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ch = listInst.getSelectedIndex();
					if(ch == -1) return;
					
					if(inst[ch].isVisible()){
						JOptionPane.showMessageDialog(cwindow, "The given Faculty Home is already open.");
					}
					else
					{	
						for(int i=0; i<instcount - 1; i++)
							inst[i] = inst[i+1];
						instcount--;
						updateDetails();
					}
				}
			});
			btnRemoveI.setBounds(244, 264, 110, 26);
			panel.add(btnRemoveI);
			
			btnViewI = new JButton("View");
			btnViewI.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ch = listInst.getSelectedIndex();
					if(ch == -1) return;
					if(inst[ch].isVisible()){
						JOptionPane.showMessageDialog(cwindow, "The given Faculty Home is already open.");
					}
					else inst[ch].facHomeGUI();
				}
			});
			btnViewI.setBounds(359, 264, 110, 26);
			panel.add(btnViewI);
			
			btnAddP = new JButton("Add");
			btnAddP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parts[partscount] = new Participant();
					parts[partscount].partHomeGUI();
					partscount++;
				}
			});
			btnAddP.setBounds(122, 429, 110, 26);
			panel.add(btnAddP);
			
			btnRemoveP = new JButton("Remove");
			btnRemoveP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ch = listPart.getSelectedIndex();
					if(ch == -1) return;
					if(inst[ch].isVisible()){
						JOptionPane.showMessageDialog(cwindow, "The given Participant Home is already open.");
					}
					else
					{
						for(int i=0; i<partscount - 1; i++)
							parts[i] = parts[i+1];
						partscount--;
						updateDetails();
					}
				}
			});
			btnRemoveP.setBounds(244, 429, 110, 26);
			panel.add(btnRemoveP);
			
			btnViewP = new JButton("View");
			btnViewP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ch = listPart.getSelectedIndex();
					if(ch == -1) return;
					if(parts[ch].isVisible()){
						JOptionPane.showMessageDialog(cwindow, "The given Participant Home is already open.");
					}
					else parts[ch].partHomeGUI();
				}
			});
			btnViewP.setBounds(359, 429, 110, 26);
			panel.add(btnViewP);
			
			JButton btnSaveAndClose = new JButton("Save and close");
			btnSaveAndClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int flag = 1;
					if(coord.isVisible()) flag = 0;
					for(int i=0; i<instcount; i++)
						if(inst[i].isVisible()) flag = 0;
					for(int i=0; i<partscount; i++)
						if(parts[i].isVisible()) flag = 0;
					if(flag == 0){
						JOptionPane.showMessageDialog(cwindow, "You need to close all faculty/participant windows for this course.");
						return;
					}
					try{
					setBasic(txtName.getText(), txtFee.getText(), txtStartDate.getText(), txtDuration.getText());
					}catch(MyException ex){
						JOptionPane.showMessageDialog(cwindow, ex.getDetail(), "Error!", 0);
						return;
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(cwindow, "Error in parsing input.", "Error!", 0);
						return;
					}
					
					cwindow.dispose();
				}
			});
			btnSaveAndClose.setBounds(310, 487, 152, 26);
			panel.add(btnSaveAndClose);
			
			listInst = new JList<String>();
			JScrollPane scrollI = new JScrollPane(listInst);
			scrollI.setBounds(215, 154, 184, 95);
			panel.add(scrollI);
			
			listPart = new JList<String>();
			JScrollPane scrollP = new JScrollPane(listPart);
			scrollP.setBounds(215, 315, 184, 95);
			panel.add(scrollP);
			
			txtName.setText(name);
			txtFee.setText(Double.toString(fee));
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			txtStartDate.setText("" + df.format(startDate));
			txtDuration.setText(Integer.toString(duration));
			
			Timer tm = new Timer(0, new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int i1 = listInst.getSelectedIndex();
					int i2 = listPart.getSelectedIndex();
					updateDetails();
					listInst.setSelectedIndex(i1);
					listPart.setSelectedIndex(i2);
				}
				
			});
			tm.setDelay(1000);
			tm.start();
			
			
		}
	}
