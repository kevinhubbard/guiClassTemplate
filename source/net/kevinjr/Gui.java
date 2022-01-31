package net.kevinjr;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Gui {
	String className, extendString;
	boolean mainClass;
	JFrame frame = new JFrame("Create a Class");
	JPanel panel = new JPanel();
	NamePanel classNamePanel = new NamePanel();
	CbPanel checkBoxPanel = new CbPanel();
	TfPanel textFieldPanel = new TfPanel();
	BtnPanel addBtnPanel = new BtnPanel();
	ClassConstructor cc = new ClassConstructor();

	public Gui() {
		panel.add(classNamePanel);
		panel.add(checkBoxPanel);
		panel.add(textFieldPanel);
		panel.add(addBtnPanel);
		JButton create = new JButton("Create");
		panel.add(create);
		addBtnPanel.importBtn.addActionListener(new AddImport());
		addBtnPanel.extendBtn.addActionListener(new AddExtend());
		addBtnPanel.implementBtn.addActionListener(new AddImplement());
		checkBoxPanel.importCb.addActionListener(new ImportCbListener());
		checkBoxPanel.extendCb.addActionListener(new ExtendCbListener());
		checkBoxPanel.implementCb.addActionListener(new ImplementCbListener());
		create.addActionListener(new SubmitListener());
		panel.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(330, 225);
		frame.setResizable(false);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((dim.width/2)-(frame.getWidth()/2), (dim.height/2)-(frame.getHeight()/2));
		frame.setVisible(true);
	}

	public void clearScreen() {
		classNamePanel.classNameTf.setText("");
		textFieldPanel.importTf.setText("");
		textFieldPanel.extendTf.setText("");
		textFieldPanel.implementTf.setText("");
		checkBoxPanel.mainCb.setSelected(false);
		checkBoxPanel.importCb.setSelected(false);
		checkBoxPanel.extendCb.setSelected(false);
		checkBoxPanel.implementCb.setSelected(false);
		className = null;
		mainClass = false;
		extendString = null;
		textFieldPanel.importTf.setEnabled(false);
		addBtnPanel.importBtn.setEnabled(false);
		textFieldPanel.implementTf.setEnabled(false);
		addBtnPanel.implementBtn.setEnabled(false);
	}

//CHECKBOX LISTENERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public class ImportCbListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBoxPanel.importCb.isSelected()) {
				textFieldPanel.importTf.setEnabled(true);
				addBtnPanel.importBtn.setEnabled(true);
			} else {
				textFieldPanel.importTf.setEnabled(false);
				addBtnPanel.importBtn.setEnabled(false);
			}
		}
	}

	public class ExtendCbListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBoxPanel.extendCb.isSelected()) {
				textFieldPanel.extendTf.setEnabled(true);
				addBtnPanel.extendBtn.setEnabled(true);
			} else {
				textFieldPanel.extendTf.setEnabled(false);
				addBtnPanel.extendBtn.setEnabled(false);
			}
		}
	}

	public class ImplementCbListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBoxPanel.implementCb.isSelected()) {
				textFieldPanel.implementTf.setEnabled(true);
				addBtnPanel.implementBtn.setEnabled(true);
			} else {
				textFieldPanel.implementTf.setEnabled(false);
				addBtnPanel.implementBtn.setEnabled(false);
			}
		}
	}
//ADD BUTTON LISTENERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public class AddImport implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String testImport = textFieldPanel.importTf.getText();
			//~~~VALIDATE INPUT~~~~//
			if (cc.validImport(testImport) == false) {
				JOptionPane.showMessageDialog(frame, "Invalid Import name.");
				textFieldPanel.importTf.setText("");
				textFieldPanel.importTf.requestFocus();
			} else {
				System.out.println(testImport + " was valid.");
				textFieldPanel.importTf.setText("");
				textFieldPanel.importTf.requestFocus();
			}
		}
	}

	public class AddExtend implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String testExtend = textFieldPanel.extendTf.getText();
			//~~~VALIDATE INPUT~~~~//
			if (cc.validExtend(testExtend) == false ) {
				JOptionPane.showMessageDialog(frame, "Invalid Extend name.");
				textFieldPanel.extendTf.setText(extendString);
				checkBoxPanel.extendCb.setSelected(false);
			} else {
				System.out.println(testExtend + " was valid.");
				extendString = textFieldPanel.extendTf.getText();
			}
			textFieldPanel.extendTf.setEnabled(false);
			addBtnPanel.extendBtn.setEnabled(false);
		}
	}

	public class AddImplement implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String testImplement = textFieldPanel.implementTf.getText();
			//~~~VALIDATE INPUT~~~~//
			if (cc.validImplement(testImplement) == false) {
				JOptionPane.showMessageDialog(frame, "Invalid Implement name.");
				textFieldPanel.implementTf.setText("");
				textFieldPanel.implementTf.requestFocus();
			} else {
				System.out.println(testImplement + " was valid.");
				textFieldPanel.implementTf.setText("");
				textFieldPanel.implementTf.requestFocus();
			}
		}
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	public class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String testName = classNamePanel.classNameTf.getText();
			if (cc.validName(testName) == false) {
				JOptionPane.showMessageDialog(frame, "Invalid Class Name.");
				classNamePanel.classNameTf.setText("");
				classNamePanel.classNameTf.requestFocus();
			} else {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				f.setCurrentDirectory(new File(System.getProperty("user.home")));
				int returnVal = f.showSaveDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Save approved.");
					File saveLoc = f.getSelectedFile();
					String locationString = saveLoc.getAbsolutePath();
					cc.setSaveLocation(locationString);
					cc.isMainClass(checkBoxPanel.mainCb.isSelected());
					cc.createFile();
					JOptionPane.showMessageDialog(frame, "File successfully created.");
					clearScreen();
					//ASK USER IF THEY WANT TO CREATE ANOTHER CLASS OR EXIT
				} else {
					System.out.println("Save selection canceled.");
				}	
			}
		}
	}
}