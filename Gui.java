import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Gui {
	String className, importString, implementString;
	boolean mainClass;
	JFrame frame = new JFrame("Create a Class");
	JPanel panel = new JPanel();
	NamePanel classNamePanel = new NamePanel();
	CbPanel checkBoxPanel = new CbPanel();
	TfPanel textFieldPanel = new TfPanel();
	BtnPanel addBtnPanel = new BtnPanel();
	ArrayList<String> importList = new ArrayList<String>();
	ArrayList<String> extendList = new ArrayList<String>();
	ArrayList<String> implementList = new ArrayList<String>();

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
		checkBoxPanel.importCb.addActionListener(new ImportsListener());
		checkBoxPanel.extendCb.addActionListener(new ExtendsListener());
		checkBoxPanel.implementCb.addActionListener(new ImplementsListener());
		create.addActionListener(new SubmitListener());
		panel.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(330, 550);
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
		importString = null;
		implementString = null;
		mainClass = false;
		importList.clear();
		extendList.clear();
		implementList.clear();
		textFieldPanel.importTf.setEnabled(false);
		addBtnPanel.importBtn.setEnabled(false);
		textFieldPanel.implementTf.setEnabled(false);
		addBtnPanel.implementBtn.setEnabled(false);
	}

	public boolean classNameCheck(String cName) {
		boolean testPass = false;
		if (cName.length() == 0) {
			JOptionPane.showMessageDialog(frame, "Invalid Class Name.");
		} else {
			testPass = true;
		}
		return testPass;
	}

	public class ImportsListener implements ActionListener {
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

	public class AddImport implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String parseImport = "import " + textFieldPanel.importTf.getText() + ";";
			importList.add(parseImport);
			textFieldPanel.importTf.setText("");
		}
	}

	public class ExtendsListener implements ActionListener {
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

	public class AddExtend implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (extendList.size() != 0) {
				String update = JOptionPane.showInputDialog(frame, "You can only extend one class.\nOverride previous parent or cancel");
				if (update == null) {
					System.out.println("nothing was input.  is still extended.");
					textFieldPanel.extendTf.setText(extendList.get(0));
				} else {
					extendList.clear();
					extendList.add(update);
				}
			} else {
				extendList.add(textFieldPanel.extendTf.getText());
			}
			textFieldPanel.extendTf.setEnabled(false);
			addBtnPanel.extendBtn.setEnabled(false);
		}
	}

	public class ImplementsListener implements ActionListener {
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

	public class AddImplement implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			implementList.add(textFieldPanel.implementTf.getText());
			textFieldPanel.implementTf.setText("");
		}
	}

	public class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			className = classNamePanel.classNameTf.getText();
			mainClass = checkBoxPanel.mainCb.isSelected();
			if (classNameCheck(className)) {
				new ClassConstructor(className, mainClass, importList, extendList, implementList);
			} else {
				System.out.println("error somewhere.");
			}
			clearScreen();
		}
	}
}