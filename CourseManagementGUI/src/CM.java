
import java.io.*;
import java.util.Date;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CM {
	
	private static int coursecount;
	private static Course[] c = new Course[50];
	private static JButton btnNewCourse, btnDeleteCourse, btnViewCourse;
	private static JTextField textName, textFee, textDate, textDuration;
	private static JList<String> listCourses;	
	
	//----------------------utility functions---------------------------------------//
	
	//----checks whether a date is older than 5 years
	public static boolean isRecent(Date d){
		return(((new Date()).getTime() - d.getTime()) /(1000*60*60*24) < 5*365.0);
	}
	
	//----returns number of courses in the last 1 year
	public static int getYearCount(Date d){
		int count=0;
		
		for(int i=0; i<coursecount; i++){
			double diff = (d.getTime() - c[i].getStartDate().getTime()) /(1000*60*60*24.0);
			if(diff>0.0 && diff<365.0)
				count++;
		}
		return count;
	}
	
	//----to load records from file at start
	public static void loadFromFile() throws IOException, ClassNotFoundException {

		ObjectInputStream is;
		try{
			is = new ObjectInputStream(new FileInputStream("courses"));
		}catch(Exception e){
			coursecount = 0; return;
		}
		coursecount = is.readInt();
		for(int i=0; i<coursecount; i++){
			c[i] = (Course) is.readObject();
			
		}
		
		is.close();
	}
	
	//----to save records to file at close
	public static void saveToFile() throws FileNotFoundException, IOException {

		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("courses"));
		os.writeInt(coursecount);
		for(int i=0; i<coursecount; i++){
			os.writeObject(c[i]);
		}

		os.close();
	}
	
	//----to clear all text fields in create course panel
	public static void clearFields(){
		textName.setText(null);
		textFee.setText(null);
		textDate.setText(null);
		textDuration.setText(null);
	}
	
	//----to update list of courses after add/remove/modify	
	public static void updateCourseList(){
		String[] cnames = new String[coursecount];
		for(int i=0; i<coursecount; i++){
			cnames[i] = new String();
			cnames[i] = c[i].getName();
			if(!isRecent(c[i].getStartDate()))
					cnames[i] += " (Expired)";
		}
		listCourses.setListData(cnames);
		btnDeleteCourse.setEnabled(coursecount > 0);
		btnViewCourse.setEnabled(coursecount > 0);

	}
	
	//-----------------end of utility functions------------------//
	

	//----to drive the main GUI
	public static void courseSystemHomeGUI(){
		
		JFrame window = new JFrame("Course Management System");
		window.setResizable(false);
		window.setLocation(new Point(450, 200));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setVisible(true);
		window.setSize(400,400);
		window.getContentPane().setLayout(new CardLayout(2, 2));
		
		JPanel CMPanel = new JPanel();
		window.getContentPane().add(CMPanel, "name_5509227841909");
		CMPanel.setLayout(null);
		
		JPanel createCoursePanel = new JPanel();
		window.getContentPane().add(createCoursePanel, "name_8011815865683");
		createCoursePanel.setLayout(null);
		
		btnNewCourse = new JButton("New course");
		btnNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCoursePanel.setVisible(true);
				CMPanel.setVisible(false);
			}
		});
		btnNewCourse.setBounds(197, 260, 159, 26);
		CMPanel.add(btnNewCourse);
		
		btnDeleteCourse = new JButton("Delete course");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ch = listCourses.getSelectedIndex();
				if(ch == -1) return;
				if(c[ch].isVisible()){
					JOptionPane.showMessageDialog(window, "You need to close Course Home first.");
					return;
				}
				for(int i=ch; i<coursecount - 1; i++)
					c[i] = c[i+1];
				
				coursecount--;
				updateCourseList();
			}
		});
		btnDeleteCourse.setBounds(197, 201, 159, 26);
		CMPanel.add(btnDeleteCourse);
		
		btnViewCourse = new JButton("View course");
		btnViewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ch = listCourses.getSelectedIndex();
				if(ch == -1) return;
				if(c[ch].isVisible()){
					JOptionPane.showMessageDialog(window, "You need to close all course-related windows first.");
					return;
				}
				c[ch].courseHomeGUI();
			}
		});
		btnViewCourse.setBounds(12, 201, 159, 26);
		CMPanel.add(btnViewCourse);
		
		JPanel readmePanel = new JPanel();
		window.getContentPane().add(readmePanel, "name_6142642525060");
		readmePanel.setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0; i<coursecount; i++){
					if(c[i].isVisible()){
						JOptionPane.showMessageDialog(window, "You need to close all other windows first.");
						return;
					}
				}
				try{
					saveToFile();
				}catch(Exception ex){}
				System.exit(0);
			}
		});
		btnExit.setBounds(197, 322, 159, 26);
		CMPanel.add(btnExit);
		
		JLabel lblCoursesList = new JLabel("List of courses");
		lblCoursesList.setBounds(135, 12, 135, 16);
		CMPanel.add(lblCoursesList);
		
		listCourses = new JList<String>();
		
		JScrollPane scrollC = new JScrollPane(listCourses);
		scrollC.setBounds(69, 41, 233, 153);
		CMPanel.add(scrollC);
		
		JButton button = new JButton("README");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CMPanel.setVisible(false);
				readmePanel.setVisible(true);
			}
		});
		button.setBounds(12, 260, 159, 26);
		CMPanel.add(button);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(47, 55, 66, 16);
		createCoursePanel.add(lblName);
		
		JLabel lblFee = new JLabel("Fee");
		lblFee.setBounds(47, 94, 66, 16);
		createCoursePanel.add(lblFee);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(47, 138, 66, 16);
		createCoursePanel.add(lblDate);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(47, 180, 66, 16);
		createCoursePanel.add(lblDuration);
	
		JButton btnNext = new JButton("Proceed to Co-ordinator Home");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c[coursecount] = new Course();
				try{
					
					c[coursecount].setBasic(textName.getText(), textFee.getText(), textDate.getText(),textDuration.getText());
				}catch(Course.MyException ex){
					JOptionPane.showMessageDialog(window, ex.getDetail(), "Error!", 0);
					return;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(window, "Error in parsing input.", "Error!", 0);
					return;
				}
				
				
				c[coursecount].setCoord();
				coursecount++;
				CMPanel.setVisible(true);
				createCoursePanel.setVisible(false);
				clearFields();
				
				
			}
		});
		btnNext.setBounds(75, 261, 235, 26);
		createCoursePanel.add(btnNext);
		
		textName = new JTextField();
		textName.setBounds(131, 53, 179, 20);
		createCoursePanel.add(textName);
		textName.setColumns(10);
		
		textFee = new JTextField();
		textFee.setBounds(131, 92, 179, 20);
		createCoursePanel.add(textFee);
		textFee.setColumns(10);
		
		textDate = new JTextField();
		textDate.setColumns(10);
		textDate.setBounds(131, 136, 179, 20);
		createCoursePanel.add(textDate);
		
		textDuration = new JTextField();
		textDuration.setColumns(10);
		textDuration.setBounds(131, 178, 179, 20);
		createCoursePanel.add(textDuration);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CMPanel.setVisible(true);
				createCoursePanel.setVisible(false);
				clearFields();
			}
		});
		btnCancel.setBounds(75, 310, 235, 26);
		createCoursePanel.add(btnCancel);
		
		
		JScrollBar scrollBar = new JScrollBar();
		listCourses.add(scrollBar);
		
		
		String readme =
				"README: Course Management System\n---------------------------------\n"
				+ "Course input specifications:\n"
				+ "Name: non-empty string\nFee: positive real number\n"
				+ "Date format: dd/mm/yyyy\nDuration: integer between 1 and 14\n\n-----------------------------------\n"
				+ "Date constraints:\n(i) You cannot create a course older than 5 years.\n"
				+ "(ii) You can only create a course having less than 10 courses in the past 12 months.\n-------------------------------------\n"
				+ "A course can have upto 5 participants and 5 instructors.\n"
				+ "You must use Exit button to exit the program. All course/faculty/participant windows must be closed already.";
		
		JButton btnGotIt = new JButton("Got it");
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CMPanel.setVisible(true);
				readmePanel.setVisible(false);
			}
		});
		btnGotIt.setBounds(55, 301, 110, 26);
		readmePanel.add(btnGotIt);
		
		JTextArea txtrReadme = new JTextArea(readme);
		txtrReadme.setEditable(false);
		txtrReadme.setWrapStyleWord(true);
		txtrReadme.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(txtrReadme);
		scroll.setBounds(12, 12, 370, 277);
		readmePanel.add(scroll);
		
		
		Timer tm = new Timer(0, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int index = listCourses.getSelectedIndex();
				updateCourseList();
				listCourses.setSelectedIndex(index);
			}
			
		});
		tm.setDelay(1000);
		tm.start();
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		loadFromFile();
		courseSystemHomeGUI();

	}
}